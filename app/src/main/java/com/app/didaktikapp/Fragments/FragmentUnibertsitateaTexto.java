package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;

import com.app.didaktikapp.R;

import in.codeshuffle.typewriterview.TypeWriterView;


public class FragmentUnibertsitateaTexto extends Fragment {


    private View view;

    private LinearLayout textoBreveLayout;

    private Button btnContinuar;


    public FragmentUnibertsitateaTexto() {

    }


    public static FragmentUnibertsitateaTexto newInstance() {
        FragmentUnibertsitateaTexto fragment = new FragmentUnibertsitateaTexto();
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
        view = inflater.inflate(R.layout.fragment_unibertsitatea_texto, container, false);
        textoBreveLayout = view.findViewById(R.id.uniTextoBreveLayout);
        textoBreveLayout.setVisibility(View.VISIBLE);
        //Create Object and refer to layout view
        TypeWriterView typeWriterView=(TypeWriterView)view.findViewById(R.id.uniTextoBreve);

        //Setting each character animation delay
        typeWriterView.setDelay(10);

        //Setting music effect On/Off
        typeWriterView.setWithMusic(false);

        //Animating Text
        typeWriterView.animateText(getString(R.string.TextoUniversidad));

        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText(R.string.Continuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ACTUALIZAR BBDD
                //CAMBIAR DE FRAGMENT
                getFragmentManager().beginTransaction().remove(FragmentUnibertsitateaTexto.this).commit();


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
