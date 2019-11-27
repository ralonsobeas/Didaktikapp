package com.app.didaktikapp.Fragments;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.app.didaktikapp.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class FragmentErrota extends Fragment {


    private View view;

    private LinearLayout fotosLayout;

    private Button btnContinuar;

    private ImageView img1_1,img1_2,img1_3,img1_4,img2_1,img2_2,img2_3,img2_4,img3_1,img3_2,anterior;

    private String imgSelec;

    private boolean sela1,sela2,selb1,selb2,selc1,selc2,seld1,seld2,sele1,sele2 = false;


    public FragmentErrota() {

    }


    public static FragmentErrota newInstance() {
        FragmentErrota fragment = new FragmentErrota();
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
        view = inflater.inflate(R.layout.fragment_errota, container, false);
        fotosLayout = view.findViewById(R.id.errotaUnirImagenes);
        fotosLayout.setVisibility(View.VISIBLE);

        crearImagenes(view);

        btnContinuar = view.findViewById(R.id.btnContinuar);
        btnContinuar.setText(getResources().getString(R.string.Contunuar));
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(textoBreveLayout.getVisibility() == View.VISIBLE){
//                    textoBreveLayout.setVisibility(View.INVISIBLE);
//                    preguntasLayout.setVisibility(View.VISIBLE);
//                    btnCorregir.setEnabled(false);
//                    btnContinuar.setEnabled(false);
//                }else if(preguntasLayout.getVisibility() == View.VISIBLE){
//                    preguntasLayout.setVisibility(View.INVISIBLE);
//                    fotosLayout.setVisibility(View.VISIBLE);
//                    btnContinuar.setText("FINALIZAR");
//                }else if(fotosLayout.getVisibility() == View.VISIBLE){
//                    getFragmentManager().beginTransaction().remove(FragmentErrota.this).commit();
//                }

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

    public void crearImagenes(View view) {
        img1_1 = view.findViewById(R.id.errotaImagen1_1);
        img1_2 = view.findViewById(R.id.errotaImagen1_2);
        img1_3 = view.findViewById(R.id.errotaImagen1_3);
        img1_4 = view.findViewById(R.id.errotaImagen1_4);
        img2_1 = view.findViewById(R.id.errotaImagen2_1);
        img2_2 = view.findViewById(R.id.errotaImagen2_2);
        img2_3 = view.findViewById(R.id.errotaImagen2_3);
        img2_4 = view.findViewById(R.id.errotaImagen2_4);
        img3_1 = view.findViewById(R.id.errotaImagen3_2);
        img3_2 = view.findViewById(R.id.errotaImagen3_3);
        sela1 = false;
        sela2 = false;
        selb1 = false;
        selb2 = false;
        selc1 = false;
        selc2 = false;
        seld1 = false;
        seld2 = false;
        sele1 = false;
        sele2 = false;
        imgSelec = "";

        ImageView[] iva = new ImageView[] {img1_1,img1_2,img1_3,img1_4,img2_1,img2_2,img2_3,img2_4,img3_1,img3_2};
        List<ImageView> list = Arrays.asList(iva);
        Collections.shuffle(list);
        for (int x=0;x<list.size();x++) {
            int img = R.drawable.errotaa1;
            switch (x) {
                case 0:
                    img = R.drawable.errotaa1;
                    int finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (sela1==false) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "a1";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("a2")) {
                                        sela1 = true;
                                        sela2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 1:
                    img = R.drawable.errotaa2;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (sela2==false) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "a2";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("a1")) {
                                        sela1 = true;
                                        sela2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 2:
                    img = R.drawable.errotab1;
                    finalX = x;
                    list.get(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (selb1==false) {
                                if (imgSelec.equals("")) {
                                    imgSelec = "b1";
                                    anterior = list.get(finalX);
                                    list.get(finalX).setColorFilter(R.color.naranja);
                                }
                                else {
                                    if (imgSelec.equals("b2")) {
                                        selb1 = true;
                                        selb2 = true;
                                        list.get(finalX).setColorFilter(R.color.verde);
                                        anterior.setColorFilter(R.color.verde);
                                    } else {
                                        anterior.setColorFilter(null);
                                    }
                                    imgSelec = "";
                                }
                            }
                        }
                    });
                    break;
                case 3:
                    img = R.drawable.errotab2;
                    break;
                case 4:
                    img = R.drawable.errotac1;
                    break;
                case 5:
                    img = R.drawable.errotac2;
                    break;
                case 6:
                    img = R.drawable.errotad1;
                    break;
                case 7:
                    img = R.drawable.errotad2;
                    break;
                case 8:
                    img = R.drawable.errotae1;
                    break;
                case 9:
                    img = R.drawable.errotae2;
                    break;
            }
            list.get(x).setImageResource(img);
        }

    }

}
