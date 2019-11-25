package com.app.didaktikapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.didaktikapp.R;

public class Sopa extends AppCompatActivity {

    private MediaPlayer mp, mp2;
    private ImageButton btnNext;
    private TextView respuesta1, respuesta2, respuesta3, respuesta4;
    private boolean blnErrejionalista, blnIngelesa, blnSmith, blnAchurraco, blnEklektikoa, blnSotoa, blnNursery;
    private boolean blnErrejionalistaOK, blnIngelesaOK, blnSmithOK, blnAchurracoOK, blnEklektikoaOK, blnSotoaOK, blnNurseryOK;
    private boolean blnSinValor, blnPulsado, blnPintar;
    private Context cont = this;
    private int pag_anterior;
    static final int REQ_BTN = 0;
    static final int REQ_BTNATRAS = 12;
    private int contEstilosArkitektos = 0;
    private int contArkitektos = 0;

    private ImageView chk1,chk2,chk3_1,chk3_2,chk3_3,chk4_1,chk4_2;

    BroadcastReceiver miBroadcast = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i("TAG", "Screen ON");
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i("TAG", "Screen OFF");
                mp.stop();
                mp2.stop();

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_sopa2);

        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

//        btnNext = findViewById(R.id.btnNext);
//        btnNext.setEnabled(false);
//        btnNext.setVisibility(View.INVISIBLE);
//        mp = MediaPlayer.create(getApplicationContext(), R.raw.a7_1);
//        mp2 = MediaPlayer.create(getApplicationContext(), R.raw.correct);
//        mp.start();

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        switch (pag_anterior){
            case 0:
                break;
            case 1:
                //btnNext.set
                btnNext.setEnabled(true);
                btnNext.setVisibility(View.VISIBLE);
//                btnNext.setOnClickListener(v -> {
//                    mp.stop();
//                    Intent i = new Intent(Hizkisalda_18.this,Informazioakuadroabete_19.class);
//                    i.putExtra("pag_anterior",1);
//                    startActivityForResult(i, REQ_BTN);
//                    finish();
//                });
                break;
        }

        //Booleans a falso
        blnErrejionalista = false;
        blnIngelesa = false;
        blnSmith = false;
        blnAchurraco = false;
        blnEklektikoa = false;
        blnSotoa = false;
        blnNursery = false;

        blnErrejionalistaOK = false;
        blnIngelesaOK = false;
        blnSmithOK = false;
        blnAchurracoOK = false;
        blnEklektikoaOK = false;
        blnSotoaOK = false;
        blnNurseryOK = false;

        blnSinValor = false;
        blnPulsado = false;
        blnPintar = false;

        respuesta1 = findViewById(R.id.respuesta1);
        respuesta2 = findViewById(R.id.respuesta2);
        respuesta3 = findViewById(R.id.respuesta3);
        respuesta4 = findViewById(R.id.respuesta4);

        chk1 = findViewById(R.id.imgCheck1);
        chk2 = findViewById(R.id.imgCheck2);
        chk3_1 = findViewById(R.id.imgCheck3_1);
        chk3_2 = findViewById(R.id.imgCheck3_2);
        chk3_3 = findViewById(R.id.imgCheck3_3);
        chk4_1 = findViewById(R.id.imgCheck4_1);
        chk4_2 = findViewById(R.id.imgCheck4_2);

        //FILA 1
        final TextView txt1_1Errejionalista = findViewById(R.id.txt1_1);
        final TextView txt1_2 = findViewById(R.id.txt1_2);
        final TextView txt1_3 = findViewById(R.id.txt1_3);
        final TextView txt1_4 = findViewById(R.id.txt1_4);
        final TextView txt1_5 = findViewById(R.id.txt1_5);
        final TextView txt1_6 = findViewById(R.id.txt1_6);
        final TextView txt1_7 = findViewById(R.id.txt1_7);
        final TextView txt1_8 = findViewById(R.id.txt1_8);
        final TextView txt1_9 = findViewById(R.id.txt1_9);
        final TextView txt1_10 = findViewById(R.id.txt1_10);
        final TextView txt1_11 = findViewById(R.id.txt1_11);
        final TextView txt1_12 = findViewById(R.id.txt1_12);
        final TextView txt1_13 = findViewById(R.id.txt1_13);
        final TextView txt1_14 = findViewById(R.id.txt1_14);

        //FILA 2
        final TextView txt2_1 = findViewById(R.id.txt2_1);
        final TextView txt2_2 = findViewById(R.id.txt2_2);
        final TextView txt2_3 = findViewById(R.id.txt2_3);
        final TextView txt2_4 = findViewById(R.id.txt2_4);
        final TextView txt2_5 = findViewById(R.id.txt2_5);
        final TextView txt2_6 = findViewById(R.id.txt2_6);
        final TextView txt2_7 = findViewById(R.id.txt2_7);
        final TextView txt2_8 = findViewById(R.id.txt2_8);
        final TextView txt2_9 = findViewById(R.id.txt2_9);
        final TextView txt2_10 = findViewById(R.id.txt2_10);
        final TextView txt2_11 = findViewById(R.id.txt2_11);
        final TextView txt2_12 = findViewById(R.id.txt2_12);
        final TextView txt2_13 = findViewById(R.id.txt2_13);
        final TextView txt2_14 = findViewById(R.id.txt2_14);

        //FILA 3
        final TextView txt3_1 = findViewById(R.id.txt3_1);
        final TextView txt3_2 = findViewById(R.id.txt3_2);
        final TextView txt3_3 = findViewById(R.id.txt3_3);
        final TextView txt3_4 = findViewById(R.id.txt3_4);
        final TextView txt3_5 = findViewById(R.id.txt3_5);
        final TextView txt3_6 = findViewById(R.id.txt3_6);
        final TextView txt3_7 = findViewById(R.id.txt3_7);
        final TextView txt3_8 = findViewById(R.id.txt3_8);
        final TextView txt3_9 = findViewById(R.id.txt3_9);
        final TextView txt3_10 = findViewById(R.id.txt3_10);
        final TextView txt3_11 = findViewById(R.id.txt3_11);
        final TextView txt3_12 = findViewById(R.id.txt3_12);
        final TextView txt3_13 = findViewById(R.id.txt3_13);
        final TextView txt3_14 = findViewById(R.id.txt3_14);

        //FILA 4
        final TextView txt4_1 = findViewById(R.id.txt4_1);
        final TextView txt4_2 = findViewById(R.id.txt4_2);
        final TextView txt4_3 = findViewById(R.id.txt4_3);
        final TextView txt4_4Eklektikoa = findViewById(R.id.txt4_4);
        final TextView txt4_5 = findViewById(R.id.txt4_5);
        final TextView txt4_6 = findViewById(R.id.txt4_6);
        final TextView txt4_7 = findViewById(R.id.txt4_7);
        final TextView txt4_8 = findViewById(R.id.txt4_8);
        final TextView txt4_9 = findViewById(R.id.txt4_9);
        final TextView txt4_10 = findViewById(R.id.txt4_10);
        final TextView txt4_11 = findViewById(R.id.txt4_11);
        final TextView txt4_12 = findViewById(R.id.txt4_12);
        final TextView txt4_13 = findViewById(R.id.txt4_13);
        final TextView txt4_14 = findViewById(R.id.txt4_14);

        //FILA 5
        final TextView txt5_1 = findViewById(R.id.txt5_1);
        final TextView txt5_2 = findViewById(R.id.txt5_2);
        final TextView txt5_3 = findViewById(R.id.txt5_3);
        final TextView txt5_4 = findViewById(R.id.txt5_4);
        final TextView txt5_5 = findViewById(R.id.txt5_5);
        final TextView txt5_6 = findViewById(R.id.txt5_6);
        final TextView txt5_7 = findViewById(R.id.txt5_7);
        final TextView txt5_8 = findViewById(R.id.txt5_8);
        final TextView txt5_9 = findViewById(R.id.txt5_9);
        final TextView txt5_10 = findViewById(R.id.txt5_10);
        final TextView txt5_11 = findViewById(R.id.txt5_11);
        final TextView txt5_12 = findViewById(R.id.txt5_12);
        final TextView txt5_13 = findViewById(R.id.txt5_13);
        final TextView txt5_14 = findViewById(R.id.txt5_14);

        //FILA 6
        final TextView txt6_1 = findViewById(R.id.txt6_1);
        final TextView txt6_2 = findViewById(R.id.txt6_2);
        final TextView txt6_3 = findViewById(R.id.txt6_3);
        final TextView txt6_4 = findViewById(R.id.txt6_4);
        final TextView txt6_5 = findViewById(R.id.txt6_5);
        final TextView txt6_6Ingelesa = findViewById(R.id.txt6_6);
        final TextView txt6_7 = findViewById(R.id.txt6_7);
        final TextView txt6_8 = findViewById(R.id.txt6_8);
        final TextView txt6_9 = findViewById(R.id.txt6_9);
        final TextView txt6_10 = findViewById(R.id.txt6_10);
        final TextView txt6_11 = findViewById(R.id.txt6_11);
        final TextView txt6_12Smith = findViewById(R.id.txt6_12);
        final TextView txt6_13IngelesaEnd = findViewById(R.id.txt6_13);
        final TextView txt6_14AchucarroEnd = findViewById(R.id.txt6_14);

        //FILA 7
        final TextView txt7_1 = findViewById(R.id.txt7_1);
        final TextView txt7_2 = findViewById(R.id.txt7_2);
        final TextView txt7_3 = findViewById(R.id.txt7_3);
        final TextView txt7_4 = findViewById(R.id.txt7_4);
        final TextView txt7_5 = findViewById(R.id.txt7_5);
        final TextView txt7_6 = findViewById(R.id.txt7_6);
        final TextView txt7_7 = findViewById(R.id.txt7_7);
        final TextView txt7_8 = findViewById(R.id.txt7_8);
        final TextView txt7_9 = findViewById(R.id.txt7_9);
        final TextView txt7_10 = findViewById(R.id.txt7_10);
        final TextView txt7_11 = findViewById(R.id.txt7_11);
        final TextView txt7_12 = findViewById(R.id.txt7_12);
        final TextView txt7_13 = findViewById(R.id.txt7_13);
        final TextView txt7_14 = findViewById(R.id.txt7_14);

        //FILA 8
        final TextView txt8_1 = findViewById(R.id.txt8_1);
        final TextView txt8_2 = findViewById(R.id.txt8_2);
        final TextView txt8_3 = findViewById(R.id.txt8_3);
        final TextView txt8_4 = findViewById(R.id.txt8_4);
        final TextView txt8_5 = findViewById(R.id.txt8_5);
        final TextView txt8_6 = findViewById(R.id.txt8_6);
        final TextView txt8_7 = findViewById(R.id.txt8_7);
        final TextView txt8_8Nursery = findViewById(R.id.txt8_8);
        final TextView txt8_9 = findViewById(R.id.txt8_9);
        final TextView txt8_10 = findViewById(R.id.txt8_10);
        final TextView txt8_11 = findViewById(R.id.txt8_11);
        final TextView txt8_12 = findViewById(R.id.txt8_12);
        final TextView txt8_13 = findViewById(R.id.txt8_13);
        final TextView txt8_14 = findViewById(R.id.txt8_14);

        //FILA 9
        final TextView txt9_1 = findViewById(R.id.txt9_1);
        final TextView txt9_2 = findViewById(R.id.txt9_2);
        final TextView txt9_3 = findViewById(R.id.txt9_3);
        final TextView txt9_4 = findViewById(R.id.txt9_4);
        final TextView txt9_5 = findViewById(R.id.txt9_5);
        final TextView txt9_6 = findViewById(R.id.txt9_6);
        final TextView txt9_7 = findViewById(R.id.txt9_7);
        final TextView txt9_8 = findViewById(R.id.txt9_8);
        final TextView txt9_9 = findViewById(R.id.txt9_9);
        final TextView txt9_10 = findViewById(R.id.txt9_10);
        final TextView txt9_11 = findViewById(R.id.txt9_11);
        final TextView txt9_12 = findViewById(R.id.txt9_12);
        final TextView txt9_13 = findViewById(R.id.txt9_13);
        final TextView txt9_14 = findViewById(R.id.txt9_14);


        //FILA 10
        final TextView txt10_1 = findViewById(R.id.txt10_1);
        final TextView txt10_2 = findViewById(R.id.txt10_2);
        final TextView txt10_3 = findViewById(R.id.txt10_3);
        final TextView txt10_4 = findViewById(R.id.txt10_4);
        final TextView txt10_5 = findViewById(R.id.txt10_5);
        final TextView txt10_6 = findViewById(R.id.txt10_6);
        final TextView txt10_7 = findViewById(R.id.txt10_7);
        final TextView txt10_8 = findViewById(R.id.txt10_8);
        final TextView txt10_9 = findViewById(R.id.txt10_9);
        final TextView txt10_10 = findViewById(R.id.txt10_10);
        final TextView txt10_11 = findViewById(R.id.txt10_11);
        final TextView txt10_12SmithEnd = findViewById(R.id.txt10_12);
        final TextView txt10_13 = findViewById(R.id.txt10_13);
        final TextView txt10_14 = findViewById(R.id.txt10_14);

        //FILA 11
        final TextView txt11_1 = findViewById(R.id.txt11_1);
        final TextView txt11_2 = findViewById(R.id.txt11_2);
        final TextView txt11_3 = findViewById(R.id.txt11_3);
        final TextView txt11_4 = findViewById(R.id.txt11_4);
        final TextView txt11_5 = findViewById(R.id.txt11_5);
        final TextView txt11_6 = findViewById(R.id.txt11_6);
        final TextView txt11_7 = findViewById(R.id.txt11_7);
        final TextView txt11_8 = findViewById(R.id.txt11_8);
        final  TextView txt11_9 = findViewById(R.id.txt11_9);
        final TextView txt11_10 = findViewById(R.id.txt11_10);
        final TextView txt11_11 = findViewById(R.id.txt11_11);
        final TextView txt11_12 = findViewById(R.id.txt11_12);
        final TextView txt11_13 = findViewById(R.id.txt11_13);
        final TextView txt11_14 = findViewById(R.id.txt11_14);

        //FILA 12
        final TextView txt12_1Sotoa = findViewById(R.id.txt12_1);
        final TextView txt12_2 = findViewById(R.id.txt12_2);
        final TextView txt12_3 = findViewById(R.id.txt12_3);
        final TextView txt12_4 = findViewById(R.id.txt12_4);
        final TextView txt12_5SotoaEnd = findViewById(R.id.txt12_5);
        final TextView txt12_6 = findViewById(R.id.txt12_6);
        final TextView txt12_7 = findViewById(R.id.txt12_7);
        final TextView txt12_8 = findViewById(R.id.txt12_8);
        final TextView txt12_9 = findViewById(R.id.txt12_9);
        final TextView txt12_10 = findViewById(R.id.txt12_10);
        final TextView txt12_11 = findViewById(R.id.txt12_11);
        final TextView txt12_12 = findViewById(R.id.txt12_12);
        final TextView txt12_13 = findViewById(R.id.txt12_13);
        final TextView txt12_14 = findViewById(R.id.txt12_14);

        //FILA 13
        final TextView txt13_1 = findViewById(R.id.txt13_1);
        final TextView txt13_2 = findViewById(R.id.txt13_2);
        final TextView txt13_3 = findViewById(R.id.txt13_3);
        final TextView txt13_4EklektikoaEnd = findViewById(R.id.txt13_4);
        final TextView txt13_5 = findViewById(R.id.txt13_5);
        final TextView txt13_6 = findViewById(R.id.txt13_6);
        final TextView txt13_7 = findViewById(R.id.txt13_7);
        final TextView txt13_8 = findViewById(R.id.txt13_8);
        final TextView txt13_9 = findViewById(R.id.txt13_9);
        final TextView txt13_10 = findViewById(R.id.txt13_10);
        final TextView txt13_11 = findViewById(R.id.txt13_11);
        final TextView txt13_12 = findViewById(R.id.txt13_12);
        final TextView txt13_13 = findViewById(R.id.txt13_13);
        final TextView txt13_14 = findViewById(R.id.txt13_14);

        //FILA 14
        final TextView txt14_1 = findViewById(R.id.txt14_1);
        final TextView txt14_2 = findViewById(R.id.txt14_2);
        final TextView txt14_3 = findViewById(R.id.txt14_3);
        final TextView txt14_4 = findViewById(R.id.txt14_4);
        final TextView txt14_5 = findViewById(R.id.txt14_5);
        final TextView txt14_6 = findViewById(R.id.txt14_6);
        final TextView txt14_7 = findViewById(R.id.txt14_7);
        final TextView txt14_8NurseryEnd = findViewById(R.id.txt14_8);
        final TextView txt14_9 = findViewById(R.id.txt14_9);
        final TextView txt14_10 = findViewById(R.id.txt14_10);
        final TextView txt14_11 = findViewById(R.id.txt14_11);
        final TextView txt14_12 = findViewById(R.id.txt14_12);
        final TextView txt14_13 = findViewById(R.id.txt14_13);
        final TextView txt14_14ErrejionalistaEnd = findViewById(R.id.txt14_14);

        final TextView[] textViewArray = {txt1_1Errejionalista,txt1_2,txt1_3,txt1_4,txt1_5,txt1_6,txt1_7,txt1_8,txt1_9,txt1_10,txt1_11,txt1_12,txt1_13,txt1_14,
                txt2_1,txt2_2,txt2_3,txt2_4,txt2_5,txt2_6,txt2_7,txt2_8,txt2_9,txt2_10,txt2_11,txt2_12,txt2_13,txt2_14,
                txt3_1,txt3_2,txt3_3,txt3_4,txt3_5,txt3_6,txt3_7,txt3_8,txt3_9,txt3_10,txt3_11,txt3_12,txt3_13,txt3_14,
                txt4_1,txt4_2,txt4_3,txt4_4Eklektikoa,txt4_5,txt4_6,txt4_7,txt4_8,txt4_9,txt4_10,txt4_11,txt4_12,txt4_13,txt4_14,
                txt5_1,txt5_2,txt5_3,txt5_4,txt5_5,txt5_6,txt5_7,txt5_8,txt5_9,txt5_10,txt5_11,txt5_12,txt5_13,txt5_14,
                txt6_1,txt6_2,txt6_3,txt6_4,txt6_5,txt6_6Ingelesa,txt6_7,txt6_8,txt6_9,txt6_10,txt6_11,txt6_12Smith,txt6_13IngelesaEnd,txt6_14AchucarroEnd,
                txt7_1,txt7_2,txt7_3,txt7_4,txt7_5,txt7_6,txt7_7,txt7_8,txt7_9,txt7_10,txt7_11,txt7_12,txt7_13,txt7_14,
                txt8_1,txt8_2,txt8_3,txt8_4,txt8_5,txt8_6,txt8_7,txt8_8Nursery,txt8_9,txt8_10,txt8_11,txt8_12,txt8_13,txt8_14,
                txt9_1,txt9_2,txt9_3,txt9_4,txt9_5,txt9_6,txt9_7,txt9_8,txt9_9,txt9_10,txt9_11,txt9_12,txt9_13,txt9_14,
                txt10_1,txt10_2,txt10_3,txt10_4,txt10_5,txt10_6,txt10_7,txt10_8,txt10_9,txt10_10,txt10_11,txt10_12SmithEnd,txt10_13,txt10_14,
                txt11_1,txt11_2,txt11_3,txt11_4,txt11_5,txt11_6,txt11_7,txt11_8,txt11_9,txt11_10,txt11_11,txt11_12,txt11_13,txt11_14,
                txt12_1Sotoa,txt12_2,txt12_3,txt12_4,txt12_5SotoaEnd,txt12_6,txt12_7,txt12_8,txt12_9,txt12_10,txt12_11,txt12_12,txt12_13,txt12_14,
                txt13_1,txt13_2,txt13_3,txt13_4EklektikoaEnd,txt13_5,txt13_6,txt13_7,txt13_8,txt13_9,txt13_10,txt13_11,txt13_12,txt13_13,txt13_14,
                txt14_1,txt14_2,txt14_3,txt14_4,txt14_5,txt14_6,txt14_7,txt14_8NurseryEnd,txt14_9,txt14_10,txt14_11,txt14_12,txt14_13,txt14_14ErrejionalistaEnd};

        final TextView[] textViewErrejionalista = {txt1_1Errejionalista,txt2_2,txt3_3,txt4_4Eklektikoa,txt5_5,txt6_6Ingelesa,txt7_7,txt8_8Nursery,txt9_9,txt10_10,txt11_11,txt12_12,txt13_13,txt14_14ErrejionalistaEnd};
        final TextView[] textViewEIngelesa = {txt6_6Ingelesa,txt6_7,txt6_8,txt6_9,txt6_10,txt6_11,txt6_12Smith,txt6_13IngelesaEnd};
        final TextView[] textViewSmith = {txt6_12Smith,txt7_12,txt8_12,txt9_12,txt10_12SmithEnd};
        final TextView[] textViewAchucarro = {txt14_14ErrejionalistaEnd,txt13_14,txt12_14,txt11_14,txt10_14,txt9_14,txt8_14,txt7_14,txt6_14AchucarroEnd};
        final TextView[] textViewEklektikoa = {txt4_4Eklektikoa,txt5_4,txt6_4,txt7_4,txt8_4,txt9_4,txt10_4,txt11_4,txt12_4,txt13_4EklektikoaEnd};
        final TextView[] textViewSotoa = {txt12_1Sotoa,txt12_2,txt12_3,txt12_4,txt12_5SotoaEnd};
        final TextView[] textViewNursery = {txt8_8Nursery,txt9_8,txt10_8,txt11_8,txt12_8,txt13_8,txt14_8NurseryEnd};

        final int[] contLetras = {0} ;
        for (int i = 0; i < textViewArray.length; i++){
            final int j = i;
            textViewArray[i].setBackgroundColor(Color.TRANSPARENT);
            textViewArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorDrawable cd = (ColorDrawable) textViewArray[j].getBackground();
                    int colorCode = cd.getColor();

                    if (colorCode != Color.BLUE &&
                            colorCode != Color.CYAN &&
                            colorCode != Color.GREEN &&
                            colorCode != Color.MAGENTA &&
                            colorCode != Color.RED &&
                            colorCode != Color.DKGRAY &&
                            colorCode != Color.YELLOW ||

                            textViewArray[j] == txt6_6Ingelesa && (colorCode == Color.BLUE || colorCode == Color.CYAN)||
                            textViewArray[j] == txt6_12Smith && (colorCode == Color.CYAN || colorCode == Color.GREEN) ||
                            textViewArray[j] == txt14_14ErrejionalistaEnd && (colorCode == Color.BLUE || colorCode == Color.MAGENTA) ||
                            textViewArray[j] == txt4_4Eklektikoa && (colorCode == Color.BLUE || colorCode == Color.RED) ||
                            textViewArray[j] == txt12_4 && (colorCode == Color.RED || colorCode == Color.DKGRAY) ||
                            textViewArray[j] == txt8_8Nursery && (colorCode == Color.BLUE || colorCode == Color.YELLOW))
                    {

                        textViewArray[j].setBackgroundColor(Color.GRAY);

                        //ERREJIONALISTA
                        if (txt1_1Errejionalista == textViewArray[j]){
                            blnErrejionalista = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                            cd = (ColorDrawable) txt1_1Errejionalista.getBackground();
                            colorCode = cd.getColor();
                        }

                        if (txt14_14ErrejionalistaEnd == textViewArray[j] && blnErrejionalista == true && colorCode == Color.GRAY){
                            blnSinValor = false;
                            blnErrejionalista = false;
                            blnPintar = true;
                            blnErrejionalistaOK = true;
                            contEstilosArkitektos+=1;
                            comprobarFin();
                            for (int e = 0; e<textViewErrejionalista.length; e++){
                                textViewErrejionalista[e].setBackgroundColor(Color.BLUE);
                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        //INGELESA
                        if (txt6_6Ingelesa == textViewArray[j]){
                            blnIngelesa = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                            cd = (ColorDrawable) txt6_6Ingelesa.getBackground();
                            colorCode = cd.getColor();
                        }
                        if (txt6_13IngelesaEnd == textViewArray[j] && blnIngelesa == true && colorCode == Color.GRAY){
                            blnSinValor = false;
                            blnIngelesaOK = true;
                            contEstilosArkitektos+=1;
                            comprobarFin();
                            for (int e = 0; e<textViewEIngelesa.length; e++){
                                textViewEIngelesa[e].setBackgroundColor(Color.CYAN);
                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        //SMITH
                        if (txt6_12Smith == textViewArray[j]){
                            blnSmith = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                            cd = (ColorDrawable) txt6_12Smith.getBackground();
                            colorCode = cd.getColor();
                        }
                        if (txt10_12SmithEnd == textViewArray[j] && blnSmith == true && colorCode == Color.GRAY){
                            blnSinValor = false;
                            contArkitektos+=1;
                            blnSmithOK = true;
                            comprobarFin();
                            for (int e = 0; e<textViewSmith.length; e++){
                                textViewSmith[e].setBackgroundColor(Color.GREEN);

                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        //ACHUCARRO
                        if (!blnPintar){
                            if (txt14_14ErrejionalistaEnd == textViewArray[j]) {
                                blnAchurraco = true;
                                blnSinValor = false;
                            } else {
                                blnSinValor = true;
                                cd = (ColorDrawable) txt14_14ErrejionalistaEnd.getBackground();
                                colorCode = cd.getColor();
                            }
                            if (txt6_14AchucarroEnd == textViewArray[j] && blnAchurraco == true && colorCode == Color.GRAY) {
                                blnSinValor = false;
                                blnAchurracoOK = true;
                                contArkitektos+=1;
                                comprobarFin();
                                for (int e = 0; e < textViewAchucarro.length; e++) {
                                    textViewAchucarro[e].setBackgroundColor(Color.MAGENTA);
                                }
                            } else {
                                blnSinValor = true;
                            }
                        }else{
                            blnPintar = false;
                        }

                        //EKLEKTIKOA
                        if (txt4_4Eklektikoa == textViewArray[j]){
                            blnEklektikoa = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                            cd = (ColorDrawable) txt4_4Eklektikoa.getBackground();
                            colorCode = cd.getColor();
                        }
                        if (txt13_4EklektikoaEnd  == textViewArray[j] && blnEklektikoa == true && colorCode == Color.GRAY){
                            blnSinValor = false;
                            contEstilosArkitektos+=1;
                            blnEklektikoaOK = true;
                            comprobarFin();
                            Log.d("mytag","PINTANDO EKLEKTIKOA");
                            for (int e = 0; e<textViewEklektikoa.length; e++){
                                textViewEklektikoa[e].setBackgroundColor(Color.RED);
                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        //SOTOA
                        if (txt12_1Sotoa == textViewArray[j]){
                            blnSotoa = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                            cd = (ColorDrawable) txt12_1Sotoa.getBackground();
                            colorCode = cd.getColor();
                        }
                        if (txt12_5SotoaEnd == textViewArray[j] && blnSotoa == true && colorCode == Color.GRAY){
                            blnSinValor = false;
                            blnSotoaOK = true;
                            comprobarFin();

                            for (int e = 0; e<textViewSotoa.length; e++){
                                textViewSotoa[e].setBackgroundColor(Color.DKGRAY);
                            }
                        }
                        else{
                            blnSinValor = true;
                        }
                        //NURSERY
                        if (txt8_8Nursery == textViewArray[j]){
                            blnNursery = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                            cd = (ColorDrawable) txt8_8Nursery.getBackground();
                            colorCode = cd.getColor();
                        }
                        if (txt14_8NurseryEnd == textViewArray[j] && blnNursery == true && colorCode == Color.GRAY){
                            blnSinValor = false;
                            blnNurseryOK = true;
                            comprobarFin();
                            for (int e = 0; e<textViewNursery.length; e++){
                                textViewNursery[e].setBackgroundColor(Color.YELLOW);

                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        if (blnPulsado){
                            Log.d("mytag","SEGUNDA LETRA PULSADA");
                            // Drawable d = getResources().getColor(Color.GRAY);
                            cd = (ColorDrawable) textViewArray[j].getBackground();
                            colorCode = cd.getColor();
                            if (blnSinValor && colorCode == Color.GRAY){
                                Log.d("mytag", "TextView pasar a gris o color: "+ textViewArray[j]);

                                textViewArray[ contLetras[0]].setBackgroundColor(Color.TRANSPARENT);
                                textViewArray[j].setBackgroundColor(Color.TRANSPARENT);

                                if (textViewArray[contLetras[0]] == txt6_6Ingelesa || textViewArray[j] == txt6_6Ingelesa){
                                    cd = (ColorDrawable) txt5_5.getBackground();
                                    colorCode = cd.getColor();
                                    if (colorCode == Color.BLUE){
                                        txt6_6Ingelesa.setBackgroundColor(Color.BLUE);
                                    }
                                    else {
                                        Log.d("mytag", "PULSADO LETRA DE INGELESA");
                                        cd = (ColorDrawable) txt6_7.getBackground();
                                        colorCode = cd.getColor();
                                        if (colorCode == Color.CYAN){
                                            txt6_6Ingelesa.setBackgroundColor(Color.CYAN);
                                        }
                                    }
                                }

                                if (textViewArray[contLetras[0]] == txt6_12Smith || textViewArray[j] == txt6_12Smith){
                                    cd = (ColorDrawable) txt7_12.getBackground();
                                    colorCode = cd.getColor();

                                    //txt6_12Smith.setBackgroundColor(Color.CYAN);
                                    if (colorCode == Color.GREEN){
                                        txt6_12Smith.setBackgroundColor(Color.GREEN);
                                    }
                                    else {
                                        Log.d("mytag", "PULSADO LETRA DE INGELESA");
                                        cd = (ColorDrawable) txt6_11.getBackground();
                                        colorCode = cd.getColor();
                                        if (colorCode == Color.CYAN){
                                            txt6_12Smith.setBackgroundColor(Color.CYAN);
                                        }
                                    }
                                }
                                if (textViewArray[contLetras[0]] == txt14_14ErrejionalistaEnd || textViewArray[j] == txt14_14ErrejionalistaEnd){

                                    cd = (ColorDrawable) txt13_13.getBackground();
                                    colorCode = cd.getColor();
                                    if (colorCode == Color.BLUE){
                                        txt14_14ErrejionalistaEnd.setBackgroundColor(Color.BLUE);
                                    }
                                    else {
                                        //Log.d("mytag", "PULSADO LETRA DE INGELESA");
                                        cd = (ColorDrawable) txt13_14.getBackground();
                                        colorCode = cd.getColor();
                                        if (colorCode == Color.MAGENTA){
                                            txt14_14ErrejionalistaEnd.setBackgroundColor(Color.MAGENTA);
                                        }
                                    }
                                }
                                if (textViewArray[contLetras[0]] == txt4_4Eklektikoa || textViewArray[j] == txt4_4Eklektikoa){

                                    cd = (ColorDrawable) txt5_4.getBackground();
                                    colorCode = cd.getColor();
                                    if (colorCode == Color.RED){
                                        txt4_4Eklektikoa.setBackgroundColor(Color.RED);
                                    }
                                    else {
                                        cd = (ColorDrawable) txt5_5.getBackground();
                                        colorCode = cd.getColor();
                                        if (colorCode == Color.BLUE){
                                            txt4_4Eklektikoa.setBackgroundColor(Color.BLUE);
                                        }
                                    }

                                }
                                if (textViewArray[contLetras[0]] == txt12_4 || textViewArray[j] == txt12_4){

                                    cd = (ColorDrawable) txt12_3.getBackground();
                                    colorCode = cd.getColor();
                                    if (colorCode == Color.DKGRAY){
                                        txt12_4.setBackgroundColor(Color.DKGRAY);
                                    }
                                    else {

                                        cd = (ColorDrawable) txt11_4.getBackground();
                                        colorCode = cd.getColor();
                                        if (colorCode == Color.RED){
                                            txt12_4.setBackgroundColor(Color.RED);
                                        }
                                    }
                                }
                                if (textViewArray[contLetras[0]] == txt8_8Nursery || textViewArray[j] == txt8_8Nursery){


                                    cd = (ColorDrawable) txt8_9.getBackground();
                                    colorCode = cd.getColor();
                                    if (colorCode == Color.YELLOW){
                                        txt8_8Nursery.setBackgroundColor(Color.YELLOW);
                                    }
                                    else {

                                        cd = (ColorDrawable) txt7_7.getBackground();
                                        colorCode = cd.getColor();
                                        if (colorCode == Color.BLUE){
                                            txt8_8Nursery.setBackgroundColor(Color.BLUE);
                                        }
                                    }

                                }

                                Log.d("mytag","PRIMERA LETRA: "+contLetras[0]);
                                Log.d("mytag","SEGUNDA LETRA: "+j);

                                blnSinValor = false;
                            }
                            blnPulsado = false;
                        }else{
                            Log.d("mytag","PRIMERA LETRA PULSADA");
                            contLetras[0] = j;
                            blnPulsado = true;
                        }

                        //3 estilos de arkitektos
                        Log.d("mytag","CONTADOR ESTILOS: "+contEstilosArkitektos);
                        switch (contEstilosArkitektos){
                            case 3:
                                chk3_3.setVisibility(View.VISIBLE);
                            case 2:
                                chk3_2.setVisibility(View.VISIBLE);
                            case 1:
                                chk3_1.setVisibility(View.VISIBLE);
                                break;
                        }

                        if (blnSotoaOK){
                            chk1.setVisibility(View.VISIBLE);
                        }

                        if (blnNurseryOK){
                            chk2.setVisibility(View.VISIBLE);
                        }

                        Log.d("mytag","CONTADOR ARKITEKTOS: "+contArkitektos);
                        switch(contArkitektos){
                            case 2:
                                chk4_2.setVisibility(View.VISIBLE);
                            case 1:
                                chk4_1.setVisibility(View.VISIBLE);
                                break;
                        }
                    }
                }
            });
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (pag_anterior == 0){
                Intent i = new Intent();
                i.putExtra("keydown",REQ_BTNATRAS);
                setResult(RESULT_OK,i);
            }
            mp.stop();
            mp2.stop();
            Log.d("mytag","Has ido atras");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    public void comprobarFin(){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Oso ondo!!", Toast.LENGTH_SHORT);
        toast.show();
        //Etxeko zerbitzuetarako tokia
        if(blnSotoaOK){
            respuesta1.setTextColor(Color.GREEN);
            //respuesta1.setVisibility(View.INVISIBLE);
        }
        //Etxearen umeentzako zonaldea
        if(blnNurseryOK){
            respuesta2.setTextColor(Color.GREEN);
            //respuesta2.setVisibility(View.INVISIBLE);
        }
        //3 arkitektura estiloak
        if(blnEklektikoaOK && blnErrejionalistaOK && blnIngelesaOK){
            //respuesta3.setVisibility(View.INVISIBLE);
            respuesta3.setTextColor(Color.GREEN);
        }
        //2 arkitekto harrantzitsuen abizenak
        if(blnAchurracoOK && blnSmithOK){
            respuesta4.setTextColor(Color.GREEN);
            //respuesta4.setVisibility(View.INVISIBLE);
        }

        if(blnErrejionalistaOK && blnIngelesaOK && blnSmithOK && blnAchurracoOK && blnEklektikoaOK && blnSotoaOK && blnNurseryOK){
            mp2.start();
            mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            public void onCompletion(MediaPlayer mp) {
                                                if (pag_anterior == 0){
                                                    int i = 18;
//                                                    db=new MyOpenHelper(cont);
//                                                    db.ActualizarJuego_Id(i);
//                                                    db.close();

                                                    Intent returnIntent = new Intent();
                                                    returnIntent.putExtra("result",1);
                                                    setResult(Activity.RESULT_OK,returnIntent);
                                                    finish();
                                                }
                                            }
                                        }
            );
        }
    }
}

