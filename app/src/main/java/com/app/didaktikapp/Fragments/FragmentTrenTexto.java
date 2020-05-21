package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.ActividadTren;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.ClassToFtp;
import com.app.didaktikapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentTrenTexto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentTrenTexto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTrenTexto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Long idActividad;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View view;

    private LinearLayout textoLayout;

    private Spinner sp;

    private TextView textoCambiar,textoPregunta,textoError;

    boolean primerError;

    private Button btnContinuar;

    public FragmentTrenTexto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param idActividad Parameter 1.
     * @return A new instance of fragment FragmentTrenTexto.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTrenTexto newInstance(Long idActividad) {
        FragmentTrenTexto fragment = new FragmentTrenTexto();
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
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tren_texto, container, false);

        textoLayout = view.findViewById(R.id.trenTextoLayout);
        textoLayout.setVisibility(View.VISIBLE);

        textoCambiar = view.findViewById(R.id.trenTextoCambiar);
        textoPregunta = view.findViewById(R.id.trenTextoPregunta);
        textoError = view.findViewById(R.id.trenTextoError);

        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setEnabled(false);

        btnContinuar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ActividadTren actividadTren = DatabaseRepository.getAppDatabase().getTrenDao().getTren(idActividad);

                actividadTren.setEstado(2);

                actividadTren.setFragment(2);

                DatabaseRepository.getAppDatabase().getTrenDao().updateTren(actividadTren);
                ClassToFtp.send(getActivity(),ClassToFtp.TIPO_TREN);
                //CERRAR FRAGMENT
                getFragmentManager().beginTransaction().remove(FragmentTrenTexto.this).commit();
            }
        });

        //Al iniciar la actividad detecta que ha habido un click en el spinner,
        //asi que este booleano evita que salga el mensaje de error la primera vez
        primerError = false;

        sp = view.findViewById(R.id.trenTextoSpinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String seleccionado = sp.getSelectedItem().toString();
                String pregunta = textoPregunta.getText().toString();
                String texto = textoCambiar.getText().toString();

                if (pregunta.equals("(1) ->")) {
                    if (seleccionado.equals(getResources().getStringArray(R.array.TrenTextoCambiar)[1])) {
                        textoError.setText("");
                        textoPregunta.setText("(2) ->");
                        int location = texto.indexOf("________");
                        String pp = texto.substring(0,location);
                        String sp = texto.substring(location+8);
                        textoCambiar.setText(pp+getString(R.string.correos)+sp);
                    } else {
                        if (primerError)
                            textoError.setText(getResources().getString(R.string.TrenTextoError)+" "+seleccionado);
                        else
                            primerError = true;
                    }

                } else if (pregunta.equals("(2) ->")) {
                    if (seleccionado.equals(getResources().getStringArray(R.array.TrenTextoCambiar)[2])) {
                        textoError.setText("");
                        textoPregunta.setText("(3) ->");
                        int location = texto.indexOf("________");
                        String pp = texto.substring(0,location);
                        String sp = texto.substring(location+8);
                        textoCambiar.setText(pp+getString(R.string.vasconavarra)+sp);
                    } else {
                        textoError.setText(getResources().getString(R.string.TrenTextoError)+" "+seleccionado);
                    }

                } else if (pregunta.equals("(3) ->")) {
                    if (seleccionado.equals(getResources().getStringArray(R.array.TrenTextoCambiar)[0])) {
                        textoError.setText("");
                        int location = texto.indexOf("________");
                        String pp = texto.substring(0,location);
                        String sp = texto.substring(location+8);
                        textoCambiar.setText(pp+getString(R.string.vitoria)+sp);
                        btnContinuar.setEnabled(true);
                    } else {
                        textoError.setText(getResources().getString(R.string.TrenTextoError)+" "+seleccionado);
                    }

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                textoError.setText("");

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
                                .addSpotlight(view.findViewById(R.id.trenTextoSpinner), getString(R.string.AyudaTrenTitulo), getString(R.string.AyudaTrenDetalle), "preguntaTT" + rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.btnContinuar), getString(R.string.AyudaZumTituloContinuar), getString(R.string.AyudaZumDetalleContinuar), "continuarTT" + rndGenerator.nextInt(999999999))
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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

    private void palabrasClickables(View view){
        TextView tvc = view.findViewById(R.id.trenTextoCambiar);
        TextView tvp = view.findViewById(R.id.trenTextoPregunta);
        Spinner sp = view.findViewById(R.id.trenTextoSpinner);

        String texto = tvc.getText().toString();
        int introducido = sp.getSelectedItemPosition();
        //"Esta es la ________, esta la ________ y esta la ________"
        //(todos son 8 _)

        switch (introducido) {
            case 0:
                Log.i("tag","0");
//                if (!prueba1) {
//                    int location = texto.indexOf("________");
//                    String pp = texto.substring(0,location);
//                    String sp = texto.substring(location+8);
//                    tv.setText(pp+"prueba 1"+sp);
//                    prueba1 = true;
//                }
                break;
            case 1:
                Log.i("tag","1");
//                if (!prueba2) {
//                    int location = texto.indexOf("________");
//                    if (!prueba1)
//                        location = texto.indexOf("________",location+9);
//                    String pp = texto.substring(0,location);
//                    String sp = texto.substring(location+8);
//                    tv.setText(pp+"prueba 2"+sp);
//                    prueba2 = true;
//                }
                break;
            case 2:
//                if (!prueba3) {
//                    int location = texto.indexOf("________");
//                    if (!prueba1)
//                        location = texto.indexOf("________",location+9);
//                    if (!prueba2)
//                        location = texto.indexOf("________",location+9);
//                    String pp = texto.substring(0,location);
//                    String sp = texto.substring(location+8);
//                    tv.setText(pp+"prueba 3"+sp);
//                    prueba3 = true;
//                }
                break;
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ((MapActivity)getActivity()).cambiarLocalizacion();
    }
}
