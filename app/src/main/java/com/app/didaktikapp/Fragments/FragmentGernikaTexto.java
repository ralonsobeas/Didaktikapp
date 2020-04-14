package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.didaktikapp.BBDD.Modelos.ActividadGernika;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.ClassToFtp;
import com.app.didaktikapp.R;

import in.codeshuffle.typewriterview.TypeWriterView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentGernikaTexto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentGernikaTexto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGernikaTexto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View view;

    private Long idActividad;

    private LinearLayout textoBreveLayout;

    private Button btnContinuar;

    public FragmentGernikaTexto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FragmentGernikaTexto.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGernikaTexto newInstance(Long param1) {
        FragmentGernikaTexto fragment = new FragmentGernikaTexto();
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
        view = inflater.inflate(R.layout.fragment_gernika_texto, container, false);
        textoBreveLayout = view.findViewById(R.id.gernikaTextoBreveLayout);
        textoBreveLayout.setVisibility(View.VISIBLE);
        //Create Object and refer to layout view
        TypeWriterView typeWriterView= view.findViewById(R.id.gernikaTextoBreve);

        //Setting each character animation delay
        typeWriterView.setDelay(10);

        //Setting music effect On/Off
        typeWriterView.setWithMusic(false);

        //Animating Text
        typeWriterView.animateText(getString(R.string.GernikaTexto));

        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ACTUALIZAR BBDDç
                guardarBBDD();

                //CAMBIAR DE FRAGMENT
                getFragmentManager().beginTransaction().remove(FragmentGernikaTexto.this).commit();


                //Lanzar siguiente fragment
                FragmentGernikaPoema fragment = FragmentGernikaPoema.newInstance(idActividad);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                transaction.replace(R.id.fragment_frame, fragment);
                transaction.commit();
                transaction.addToBackStack("Fragment");

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

        actividadGernika.setEstado(1);

        actividadGernika.setFragment(1);

        DatabaseRepository.getAppDatabase().getGernikaDao().updateGernika(actividadGernika);
        ClassToFtp.send(getActivity(),ClassToFtp.TIPO_GERNIKA);

    }


}
