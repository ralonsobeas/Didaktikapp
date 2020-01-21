package com.app.didaktikapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FragmentUnibertsitateaFotos extends Fragment {

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

    private String mParam1, mParam2, mParam3;

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
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
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
        return view;
    }

    private class ListenerBoton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dispatchTakePictureIntent(v);
        }

        private void dispatchTakePictureIntent(View v){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

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

            if (requestCode == REQUEST_IMAGE_CAPTURE1) {
                img1.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,1000,1000,false));
                foto1.setVisibility(View.GONE);
                img1.setVisibility(View.VISIBLE);
                fotohecha1 = true;
            } else if (requestCode == REQUEST_IMAGE_CAPTURE2) {
                img2.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,1000,1000,false));
                foto2.setVisibility(View.GONE);
                img2.setVisibility(View.VISIBLE);
                fotohecha2 = true;
            } else {
                img3.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,1000,1000,false));
                foto3.setVisibility(View.GONE);
                img3.setVisibility(View.VISIBLE);
                fotohecha3 = true;
            }

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

        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Titulo" , "descripcion");
    }

    private void guardarBBDD(ImageView iv1,ImageView iv2,ImageView  iv3){

        ActividadUniversitatea actividadUniversitatea = DatabaseRepository.getAppDatabase().getUniversitateaDao().getUniversitatea(idActividad);

        actividadUniversitatea.setEstado(2);

        actividadUniversitatea.setFragment(3);

        actividadUniversitatea.setFoto1(imageToBase64(iv1));

        actividadUniversitatea.setFoto2(imageToBase64(iv2));

        actividadUniversitatea.setFoto3(imageToBase64(iv3));

        DatabaseRepository.getAppDatabase().getUniversitateaDao().updateUniversitatea(actividadUniversitatea);

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



}