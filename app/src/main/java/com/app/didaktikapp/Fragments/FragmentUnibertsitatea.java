package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.didaktikapp.R;


public class FragmentUnibertsitatea extends Fragment {


    private View view;

    private LinearLayout textoBreveLayout, preguntasLayout, fotosLayout;

    private Button btnContinuar;


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
        preguntasLayout = view.findViewById(R.id.uniPreguntasLayout);
        preguntasLayout.setVisibility(View.INVISIBLE);
        fotosLayout = view.findViewById(R.id.uniFotosLayout);
        fotosLayout.setVisibility(View.INVISIBLE);

        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText("CONTINUAR");
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(textoBreveLayout.getVisibility() == View.VISIBLE){
                    textoBreveLayout.setVisibility(View.INVISIBLE);
                    preguntasLayout.setVisibility(View.VISIBLE);
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
