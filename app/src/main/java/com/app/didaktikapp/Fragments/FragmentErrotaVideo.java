package com.app.didaktikapp.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
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
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FragmentErrotaVideo extends Fragment {

    private View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Long idActividad;

    private Button btnContinuar;
    private String mParam1;
    private String mParam2;


    public FragmentErrotaVideo() {}


    public static FragmentErrotaVideo newInstance(Long param1) {
        FragmentErrotaVideo fragment = new FragmentErrotaVideo();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idActividad = getArguments().getLong(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // OBTENEMOS LA VIEW
        view = inflater.inflate(R.layout.fragment_errota_video, container, false);

        //CONFIGURAMOS EL BOTON CONTINUAR
        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText(getResources().getString(R.string.Continuar));
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //actualizar bbdd
                guardarBBDD();
                //cerrar fragment
                //abrir siguiente fragment (fotos)
                FragmentErrotaFotos fragment = FragmentErrotaFotos.newInstance(idActividad);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                transaction.replace(R.id.fragment_frame, fragment);
                transaction.commit();
                transaction.addToBackStack("Fragment");

            }
        });
        btnContinuar.setEnabled(false);

        crearVideo(view);

        VideoView empezar = view.findViewById(R.id.errotaVideo);
        empezar.start();

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

    public void crearVideo(View view) {
        Uri path = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.videoprueba);
        VideoView video = view.findViewById(R.id.errotaVideo);
        video.setVideoURI(path);

        MediaController mediaController = new MediaController(getActivity());
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);
        video.setZOrderOnTop(true);

        video.setVisibility(View.VISIBLE);
        video.requestFocus();

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("video","Video terminado");
                btnContinuar.setEnabled(true);
            }
        });
    }

    private void guardarBBDD(){

        ActividadErrota actividadErrota = DatabaseRepository.getAppDatabase().getErrotaDao().getErrota(idActividad);

        actividadErrota.setFragment(2);

        DatabaseRepository.getAppDatabase().getErrotaDao().updateErrota(actividadErrota);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ((MapActivity)getActivity()).cambiarLocalizacion();
    }

}
