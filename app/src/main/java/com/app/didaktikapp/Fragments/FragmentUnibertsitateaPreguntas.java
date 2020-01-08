package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.app.didaktikapp.R;


public class FragmentUnibertsitateaPreguntas extends Fragment {


    private View view;

    private LinearLayout preguntasLayout;

    private Button btnContinuar, btnCorregir;

    private RadioGroup grupo1, grupo2;

    private RadioButton radio1S, radio1N, radio2S, radio2N;


    public FragmentUnibertsitateaPreguntas() {

    }


    public static FragmentUnibertsitateaPreguntas newInstance() {
        FragmentUnibertsitateaPreguntas fragment = new FragmentUnibertsitateaPreguntas();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                if(grupo1.getCheckedRadioButtonId() == radio1N.getId()){
                    radio1N.setTextColor(Color.GREEN);
                }else{
                    radio1S.setTextColor(Color.RED);
                }

                if(grupo2.getCheckedRadioButtonId() == radio2N.getId()){
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
                //CAMBIAR DE FRAGMENT
                getFragmentManager().beginTransaction().remove(FragmentUnibertsitateaPreguntas.this).commit();

            }
        });
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



}
