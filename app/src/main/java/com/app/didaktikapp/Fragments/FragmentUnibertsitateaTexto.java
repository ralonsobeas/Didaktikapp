package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.R;

import in.codeshuffle.typewriterview.TypeWriterView;


public class FragmentUnibertsitateaTexto extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Long idActividad;
    private String mParam2;


    private View view;

    private LinearLayout textoBreveLayout;

    private Button btnContinuar;


    public FragmentUnibertsitateaTexto() {

    }


    public static FragmentUnibertsitateaTexto newInstance(Long param1) {
        FragmentUnibertsitateaTexto fragment = new FragmentUnibertsitateaTexto();
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
        typeWriterView.animateText(getString(R.string.UniversidadTexto));

        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText(R.string.Continuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ACTUALIZAR BBDD
                guardarBBDD();
                //CAMBIAR DE FRAGMENT
                getFragmentManager().beginTransaction().remove(FragmentUnibertsitateaTexto.this).commit();


            }
        });
        return view;
    }

    private void guardarBBDD(){
        ActividadUniversitatea actividadUniversitatea = DatabaseRepository.getAppDatabase().getUniversitateaDao().getUniversitatea(idActividad);

        actividadUniversitatea.setEstado(1);
        actividadUniversitatea.setFragment(1);

        DatabaseRepository.getAppDatabase().getUniversitateaDao().updateUniversitatea(actividadUniversitatea);

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
