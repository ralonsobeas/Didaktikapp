package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.didaktikapp.BBDD.Modelos.ActividadGernika;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.ClassToFtp;
import com.app.didaktikapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;

import in.codeshuffle.typewriterview.TypeWriterView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentGernikaPreguntas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentGernikaPreguntas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGernikaPreguntas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentGernikaTexto.OnFragmentInteractionListener mListener;

    private Long idActividad;

    private View view;

    private Button btnContinuar, btnCorregir;

    private RadioGroup grupoPregunta1, grupoPregunta2, grupoPregunta3;

    private RadioButton radioPregunta1OpcionA,
                        radioPregunta2OpcionC,
                        radioPregunta3OpcionB;

    private int correctas;



    public FragmentGernikaPreguntas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FragmentGernikaPreguntas.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGernikaPreguntas newInstance(Long param1) {
        FragmentGernikaPreguntas fragment = new FragmentGernikaPreguntas();
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
        view = inflater.inflate(R.layout.fragment_gernika_preguntas, container, false);

        btnCorregir = view.findViewById(R.id.btnCorregir);

        grupoPregunta1 = view.findViewById(R.id.grupoPregunta1);
        grupoPregunta2 = view.findViewById(R.id.grupoPregunta2);
        grupoPregunta3 = view.findViewById(R.id.grupoPregunta3);

        radioPregunta1OpcionA = view.findViewById(R.id.GernikaPregunta1OpcionA);
        radioPregunta2OpcionC = view.findViewById(R.id.GernikaPregunta2OpcionC);
        radioPregunta3OpcionB = view.findViewById(R.id.GernikaPregunta3OpcionB);

        grupoPregunta1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(grupoPregunta1.getCheckedRadioButtonId() != -1 && grupoPregunta2.getCheckedRadioButtonId() != -1 && grupoPregunta3.getCheckedRadioButtonId() != -1){
                    btnCorregir.setEnabled(true);
                }


            }
        });

        grupoPregunta2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(grupoPregunta1.getCheckedRadioButtonId() != -1 && grupoPregunta2.getCheckedRadioButtonId() != -1 && grupoPregunta3.getCheckedRadioButtonId() != -1){
                    btnCorregir.setEnabled(true);
                }


            }
        });

        grupoPregunta3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(grupoPregunta1.getCheckedRadioButtonId() != -1 && grupoPregunta2.getCheckedRadioButtonId() != -1 && grupoPregunta3.getCheckedRadioButtonId() != -1){
                    btnCorregir.setEnabled(true);
                }


            }
        });

        btnCorregir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                correctas = 0;

                //variables que almacenan las respuestas
                RadioButton respuestaPregunta1, respuestaPregunta2, respuestaPregunta3;

                //me quedo con el boton seleccionado de cada pregunta
                respuestaPregunta1 = view.findViewById(grupoPregunta1.getCheckedRadioButtonId());
                respuestaPregunta2 = view.findViewById(grupoPregunta2.getCheckedRadioButtonId());
                respuestaPregunta3 = view.findViewById(grupoPregunta3.getCheckedRadioButtonId());

                //comparo con la respuesta correcta
                if(respuestaPregunta1.getId() == radioPregunta1OpcionA.getId()){
                    correctas++;
                    respuestaPregunta1.setTextColor(Color.GREEN);
                }else{
                    respuestaPregunta1.setTextColor(Color.RED);
                }

                //comparo con la respuesta correcta
                if(respuestaPregunta2.getId() == radioPregunta2OpcionC.getId()){
                    correctas++;
                    respuestaPregunta2.setTextColor(Color.GREEN);
                }else{
                    respuestaPregunta2.setTextColor(Color.RED);
                }

                //comparo con la respuesta correcta
                if(respuestaPregunta3.getId() == radioPregunta3OpcionB.getId()){
                    correctas++;
                    respuestaPregunta3.setTextColor(Color.GREEN);
                }else{
                    respuestaPregunta3.setTextColor(Color.RED);
                }

                btnCorregir.setEnabled(false);
                btnContinuar.setEnabled(true);

            }
        });

        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText(R.string.Continuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ACTUALIZAR BBDD
                guardarBBDD();
                //CAMBIAR DE FRAGMENT
                getFragmentManager().beginTransaction().remove(FragmentGernikaPreguntas.this).commit();

                //Lanzar siguiente fragment
                FragmentPuzle fragment = FragmentPuzle.newInstance(idActividad, R.drawable.gernika);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                transaction.replace(R.id.fragment_frame, fragment);
                transaction.commit();
                transaction.addToBackStack("Fragment");

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
                                .addSpotlight(view.findViewById(R.id.helpButton), getString(R.string.AyudaGernikaTituloPregunta), getString(R.string.AyudaGernikaDetallePregunta), "preguntaGP"+ rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.btnCorregir),  getString(R.string.AyudaGernikaTituloRespuesta),  getString(R.string.AyudaGernikaDetalleRespuesta), "respuestaGP"+ rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.btnContinuar), getString(R.string.AyudaZumTituloContinuar), getString(R.string.AyudaZumDetalleContinuar), "continuarGP"+ rndGenerator.nextInt(999999999))
                                .startSequence();
                    }
                },0);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void guardarBBDD(){

        ActividadGernika actividadGernika = DatabaseRepository.getAppDatabase().getGernikaDao().getGernika(idActividad);

        actividadGernika.setEstado(2);

        actividadGernika.setFragment(3);

        //CAMBIAR A CORRECTAS
        actividadGernika.setTest(correctas+"/3");

        DatabaseRepository.getAppDatabase().getGernikaDao().updateGernika(actividadGernika);
        ClassToFtp.send(getActivity(),ClassToFtp.TIPO_GERNIKA);

    }
}
