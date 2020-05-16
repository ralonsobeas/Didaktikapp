package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.ClassToFtp;
import com.app.didaktikapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;

/**
 * Fragmento de video, que se llama desde varios sitios de la aplicación pasandole el video
 * que debe reproducir. Dependiendo desde donde se llame, guarda información en la base de datos
 * @author gennakk
 */
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

    public FragmentVideo() {
    }


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
        TextView titulo = view.findViewById(R.id.errotaTitulo);

        switch (fragment) {
            case "errota":
                titulo.setText(R.string.nombreLugar5);
                rawvideo = R.raw.video_sanmiguel_errota;
                break;
            case "parrokia":
                titulo.setText(R.string.nombreLugar3);
                rawvideo = R.raw.video_sanmiguel_parrokia;
                break;
            case "zumeltzegi":
                titulo.setText(R.string.nombreLugar1);
//                rawvideo = R.raw.video_zumeltzegi;
                break;
            default:
                Log.e("VIDEO", "Nombre '" + fragment + "' no reconocido");
        }

          /*
        Botón flotante de ayuda
         */
        FloatingActionButton floatingActionButton = view.findViewById(R.id.helpButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewBoton) {

                SpotlightConfig config = new SpotlightConfig();
                config.setMaskColor(Color.parseColor("#E63A3A3A"));
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
                config.setLineAndArcColor(Color.parseColor("#2B82C5"));
                config.setShowTargetArc(true);


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        SpotlightSequence.getInstance(getActivity(), config)
                                .addSpotlight(view.findViewById(R.id.helpButton), getString(R.string.AyudaVideoTitulo), getString(R.string.AyudaVideoDetalle), "video")
                                .addSpotlight(view.findViewById(R.id.btnContinuar), getString(R.string.AyudaZumTituloContinuar), getString(R.string.AyudaZumDetalleContinuar), "continuar")
                                .startSequence();
                    }
                }, 0);
            }
        });




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
                        ClassToFtp.send(getActivity(),ClassToFtp.TIPO_ERROTA);

                        //Cerrar fragment y abrir el siguiente
                        FragmentErrotaFotos fragment = FragmentErrotaFotos.newInstance(idActividad);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                        transaction.replace(R.id.fragment_frame, fragment);
                        transaction.commit();
                        transaction.addToBackStack("Fragment");
                        break;
                    case "parrokia":
                        //Cerrar fragment y abrir el siguiente
                        FragmentSanMiguel fragmentSanMiguel = FragmentSanMiguel.newInstance(idActividad);
                        FragmentTransaction transactionSanMiguel = getActivity().getSupportFragmentManager().beginTransaction();
                        transactionSanMiguel.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                        transactionSanMiguel.replace(R.id.fragment_frame, fragmentSanMiguel);
                        transactionSanMiguel.commit();
                        transactionSanMiguel.addToBackStack("Fragment");
                        break;
                    case "zumeltzegi":
                        //Cerrar fragment y abrir el siguiente
                        FragmentZumeltzegi fragmentZumeltzegi = FragmentZumeltzegi.newInstance(idActividad);
                        FragmentTransaction transactionZumeltzegi = getActivity().getSupportFragmentManager().beginTransaction();
                        transactionZumeltzegi.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                        transactionZumeltzegi.replace(R.id.fragment_frame, fragmentZumeltzegi);
                        transactionZumeltzegi.commit();
                        transactionZumeltzegi.addToBackStack("Fragment");

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
