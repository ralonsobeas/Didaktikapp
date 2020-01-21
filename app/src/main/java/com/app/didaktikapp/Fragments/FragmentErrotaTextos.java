package com.app.didaktikapp.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.app.didaktikapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                //cerrar fragment
                //abrir siguiente (video)

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

}
