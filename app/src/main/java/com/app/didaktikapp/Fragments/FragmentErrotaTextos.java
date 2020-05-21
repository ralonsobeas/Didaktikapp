package com.app.didaktikapp.Fragments;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.ClassToFtp;
import com.app.didaktikapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Fragmento ErrotaTextos, correspondiente a la actividad de arrastrar palabras a los
 * huecos de un párrafo en San Miguel Errota. Guarda las respuestas en la BD así como su estado
 * @author gennakk
 */
public class FragmentErrotaTextos extends Fragment {

    private View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Long idActividad;

    private Button btnContinuar;


    private TextView option1, option2, option3, option4;
    private EditText choice1, choice2, choice3, choice4;
    private Map<String, String> maprpta;
    public ArrayList<EditText> listaEts;
    public CharSequence dragData;


    public FragmentErrotaTextos() {

    }


    public static FragmentErrotaTextos newInstance(Long param1) {
        FragmentErrotaTextos fragment = new FragmentErrotaTextos();
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
        // OBTENEMOS LA VIEW
        view = inflater.inflate(R.layout.fragment_errota_textos, container, false);

        listaEts = new ArrayList<EditText>();

        //CONFIGURAMOS EL BOTON CONTINUAR
        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText(getResources().getString(R.string.Continuar));
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //actualizar BD
                guardarBBDD();

                //Lanzar siguiente fragment
                FragmentVideo fragment = FragmentVideo.newInstance(idActividad,"errota");
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
                transaction.replace(R.id.fragment_frame, fragment);
                transaction.commit();
                transaction.addToBackStack("Fragment");


            }
        });
        btnContinuar.setEnabled(false);

        //Cargar el mapa
        maprpta = new HashMap<>();
        maprpta.put("choice_1", "erosi");
        maprpta.put("choice_2", "berriak");
        maprpta.put("choice_3", "San Miguel");
        maprpta.put("choice_4", "Berrikuntza");

        //views to drop onto
        choice1 = view.findViewById(R.id.choice_1);
        choice2 = view.findViewById(R.id.choice_2);
        choice3 = view.findViewById(R.id.choice_3);
        choice4 = view.findViewById(R.id.choice_4);

        //views to drag
        option1 = view.findViewById(R.id.option_1);
        option2 = view.findViewById(R.id.option_2);
        option3 = view.findViewById(R.id.option_3);
        option4 = view.findViewById(R.id.option_4);

        //set touch listeners
        option1.setOnTouchListener(new ChoiceTouchListener());
        option2.setOnTouchListener(new ChoiceTouchListener());
        option3.setOnTouchListener(new ChoiceTouchListener());
        option4.setOnTouchListener(new ChoiceTouchListener());

        //set drag listeners
        choice1.setOnDragListener(new ChoiceDragListener());
        choice2.setOnDragListener(new ChoiceDragListener());
        choice3.setOnDragListener(new ChoiceDragListener());
        choice4.setOnDragListener(new ChoiceDragListener());


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
                                .addSpotlight(view.findViewById(R.id.helpButton), getString(R.string.AyudaErrotaTituloTextos), getString(R.string.AyudaErrotaDetalleTextos), "preguntaET" + rndGenerator.nextInt(999999999))
                                .addSpotlight(view.findViewById(R.id.btnContinuar), getString(R.string.AyudaZumTituloContinuar), getString(R.string.AyudaZumDetalleContinuar), "continuarET" + rndGenerator.nextInt(999999999))
                                .startSequence();
                    }
                },0);
            }
        });
        return view;
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    @SuppressLint("NewApi")
    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();

                    EditText spacetofill = (EditText) v;
                    TextView cadenarpta = (TextView) view;

                    String strfill = getResources().getResourceEntryName(spacetofill.getId());
                    //El texto del elemento arrastrado es igual al valor obtenido del mapa, pasandole como parametro el idname del elemento a ser rellenado.

                    if( cadenarpta.getText().toString().equals(maprpta.get(strfill)) )
                    {
                        view.setVisibility(View.INVISIBLE);

                        spacetofill.setText(cadenarpta.getText().toString());
                        spacetofill.setGravity(Gravity.CENTER_HORIZONTAL);
                        spacetofill.setTypeface(Typeface.DEFAULT_BOLD);

                        Object tag = spacetofill.getTag();
                        if(tag!=null)
                        {
                            int existingID = (Integer)tag;
                            view.findViewById(existingID).setVisibility(View.VISIBLE);
                        }

                        spacetofill.setTag(cadenarpta.getId());
                        spacetofill.setOnDragListener(null);
                        spacetofill.setCompoundDrawables(null, null, null, null);

                        listaEts.add(spacetofill);

                    }else{
                        spacetofill.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_update), null);
                    }

                    onComplete();

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    public void onComplete(){
        if(listaEts.size()==4){
            btnContinuar.setEnabled(true);

        }
    }

    private void guardarBBDD(){

        ActividadErrota actividadErrota = DatabaseRepository.getAppDatabase().getErrotaDao().getErrota(idActividad);

        actividadErrota.setEstado(1);

        actividadErrota.setFragment(1);

        //PLACEHOLDER
        actividadErrota.setFrases("5");

        DatabaseRepository.getAppDatabase().getErrotaDao().updateErrota(actividadErrota);
        ClassToFtp.send(getActivity(),ClassToFtp.TIPO_ERROTA);

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ((MapActivity)getActivity()).cambiarLocalizacion();
    }

}
