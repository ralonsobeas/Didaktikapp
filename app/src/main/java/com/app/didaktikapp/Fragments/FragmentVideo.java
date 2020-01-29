package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.R;

public class FragmentVideo extends Fragment {

    private View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Long idActividad;

    private Button btnContinuar;
    private String mParam1;
    private String mParam2;

    private VideoView video;

    private static String fragment;
    private int rawvideo;

    public FragmentVideo() {}


    public static FragmentVideo newInstance(Long param1, String nombre) {
        fragment = nombre;

        FragmentVideo fragment = new FragmentVideo();
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

        switch(fragment) {
            case "errota":
                rawvideo = R.raw.video_sanmiguel_errota;
                break;
            case "parrokia":
                rawvideo = R.raw.video_sanmiguel_parrokia;
                break;
            case "zumeltzegi":
                rawvideo = R.raw.video_zumeltzegi;
                break;
            default:
                Log.e("VIDEO","Nombre '"+fragment+"' no reconocido");
        }

        //CONFIGURAMOS EL BOTON CONTINUAR
        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText(getResources().getString(R.string.Continuar));
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (video.isPlaying()) video.stopPlayback();

                //actualizar bbdd
//                guardarBBDD();

                switch(fragment) {
                    case "errota":
                        //Guardar BBDD
                        ActividadErrota actividadErrota = DatabaseRepository.getAppDatabase().getErrotaDao().getErrota(idActividad);
                        actividadErrota.setFragment(2);
                        DatabaseRepository.getAppDatabase().getErrotaDao().updateErrota(actividadErrota);

                        //Cerrar fragment y abrir el siguiente
                        FragmentErrotaFotos fragment = FragmentErrotaFotos.newInstance(idActividad);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                        transaction.replace(R.id.fragment_frame, fragment);
                        transaction.commit();
                        transaction.addToBackStack("Fragment");
                        break;
                    case "parrokia":

                        break;
                    case "zumeltzegi":

                        break;
                    default:
                        Log.e("VIDEO","Nombre no reconocido");
                }

            }
        });
        //btnContinuar.setEnabled(false);

        video = view.findViewById(R.id.errotaVideo);
        video.start();

        crearVideo();

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

    public void crearVideo() {
        // EDITAR ESTA LINEA ->
        Uri path = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+rawvideo);
        //VideoView video = view.findViewById(R.id.errotaVideo);
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

//    private void guardarBBDD(){
//
//        ActividadErrota actividadErrota = DatabaseRepository.getAppDatabase().getErrotaDao().getErrota(idActividad);
//
//        actividadErrota.setFragment(2);
//
//        DatabaseRepository.getAppDatabase().getErrotaDao().updateErrota(actividadErrota);
//
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ((MapActivity)getActivity()).cambiarLocalizacion();
    }

}
