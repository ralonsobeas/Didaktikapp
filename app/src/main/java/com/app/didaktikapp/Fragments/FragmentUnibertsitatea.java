package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.didaktikapp.R;

import in.codeshuffle.typewriterview.TypeWriterView;


public class FragmentUnibertsitatea extends Fragment {


    private View view;

    private LinearLayout textoBreveLayout, preguntasLayout, fotosLayout;

    private Button btnContinuar, btnCorregir;

    private RadioGroup grupo1, grupo2;

    private RadioButton radio1S, radio1N, radio2S, radio2N;


    public FragmentUnibertsitatea() {

    }


    public static FragmentUnibertsitatea newInstance() {
        FragmentUnibertsitatea fragment = new FragmentUnibertsitatea();
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
        view = inflater.inflate(R.layout.fragment_unibertsitatea, container, false);
        textoBreveLayout = view.findViewById(R.id.uniTextoBreveLayout);
        textoBreveLayout.setVisibility(View.VISIBLE);
        //Create Object and refer to layout view
        TypeWriterView typeWriterView=(TypeWriterView)view.findViewById(R.id.uniTextoBreve);

        //Setting each character animation delay
        typeWriterView.setDelay(10);

        //Setting music effect On/Off
        typeWriterView.setWithMusic(true);

        //Animating Text
        typeWriterView.animateText(getString(R.string.TextoUniversidad));




        preguntasLayout = view.findViewById(R.id.uniPreguntasLayout);
        preguntasLayout.setVisibility(View.INVISIBLE);
        fotosLayout = view.findViewById(R.id.uniFotosLayout);
        fotosLayout.setVisibility(View.INVISIBLE);
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

//        ColorStateList defaultStateList = new ColorStateList(
//                new int[][]{
//                        new int[]{android.R.attr.state_enabled},
//                        new int[]{-android.R.attr.state_enabled},
//                        new int[]{android.R.attr.state_focused},
//                        new int[]{android.R.attr.state_pressed}});

//        ColorStateList defaultStateList = new ColorStateList(
//                new int[][]{
//                        new int[]{android.R.attr.state_enabled},
//                        new int[]{-android.R.attr.state_enabled},
//                        new int[]{android.R.attr.state_focused},
//                        new int[]{android.R.attr.state_pressed}
//
//
//                },
//                new int[]{Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE}
//        );
//        radio1S.setButtonTintList(defaultStateList);
//        radio1N.setButtonTintList(defaultStateList);
//        radio2S.setButtonTintList(defaultStateList);
//        radio2N.setButtonTintList(defaultStateList);

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
        btnContinuar.setText("CONTINUAR");
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(textoBreveLayout.getVisibility() == View.VISIBLE){
                    textoBreveLayout.setVisibility(View.INVISIBLE);
                    preguntasLayout.setVisibility(View.VISIBLE);
                    btnCorregir.setEnabled(false);
                    btnContinuar.setEnabled(false);
                }else if(preguntasLayout.getVisibility() == View.VISIBLE){
                    preguntasLayout.setVisibility(View.INVISIBLE);
                    fotosLayout.setVisibility(View.VISIBLE);
                    btnContinuar.setText("FINALIZAR");
                }else if(fotosLayout.getVisibility() == View.VISIBLE){
                    getFragmentManager().beginTransaction().remove(FragmentUnibertsitatea.this).commit();
                }

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
