package com.app.didaktikapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.app.didaktikapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FragmentErrota extends Fragment {

    private View view;

    final int REQUEST_IMAGE_CAPTURE1 = 1890;
    final int REQUEST_IMAGE_CAPTURE2 = 1891;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private LinearLayout fotosLayout, videoLayout, preguntasLayout;

    private Button btnContinuar;

    private ImageView img1_1,img1_2,img1_3,img1_4,img2_1,img2_2,img2_3,img2_4,img3_1,img3_2,anterior,ivPregunta1,ivPregunta2;
    private ImageButton ib1,ib2;

    private String imgSelec;
    private String mParam1;
    private String mParam2;

    private boolean sela1,sela2,selb1,selb2,selc1,selc2,seld1,seld2,sele1,sele2,img1,img2 = false;


    public FragmentErrota() {

    }


    public static FragmentErrota newInstance() {
        FragmentErrota fragment = new FragmentErrota();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // OBTENEMOS LA VIEW
        view = inflater.inflate(R.layout.fragment_errota, container, false);

        // PREGUNTAMOS POR PERMISOS PARA HACER LAS FOTOS LUEGO
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(
                    getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
            } else {

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(
                    getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 225);
            } else {

            }
        }

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    REQUEST_IMAGE_CAPTURE1);
        }

        //OBTENEMOS LOS LAYOUTS
        fotosLayout = view.findViewById(R.id.errotaUnirImagenes);
        fotosLayout.setVisibility(View.VISIBLE);

        videoLayout = view.findViewById(R.id.errotaVideoLayout);
        videoLayout.setVisibility(View.INVISIBLE);

        preguntasLayout = view.findViewById(R.id.errotaSacarFotos);
        preguntasLayout.setVisibility(View.INVISIBLE);

        //CONFIGURAMOS EL BOTON CONTINUAR
        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText(getResources().getString(R.string.Contunuar));
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fotosLayout.getVisibility() == View.VISIBLE){
                    fotosLayout.setVisibility(View.INVISIBLE);
//                    videoLayout.setVisibility(View.VISIBLE);
//                    VideoView empezar = view.findViewById(R.id.errotaVideo);
//                    empezar.start();
//                    videoLayout.setVisibility(View.INVISIBLE);
                    preguntasLayout.setVisibility(View.VISIBLE);
                    btnContinuar.setEnabled(false);
                }
//                else if(preguntasLayout.getVisibility() == View.VISIBLE){
//                    preguntasLayout.setVisibility(View.INVISIBLE);
//                    fotosLayout.setVisibility(View.VISIBLE);
//                    btnContinuar.setText("FINALIZAR");
//                }
                else if(preguntasLayout.getVisibility() == View.VISIBLE){
                    guardarImagen(ivPregunta1);
                    guardarImagen(ivPregunta2);
                }

            }
        });
        btnContinuar.setEnabled(false);

        //RELLENAMOS LAS DISTINTAS ACTIVIDADES CON LO NECESARIO
        crearImagenes(view);

        crearVideo(view);

        crearPreguntas(view);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void crearImagenes(View view) {
        img1_1 = view.findViewById(R.id.errotaImagen1_1);
        img1_2 = view.findViewById(R.id.errotaImagen1_2);
        img1_3 = view.findViewById(R.id.errotaImagen1_3);
        img1_4 = view.findViewById(R.id.errotaImagen1_4);
        img2_1 = view.findViewById(R.id.errotaImagen2_1);
        img2_2 = view.findViewById(R.id.errotaImagen2_2);
        img2_3 = view.findViewById(R.id.errotaImagen2_3);
        img2_4 = view.findViewById(R.id.errotaImagen2_4);
        img3_1 = view.findViewById(R.id.errotaImagen3_2);
        img3_2 = view.findViewById(R.id.errotaImagen3_3);
        sela1 = false;
        sela2 = false;
        selb1 = false;
        selb2 = false;
        selc1 = false;
        selc2 = false;
        seld1 = false;
        seld2 = false;
        sele1 = false;
        sele2 = false;
        imgSelec = "";

        ImageView[] iva = new ImageView[] {img1_1,img1_2,img1_3,img1_4,img2_1,img2_2,img2_3,img2_4,img3_1,img3_2};
        List<ImageView> list = Arrays.asList(iva);
        Collections.shuffle(list);
        for (int x=0;x<list.size();x++) {
            int img = R.drawable.errotaa1;
            switch (x) {
                case 0:
                    img = R.drawable.errotaa1;
                    int finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!sela1) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "a1";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("a2")) {
                                        sela1 = true;
                                        sela2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                        if (sela1&&selb1&&selc1&&seld1&&sele1)
                                            btnContinuar.setEnabled(true);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 1:
                    img = R.drawable.errotaa2;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!sela2) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "a2";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("a1")) {
                                        sela1 = true;
                                        sela2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                        if (sela1&&selb1&&selc1&&seld1&&sele1)
                                            btnContinuar.setEnabled(true);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 2:
                    img = R.drawable.errotab1;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!selb1) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "b1";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("b2")) {
                                        selb1 = true;
                                        selb2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                        if (sela1&&selb1&&selc1&&seld1&&sele1)
                                            btnContinuar.setEnabled(true);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 3:
                    img = R.drawable.errotab2;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!selb2) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "b2";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("b1")) {
                                        selb1 = true;
                                        selb2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                        if (sela1&&selb1&&selc1&&seld1&&sele1)
                                            btnContinuar.setEnabled(true);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 4:
                    img = R.drawable.errotac1;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!selc1) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "c1";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("c2")) {
                                        selc1 = true;
                                        selc2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                        if (sela1&&selb1&&selc1&&seld1&&sele1)
                                            btnContinuar.setEnabled(true);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 5:
                    img = R.drawable.errotac2;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!selc2) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "c2";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("c1")) {
                                        selc1 = true;
                                        selc2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                        if (sela1&&selb1&&selc1&&seld1&&sele1)
                                            btnContinuar.setEnabled(true);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 6:
                    img = R.drawable.errotad1;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!seld1) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "d1";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("d2")) {
                                        seld1 = true;
                                        seld2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                        if (sela1&&selb1&&selc1&&seld1&&sele1)
                                            btnContinuar.setEnabled(true);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 7:
                    img = R.drawable.errotad2;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!seld2) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "d2";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("d1")) {
                                        seld1 = true;
                                        seld2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                        if (sela1&&selb1&&selc1&&seld1&&sele1)
                                            btnContinuar.setEnabled(true);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 8:
                    img = R.drawable.errotae1;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!sele1) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "e1";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("e2")) {
                                        sele1 = true;
                                        sele2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                        if (sela1&&selb1&&selc1&&seld1&&sele1)
                                            btnContinuar.setEnabled(true);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 9:
                    img = R.drawable.errotae2;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!sele2) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "e2";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("e1")) {
                                        sele1 = true;
                                        sele2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                        if (sela1&&selb1&&selc1&&seld1&&sele1)
                                            btnContinuar.setEnabled(true);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
            }
            list.get(x).setImageResource(img);
        }

    }

    public void crearVideo(View view) {
        Uri path = Uri.parse("android.resource://com.app.didaktikapp/" + R.raw.videoprueba);
        VideoView video = view.findViewById(R.id.errotaVideo);
        video.setVideoURI(path);
        video.setVisibility(View.VISIBLE);
    }

    public void crearPreguntas(View view) {
        ivPregunta1 = view.findViewById(R.id.ivErrotaPregunta1);
        ivPregunta2 = view.findViewById(R.id.ivErrotaPregunta2);

        ib1 = view.findViewById(R.id.btnErrotaPregunta1);
        ib1.setOnClickListener(new ListenerBoton());

        ib2 = view.findViewById(R.id.btnErrotaPregunta2);
        ib2.setOnClickListener(new ListenerBoton());
    }

    private class ListenerBoton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dispatchTakePictureIntent(v);
        }

        private void dispatchTakePictureIntent(View v){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            int request;
            if(v == view.findViewById(R.id.btnErrotaPregunta1)){
                request = REQUEST_IMAGE_CAPTURE1;
            }else{
                request = REQUEST_IMAGE_CAPTURE2;
            }

            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, request);
            }
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if ((requestCode == REQUEST_IMAGE_CAPTURE1 || requestCode == REQUEST_IMAGE_CAPTURE2) && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            if (requestCode == REQUEST_IMAGE_CAPTURE1) {
                ivPregunta1.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,1000,1000,false));
                ib1.setVisibility(View.INVISIBLE);
                img1 = true;
                if (img2) btnContinuar.setEnabled(true);
            }
            else {
                ivPregunta2.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,1000,1000,false));
                ib2.setVisibility(View.INVISIBLE);
                img2 = true;
                if (img1) btnContinuar.setEnabled(true);
            }

        }

        if(ivPregunta1.getDrawable() != null && ivPregunta2.getDrawable() != null){
            view.findViewById(R.id.btnContinuar).setEnabled(true);
        }
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void guardarImagen(ImageView iv){
        //to get the image from the ImageView (say iv)
        BitmapDrawable draw = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = draw.getBitmap();

        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File dir = new File(sdCard.getAbsolutePath() + "/Didaktikapp");
        dir.mkdirs();
        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        Log.i("FILE",fileName);
        Log.i("DIR",dir.getPath());
        File outFile = new File(dir, fileName);
        try {
            outStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Titulo" , "descripcion");
    }

}
