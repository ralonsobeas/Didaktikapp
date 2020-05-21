package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.ClassToFtp;
import com.app.didaktikapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;

/**
 * Fragmento UnibertsitateaPreguntas, donde se hacen preguntas sobre la universidad tras haber
 * dado información al usuario en el fragmento anterior. Guarda las respuestas y su
 * estado en la base de datos
 * @author gennakk
 */
public class FragmentUnibertsitateaPreguntas extends Fragment {


    private View view;

    private LinearLayout preguntasLayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Long idActividad;
    private String mParam2;

    private Button btnContinuar, btnCorregir;

    private RadioGroup grupo1, grupo2;

    private RadioButton radio1S, radio1N, radio2S, radio2N;

    private int correctas;


    public FragmentUnibertsitateaPreguntas() {

    }


    public static FragmentUnibertsitateaPreguntas newInstance(Long param1) {
        FragmentUnibertsitateaPreguntas fragment = new FragmentUnibertsitateaPreguntas();
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_unibertsitatea_preguntas, container, false);
        preguntasLayout = view.findViewById(R.id.uniPreguntasLayout);
        preguntasLayout.setVisibility(View.VISIBLE);


        btnCorregir = view.findViewById(R.id.btnCorregir);
        grupo1 = view.findViewById(R.id.grupoPregunta1);
        grupo2 = view.findViewById(R.id.grupoPregunta2);
        radio1S = view.findViewById(R.id.radioPregunta1Si);
        radio1S.setTextColor(Color.BLACK);
        radio1N = view.findViewById(R.id.radioPregunta1No);
        radio1N.setTextColor(Color.BLACK);
        radio2S = view.findViewById(R.id.radioPregunta2Si);
        radio2S.setTextColor(Color.BLACK);
        radio2N = view.findViewById(R.id.radioPregunta2No);
        radio2N.setTextColor(Color.BLACK);

        grupo1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(grupo1.getCheckedRadioButtonId() != -1 && grupo2.getCheckedRadioButtonId() != -1){
                    btnCorregir.setEnabled(true);
                }


            }
        });

        grupo2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(grupo1.getCheckedRadioButtonId() != -1 && grupo2.getCheckedRadioButtonId() != -1){
                    btnCorregir.setEnabled(true);
                }


            }
        });

        btnCorregir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                correctas = 0;
                if(grupo1.getCheckedRadioButtonId() == radio1N.getId()){
                    correctas++;
                    radio1N.setTextColor(Color.GREEN);
                }else{
                    radio1S.setTextColor(Color.RED);
                }

                if(grupo2.getCheckedRadioButtonId() == radio2N.getId()){
                    correctas++;
                    radio2N.setTextColor(Color.GREEN);
                }else{
                    radio2S.setTextColor(Color.RED);
                }

                radio1S.setEnabled(false);
                radio1N.setEnabled(false);
                radio2S.setEnabled(false);
                radio2N.setEnabled(false);
                btnContinuar.setEnabled(true);
                btnCorregir.setEnabled(false);

            }
        });

        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText(R.string.Continuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ACTUALIZAR BBDD
                guardarBBDD();
                //Cerrar fragment y abrir el siguiente
                FragmentUnibertsitateaFotos fragmentUnibertsitateaFotos = FragmentUnibertsitateaFotos.newInstance(idActividad);
                FragmentTransaction transactionSanMiguel = getActivity().getSupportFragmentManager().beginTransaction();
                transactionSanMiguel.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                transactionSanMiguel.replace(R.id.fragment_frame, fragmentUnibertsitateaFotos);
                transactionSanMiguel.commit();
                transactionSanMiguel.addToBackStack("Fragment");

            }
        });

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
                                .addSpotlight(view.findViewById(R.id.helpButton), getString(R.string.AyudaUniversidadTituloPregunta), getString(R.string.AyudaUniversidadDetallePregunta), "preguntaUP" + rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.btnCorregir),  getString(R.string.AyudaUniversidadTituloRespuesta),  getString(R.string.AyudaUniversidadDetalleRespuesta), "respuestaUP" + rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.btnContinuar), getString(R.string.AyudaZumTituloContinuar), getString(R.string.AyudaZumDetalleContinuar), "continuarUP" + rndGenerator.nextInt(999999999))
                                .startSequence();
                    }
                },0);
            }
        });



        return view;
    }

    private void guardarBBDD(){
        ActividadUniversitatea actividadUniversitatea = DatabaseRepository.getAppDatabase().getUniversitateaDao().getUniversitatea(idActividad);

        actividadUniversitatea.setFragment(2);

        actividadUniversitatea.setTest(correctas+"/2");

        DatabaseRepository.getAppDatabase().getUniversitateaDao().updateUniversitatea(actividadUniversitatea);
        ClassToFtp.send(getActivity(),ClassToFtp.TIPO_UNIVERSITATEA);

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
