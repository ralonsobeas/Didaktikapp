package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.ClassToFtp;
import com.app.didaktikapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSanMiguel.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSanMiguel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSanMiguel extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "FragmentSanMiguel";

    private FragmentSanMiguel.OnFragmentInteractionListener mListener;

    private Long idActividad;

    private View view;

    private Button btnContinuar, btnCorregir;

    private RadioGroup grupoPregunta1, grupoPregunta2, grupoPregunta3, grupoPregunta4;

    private RadioButton radioPregunta1OpcionC,
                        radioPregunta2OpcionB,
                        radioPregunta3OpcionC,
                        radioPregunta4OpcionB;

    private LinearLayout layout1, layout2, layout3, layout4;

    private int correctas = 0;

    public FragmentSanMiguel() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentSanMiguel.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSanMiguel newInstance(Long idActividad) {
        FragmentSanMiguel fragment = new FragmentSanMiguel();
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
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_san_miguel, container, false);

        btnCorregir = view.findViewById(R.id.btnCorregir);
        btnContinuar = view.findViewById(R.id.btnContinuar);

        grupoPregunta1 = view.findViewById(R.id.rgroupPregunta1);
        grupoPregunta2 = view.findViewById(R.id.rgroupPregunta2);
        grupoPregunta3 = view.findViewById(R.id.rgroupPregunta3);
        grupoPregunta4 = view.findViewById(R.id.rgroupPregunta4);

        radioPregunta1OpcionC = view.findViewById(R.id.rbtnPregunta1C);
        radioPregunta2OpcionB = view.findViewById(R.id.rbtnPregunta2B);
        radioPregunta3OpcionC = view.findViewById(R.id.rbtnPregunta3C);
        radioPregunta4OpcionB = view.findViewById(R.id.rbtnPregunta4B);

        layout1 = view.findViewById(R.id.layoutPregunta1);
        layout2 = view.findViewById(R.id.layoutPregunta2);
        layout3 = view.findViewById(R.id.layoutPregunta3);
        layout4 = view.findViewById(R.id.layoutPregunta4);

        grupoPregunta1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId != -1 ){
                    btnCorregir.setEnabled(true);
                }

            }
        });

        grupoPregunta2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId != -1 ){
                    btnCorregir.setEnabled(true);
                }

            }
        });

        grupoPregunta3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId != -1 ){
                    btnCorregir.setEnabled(true);
                }

            }
        });

        grupoPregunta4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId != -1 ){
                    btnCorregir.setEnabled(true);
                }

            }
        });

        btnCorregir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(layout1.getVisibility() == View.VISIBLE){

                    RadioButton respuesta = view.findViewById(grupoPregunta1.getCheckedRadioButtonId());
                    if(respuesta.getId() == radioPregunta1OpcionC.getId()){
                        correctas++;
                        respuesta.setTextColor(Color.GREEN);
                    }else{
                        respuesta.setTextColor(Color.RED);
                    }

                }else if(layout2.getVisibility() == View.VISIBLE){

                    RadioButton respuesta = view.findViewById(grupoPregunta2.getCheckedRadioButtonId());
                    if(respuesta.getId() == radioPregunta2OpcionB.getId()){
                        correctas++;
                        respuesta.setTextColor(Color.GREEN);
                    }else{
                        respuesta.setTextColor(Color.RED);
                    }

                }else if(layout3.getVisibility() == View.VISIBLE){

                    RadioButton respuesta = view.findViewById(grupoPregunta3.getCheckedRadioButtonId());
                    if(respuesta.getId() == radioPregunta3OpcionC.getId()){
                        correctas++;
                        respuesta.setTextColor(Color.GREEN);
                    }else{
                        respuesta.setTextColor(Color.RED);
                    }

                }else if(layout4.getVisibility() == View.VISIBLE){

                    RadioButton respuesta = view.findViewById(grupoPregunta4.getCheckedRadioButtonId());
                    if(respuesta.getId() == radioPregunta4OpcionB.getId()){
                        correctas++;
                        respuesta.setTextColor(Color.GREEN);
                    }else{
                        respuesta.setTextColor(Color.RED);
                    }

                }

                btnCorregir.setEnabled(false);
                btnContinuar.setEnabled(true);

            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(layout1.getVisibility() == View.VISIBLE){

                    layout1.setVisibility(View.INVISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    btnContinuar.setEnabled(false);

                }else if(layout2.getVisibility() == View.VISIBLE){

                    layout2.setVisibility(View.INVISIBLE);
                    layout3.setVisibility(View.VISIBLE);
                    btnContinuar.setEnabled(false);

                }else if(layout3.getVisibility() == View.VISIBLE){

                    layout3.setVisibility(View.INVISIBLE);
                    layout4.setVisibility(View.VISIBLE);
                    btnContinuar.setEnabled(false);


                }else if(layout4.getVisibility() == View.VISIBLE){

                    //Guardar en BBDD
                    guardarBBDD();

                    //Lanzar siguiente fragment
                    FragmentSanMiguelTinderKotlin fragment = FragmentSanMiguelTinderKotlin.newInstance(idActividad);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                    transaction.replace(R.id.fragment_frame, fragment);
                    transaction.commit();
                    transaction.addToBackStack("Fragment");

                }

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
                                .addSpotlight(view.findViewById(R.id.helpButton), getString(R.string.AyudaSanMiguelTituloPregunta), getString(R.string.AyudaSanMiguelDetallePregunta), "preguntaSM" + rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.btnCorregir),  getString(R.string.AyudaSanMiguelTituloRespuesta),  getString(R.string.AyudaSanMiguelDetalleRespuesta), "respuestaSM" + rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.btnContinuar), getString(R.string.AyudaZumTituloContinuar), getString(R.string.AyudaZumDetalleContinuar), "continuarSM" + rndGenerator.nextInt(999999999))
                                .startSequence();
                    }
                },0);
            }
        });



        return view;
    }











    private void guardarBBDD(){

        ActividadSanMiguel actividadSanMiguel = DatabaseRepository.getAppDatabase().getSanMiguelDao().getSanMiguel(idActividad);

        actividadSanMiguel.setEstado(1);

        actividadSanMiguel.setFragment(1);

        actividadSanMiguel.setTest(correctas+"/4");

        DatabaseRepository.getAppDatabase().getSanMiguelDao().updateSanMiguel(actividadSanMiguel);
        ClassToFtp.send(getActivity(),ClassToFtp.TIPO_SANMIGUEL);

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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();

        ((MapActivity)getActivity()).cambiarLocalizacion();
    }
}
