package com.app.didaktikapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.ClassToFtp;
import com.app.didaktikapp.FTP.Ftp;
import com.app.didaktikapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.muddzdev.styleabletoast.StyleableToast;
import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

/**
 * Fragmento UnibertsitateaFotos, donde se tienen que sacar fotos para
 * responder preguntas. Guarda las fotos y su estado en la base de datos
 * @author gennakk
 */
public class FragmentUnibertsitateaFotos extends Fragment {
    private Uri outuri;
    private View view;

    private LinearLayout fotosLayout;

    private Button btnContinuar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private Long idActividad;


    final int REQUEST_IMAGE_CAPTURE1 = 2001;
    final int REQUEST_IMAGE_CAPTURE2 = 2002;
    final int REQUEST_IMAGE_CAPTURE3 = 2003;

    private ImageButton foto1, foto2, foto3;

    private ImageView img1, img2, img3;

    private String ruta1, ruta2, ruta3;

    private boolean fotohecha1, fotohecha2, fotohecha3;


    public FragmentUnibertsitateaFotos() {

    }


    public static FragmentUnibertsitateaFotos newInstance(Long idActividad) {
        FragmentUnibertsitateaFotos fragment = new FragmentUnibertsitateaFotos();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, idActividad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            idActividad = getArguments().getLong(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
            //mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_unibertsitatea_fotos, container, false);
        fotosLayout = view.findViewById(R.id.uniFotosLayout);
        fotosLayout.setVisibility(View.VISIBLE);

        foto1 = view.findViewById(R.id.uniImagen1);
        foto1.setOnClickListener(new ListenerBoton());

        foto2 = view.findViewById(R.id.uniImagen2);
        foto2.setOnClickListener(new ListenerBoton());

        foto3 = view.findViewById(R.id.uniImagen3);
        foto3.setOnClickListener(new ListenerBoton());

        img1 = view.findViewById(R.id.uniImagenSacada1);
        img2 = view.findViewById(R.id.uniImagenSacada2);
        img3 = view.findViewById(R.id.uniImagenSacada3);

        fotohecha1 = false;
        fotohecha2 = false;
        fotohecha3 = false;

        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText(R.string.Continuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guardarImagen(img1);
                guardarImagen(img2);
                guardarImagen(img3);

                //EDIT BBDD Y CERRAR FRAGMENT

                guardarBBDD(img1,img2,img3);

                getFragmentManager().beginTransaction().remove(FragmentUnibertsitateaFotos.this).commit();



            }
        });

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Verifica permisos para Android 6.0+
            int permissionCheck = ContextCompat.checkSelfPermission(
                    getActivity().getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
            } else {

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Verifica permisos para Android 6.0+
            int permissionCheck = ContextCompat.checkSelfPermission(
                    getActivity().getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 225);
            } else {
                    Toast.
                            makeText(getContext(),"PERMISSOREAD",Toast.LENGTH_LONG).show();
            }
        }

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    REQUEST_IMAGE_CAPTURE1);
        }

         /*
        Botón flotante de ayuda
         */
        FloatingActionButton floatingActionButton = view.findViewById(R.id.helpButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewBoton) {

                SpotlightConfig config = new SpotlightConfig();
                config.setMaskColor( Color.parseColor("#E63A3A3A"));
                config.setIntroAnimationDuration(400);
                config.setFadingTextDuration(400);
                config.setPadding(20);
                config.setDismissOnTouch(true);
                config.setDismissOnBackpress(true);
                config.setPerformClick(false);
                config.setHeadingTvSize(24);
                config.setHeadingTvColor(Color.parseColor("#2B82C5"));
                config.setSubHeadingTvSize(24);
                config.setSubHeadingTvColor(Color.parseColor("#FAFAFA"));
                config.setLineAnimationDuration(300);
                config.setLineStroke(Utils.dpToPx(4));
                config.setLineAndArcColor( Color.parseColor("#2B82C5"));
                config.setShowTargetArc(true);


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        java.util.Random rndGenerator = new java.util.Random();

                        SpotlightSequence.getInstance(getActivity(),config)
                                .addSpotlight(view.findViewById(R.id.helpButton), getString(R.string.AyudaUniversidadTituloFotos), getString(R.string.AyudaUniversidadDetalleFotos), "preguntaUF" + rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.btnContinuar), getString(R.string.AyudaZumTituloContinuar), getString(R.string.AyudaZumDetalleContinuar), "continuarUF" + rndGenerator.nextInt(999999999))
                                .startSequence();
                    }
                },0);
            }
        });

        return view;
    }

    private class ListenerBoton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dispatchTakePictureIntent(v);
        }

        private void dispatchTakePictureIntent(View v){
            //Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            Intent takePictureIntent = new Intent();
            takePictureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            // path to /data/data/yourapp/app_data/imageDir
            File sdCard = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES);
            File dir = new File(sdCard.getAbsolutePath() + "/Didaktikapp");
            dir.mkdirs();
           // takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outuri);
            int request;
            if (v == view.findViewById(R.id.uniImagen1)) {
                request = REQUEST_IMAGE_CAPTURE1;
            } else if (v == view.findViewById(R.id.uniImagen2)) {
                request = REQUEST_IMAGE_CAPTURE2;
            } else {
                request = REQUEST_IMAGE_CAPTURE3;
            }

            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, request);
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((requestCode >= REQUEST_IMAGE_CAPTURE1 && requestCode <= REQUEST_IMAGE_CAPTURE3) && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, 1024, 1024, false);

            File sdCard = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES);
            File dir = new File(sdCard.getAbsolutePath() + "/Didaktikapp");
            dir.mkdirs();
            String fileName = String.format("%d.jpg", System.currentTimeMillis());
            String foto=null;
            if (requestCode == REQUEST_IMAGE_CAPTURE1) {
                img1.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,1000,1000,false));
                //img1.setImageURI(outuri);
                foto1.setVisibility(View.GONE);
                img1.setVisibility(View.VISIBLE);
                fotohecha1 = true;
                ruta1 = dir.getPath()+fileName;
                foto="uni1";
            } else if (requestCode == REQUEST_IMAGE_CAPTURE2) {
                img2.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,1000,1000,false));
               // img2.setImageURI(outuri);

                foto2.setVisibility(View.GONE);
                img2.setVisibility(View.VISIBLE);
                fotohecha2 = true;
                ruta2 = dir.getPath()+fileName;
                foto="uni2";
            } else {
                img3.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,1000,1000,false));
                //img3.setImageURI(outuri);

                foto3.setVisibility(View.GONE);
                img3.setVisibility(View.VISIBLE);
                fotohecha3 = true;
                ruta3 = dir.getPath()+fileName;
                foto="uni3";
            }
            Ftp.sendImage(getApplicationContext(),imageBitmap,MapActivity.GRUPO_S.getDeviceId(),MapActivity.GRUPO_S.getNombre(),foto);

        }
        if (fotohecha1&&fotohecha2&&fotohecha3){
            view.findViewById(R.id.btnContinuar).setEnabled(true);
        }
    }

    public void guardarImagen(ImageView iv){
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

        /*
        String foto=null;

        if(iv.getId()==R.id.uniImagenSacada1){
            ruta1 = dir.getPath()+fileName;
            foto="uni1";
        }
        if(iv.getId()==R.id.uniImagenSacada2){
            ruta2 = dir.getPath()+fileName;
            foto="uni2";
        }
        if(iv.getId()==R.id.uniImagenSacada3){
            ruta3 = dir.getPath()+fileName;
            foto="uni3";
        }

        Ftp.sendImage(bitmap,MapActivity.GRUPO_S.getDeviceId(),MapActivity.GRUPO_S.getNombre(),foto);
        */

        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Titulo" , "descripcion");
    }

    private void guardarBBDD(ImageView iv1,ImageView iv2,ImageView  iv3){
        StyleableToast.makeText(getContext(), getResources().getString(R.string.ToastImagenes), Toast.LENGTH_LONG, R.style.mytoast).show();

        ActividadUniversitatea actividadUniversitatea = DatabaseRepository.getAppDatabase().getUniversitateaDao().getUniversitatea(idActividad);

        actividadUniversitatea.setEstado(2);

        actividadUniversitatea.setFragment(3);
/*
        actividadUniversitatea.setFoto1(imageToBase64(iv1));

        actividadUniversitatea.setFoto2(imageToBase64(iv2));

        actividadUniversitatea.setFoto3(imageToBase64(iv3));
*/
        actividadUniversitatea.setFoto1(ruta1);

        actividadUniversitatea.setFoto2(ruta2);

        actividadUniversitatea.setFoto3(ruta3);
        DatabaseRepository.getAppDatabase().getUniversitateaDao().updateUniversitatea(actividadUniversitatea);
        ClassToFtp.send(getActivity(),ClassToFtp.TIPO_UNIVERSITATEA);

    }

    private String imageToBase64(ImageView iv){
        iv.buildDrawingCache();
        Bitmap bitmap = iv.getDrawingCache();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] image=stream.toByteArray();

        return  Base64.encodeToString(image, 0);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ((MapActivity)getActivity()).cambiarLocalizacion();
    }



}
