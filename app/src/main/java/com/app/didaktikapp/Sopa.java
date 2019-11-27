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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.didaktikapp.R;

public class Sopa extends AppCompatActivity {

    private MediaPlayer mp, mp2;
    private Button btnNext;
    private TextView respuesta1, respuesta2, respuesta3, respuesta4;
    private boolean blnZumeltzegi,blnDefentsa,blnHotelak,blnKondea;
    private boolean blnZumeltzegiOK, blnDefentsaOK, blnHotelakOK, blnKondeaOK;
    private boolean blnSinValor, blnPulsado, blnPintar;
    private Context cont = this;
    private int pag_anterior;
    static final int REQ_BTN = 0;
    static final int REQ_BTNATRAS = 12;

    private ImageView chk1,chk2,chk3,chk4;

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
        blnZumeltzegi = false;
        blnDefentsa = false;
        blnHotelak = false;
        blnKondea = false;

        blnZumeltzegiOK = false;
        blnDefentsaOK = false;
        blnHotelakOK = false;
        blnKondeaOK = false;

        blnSinValor = false;
        blnPulsado = false;
        blnPintar = false;

        respuesta1 = findViewById(R.id.respuesta1);
        respuesta2 = findViewById(R.id.respuesta2);
        respuesta3 = findViewById(R.id.respuesta3);
        respuesta4 = findViewById(R.id.respuesta4);

        chk1 = findViewById(R.id.imgCheck1);
        chk2 = findViewById(R.id.imgCheck2);
        chk3 = findViewById(R.id.imgCheck3);
        chk4 = findViewById(R.id.imgCheck4);

        //FILA 1
        final TextView txt1_1 = findViewById(R.id.txt1_1);
        final TextView txt1_2 = findViewById(R.id.txt1_2);
        final TextView txt1_3 = findViewById(R.id.txt1_3);
        final TextView txt1_4 = findViewById(R.id.txt1_4);
        final TextView txt1_5 = findViewById(R.id.txt1_5);
        final TextView txt1_6 = findViewById(R.id.txt1_6);
        final TextView txt1_7 = findViewById(R.id.txt1_7);
        final TextView txt1_8DefentsaFin = findViewById(R.id.txt1_8);
        final TextView txt1_9 = findViewById(R.id.txt1_9);
        final TextView txt1_10 = findViewById(R.id.txt1_10);

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

        //FILA 3
        final TextView txt3_1 = findViewById(R.id.txt3_1);
        final TextView txt3_2 = findViewById(R.id.txt3_2);
        final TextView txt3_3 = findViewById(R.id.txt3_3);
        final TextView txt3_4HotelakFin = findViewById(R.id.txt3_4);
        final TextView txt3_5 = findViewById(R.id.txt3_5);
        final TextView txt3_6 = findViewById(R.id.txt3_6);
        final TextView txt3_7 = findViewById(R.id.txt3_7);
        final TextView txt3_8 = findViewById(R.id.txt3_8);
        final TextView txt3_9 = findViewById(R.id.txt3_9);
        final TextView txt3_10Hotelak = findViewById(R.id.txt3_10);

        //FILA 4
        final TextView txt4_1 = findViewById(R.id.txt4_1);
        final TextView txt4_2 = findViewById(R.id.txt4_2);
        final TextView txt4_3 = findViewById(R.id.txt4_3);
        final TextView txt4_4 = findViewById(R.id.txt4_4);
        final TextView txt4_5 = findViewById(R.id.txt4_5);
        final TextView txt4_6 = findViewById(R.id.txt4_6);
        final TextView txt4_7 = findViewById(R.id.txt4_7);
        final TextView txt4_8 = findViewById(R.id.txt4_8);
        final TextView txt4_9 = findViewById(R.id.txt4_9);
        final TextView txt4_10 = findViewById(R.id.txt4_10);

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

        //FILA 6
        final TextView txt6_1 = findViewById(R.id.txt6_1);
        final TextView txt6_2 = findViewById(R.id.txt6_2);
        final TextView txt6_3 = findViewById(R.id.txt6_3);
        final TextView txt6_4 = findViewById(R.id.txt6_4);
        final TextView txt6_5 = findViewById(R.id.txt6_5);
        final TextView txt6_6 = findViewById(R.id.txt6_6);
        final TextView txt6_7 = findViewById(R.id.txt6_7);
        final TextView txt6_8 = findViewById(R.id.txt6_8);
        final TextView txt6_9 = findViewById(R.id.txt6_9);
        final TextView txt6_10 = findViewById(R.id.txt6_10);

        //FILA 7
        final TextView txt7_1Zumeltzegi = findViewById(R.id.txt7_1);
        final TextView txt7_2 = findViewById(R.id.txt7_2);
        final TextView txt7_3 = findViewById(R.id.txt7_3);
        final TextView txt7_4 = findViewById(R.id.txt7_4);
        final TextView txt7_5 = findViewById(R.id.txt7_5);
        final TextView txt7_6 = findViewById(R.id.txt7_6);
        final TextView txt7_7 = findViewById(R.id.txt7_7);
        final TextView txt7_8 = findViewById(R.id.txt7_8);
        final TextView txt7_9 = findViewById(R.id.txt7_9);
        final TextView txt7_10ZumeltzegiFin = findViewById(R.id.txt7_10);

        //FILA 8
        final TextView txt8_1 = findViewById(R.id.txt8_1);
        final TextView txt8_2 = findViewById(R.id.txt8_2);
        final TextView txt8_3 = findViewById(R.id.txt8_3);
        final TextView txt8_4KondeaFin = findViewById(R.id.txt8_4);
        final TextView txt8_5 = findViewById(R.id.txt8_5);
        final TextView txt8_6 = findViewById(R.id.txt8_6);
        final TextView txt8_7 = findViewById(R.id.txt8_7);
        final TextView txt8_8Defentsa = findViewById(R.id.txt8_8);
        final TextView txt8_9 = findViewById(R.id.txt8_9);
        final TextView txt8_10 = findViewById(R.id.txt8_10);

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

        final TextView[] textViewArray = {txt1_1,txt1_2,txt1_3,txt1_4,txt1_5,txt1_6,txt1_7,txt1_8DefentsaFin,txt1_9,txt1_10,
                txt2_1,txt2_2,txt2_3,txt2_4,txt2_5,txt2_6,txt2_7,txt2_8,txt2_9,txt2_10,
                txt3_1,txt3_2,txt3_3,txt3_4HotelakFin,txt3_5,txt3_6,txt3_7,txt3_8,txt3_9,txt3_10Hotelak,
                txt4_1,txt4_2,txt4_3,txt4_4,txt4_5,txt4_6,txt4_7,txt4_8,txt4_9,txt4_10,
                txt5_1,txt5_2,txt5_3,txt5_4,txt5_5,txt5_6,txt5_7,txt5_8,txt5_9,txt5_10,
                txt6_1,txt6_2,txt6_3,txt6_4,txt6_5,txt6_6,txt6_7,txt6_8,txt6_9,txt6_10,
                txt7_1Zumeltzegi,txt7_2,txt7_3,txt7_4,txt7_5,txt7_6,txt7_7,txt7_8,txt7_9,txt7_10ZumeltzegiFin,
                txt8_1,txt8_2,txt8_3,txt8_4KondeaFin,txt8_5,txt8_6,txt8_7,txt8_8Defentsa,txt8_9,txt8_10,
                txt9_1,txt9_2,txt9_3,txt9_4,txt9_5,txt9_6,txt9_7,txt9_8,txt9_9,txt9_10,
                txt10_1,txt10_2,txt10_3,txt10_4,txt10_5,txt10_6,txt10_7,txt10_8,txt10_9,txt10_10};

        final TextView[] textViewZumeltzegi = {txt7_1Zumeltzegi,txt7_2,txt7_3,txt7_4,txt7_5,txt7_6,txt7_7,txt7_8,txt7_9,txt7_10ZumeltzegiFin};
        final TextView[] textViewDefentsa = {txt8_8Defentsa,txt7_8,txt6_8,txt5_8,txt4_8,txt3_8,txt2_8,txt1_8DefentsaFin};
        final TextView[] textViewHotelak = {txt3_10Hotelak,txt3_9,txt3_8,txt3_7,txt3_6,txt3_5,txt3_4HotelakFin};
        final TextView[] textViewKondea = {txt3_4HotelakFin,txt4_4,txt5_4,txt6_4,txt7_4,txt8_4KondeaFin};

        final int[] contLetras = {0} ;
        for (int i = 0; i < textViewArray.length; i++){
            final int j = i;
            textViewArray[i].setBackgroundColor(Color.TRANSPARENT);
            textViewArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorDrawable cd = (ColorDrawable) textViewArray[j].getBackground();
                    int colorCode = cd.getColor();

                    if (colorCode != Color.CYAN &&
                            colorCode != Color.GREEN &&
                            colorCode != Color.RED &&
                            colorCode != Color.YELLOW ||
                            //POSIBLE ERROR AQUI
                            textViewArray[j] == txt7_1Zumeltzegi && (colorCode == Color.CYAN) ||
                            textViewArray[j] == txt8_8Defentsa && (colorCode == Color.GREEN) ||
                            textViewArray[j] == txt3_10Hotelak && (colorCode == Color.RED) ||
                            textViewArray[j] == txt3_4HotelakFin && (colorCode == Color.RED || colorCode == Color.YELLOW))
                    {

                        textViewArray[j].setBackgroundColor(Color.GRAY);

                        //ZUMELTZEGI
                        if (txt7_1Zumeltzegi == textViewArray[j]){
                            blnZumeltzegi = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                            cd = (ColorDrawable) txt7_1Zumeltzegi.getBackground();
                            colorCode = cd.getColor();
                        }

                        if (txt7_10ZumeltzegiFin == textViewArray[j] && blnZumeltzegi && colorCode == Color.GRAY){
                            blnSinValor = false;
                            blnZumeltzegi = false;
                            blnPintar = true;
                            blnZumeltzegiOK = true;
                            comprobarFin();
                            for (int e = 0; e<textViewZumeltzegi.length; e++){
                                textViewZumeltzegi[e].setBackgroundColor(Color.CYAN);
                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        //DEFENTSA
                        if (txt8_8Defentsa == textViewArray[j]){
                            blnDefentsa = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                            cd = (ColorDrawable) txt8_8Defentsa.getBackground();
                            colorCode = cd.getColor();
                        }
                        if (txt1_8DefentsaFin == textViewArray[j] && blnDefentsa && colorCode == Color.GRAY){
                            blnSinValor = false;
                            blnDefentsaOK = true;
                            comprobarFin();
                            for (int e = 0; e<textViewDefentsa.length; e++){
                                textViewDefentsa[e].setBackgroundColor(Color.GREEN);
                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        //HOTELAK
                        if (txt3_10Hotelak == textViewArray[j]){
                            blnHotelak = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                            cd = (ColorDrawable) txt3_10Hotelak.getBackground();
                            colorCode = cd.getColor();
                        }
                        if (txt3_4HotelakFin == textViewArray[j] && blnHotelak && colorCode == Color.GRAY) {
                            blnSinValor = false;
                            blnHotelakOK = true;
                            comprobarFin();
                            for (int e = 0; e<textViewHotelak.length; e++){
                                textViewHotelak[e].setBackgroundColor(Color.RED);

                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        //KONDEA
                        if (!blnPintar){
                            if (txt3_4HotelakFin == textViewArray[j]) {
                                blnKondea = true;
                                blnSinValor = false;
                            } else {
                                blnSinValor = true;
                                cd = (ColorDrawable) txt3_4HotelakFin.getBackground();
                                colorCode = cd.getColor();
                            }
                            if (txt8_4KondeaFin == textViewArray[j] && blnKondea && (colorCode == Color.GRAY || colorCode == Color.RED)) {
                                blnSinValor = false;
                                blnKondeaOK = true;
                                comprobarFin();
                                for (int e = 0; e < textViewKondea.length; e++) {
                                    textViewKondea[e].setBackgroundColor(Color.YELLOW);
                                }
                            } else {
                                blnSinValor = true;
                            }
                        }else{
                            blnPintar = false;
                        }

                        //### COMPROBAR COLORES ###
                        if (blnPulsado){
                            Log.d("mytag","SEGUNDA LETRA PULSADA");
                            // Drawable d = getResources().getColor(Color.GRAY);
                            cd = (ColorDrawable) textViewArray[j].getBackground();
                            colorCode = cd.getColor();
                            if (blnSinValor && colorCode == Color.GRAY){
                                Log.d("mytag", "TextView pasar a gris o color: "+ textViewArray[j]);

                                textViewArray[ contLetras[0]].setBackgroundColor(Color.TRANSPARENT);
                                textViewArray[j].setBackgroundColor(Color.TRANSPARENT);

//                                if (textViewArray[contLetras[0]] == txt7_1Zumeltzegi || textViewArray[j] == txt7_1Zumeltzegi){
//                                    cd = (ColorDrawable) txt7_5.getBackground();
//                                    colorCode = cd.getColor();
//                                    if (colorCode == Color.CYAN){
//                                        txt7_1Zumeltzegi.setBackgroundColor(Color.CYAN);
//                                    }
//                                    else {
//                                        Log.d("mytag", "PULSADO LETRA DE ZUMENTZEGI");
//                                        cd = (ColorDrawable) txt6_7.getBackground();
//                                        colorCode = cd.getColor();
//                                        if (colorCode == Color.CYAN){
//                                            txt7_1Zumeltzegi.setBackgroundColor(Color.CYAN);
//                                        }
//                                    }
//                                }

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

                        if (blnZumeltzegiOK){
                            chk1.setVisibility(View.VISIBLE);
                        }

                        if (blnDefentsaOK){
                            chk2.setVisibility(View.VISIBLE);
                        }

                        if (blnKondeaOK){
                            chk3.setVisibility(View.VISIBLE);
                        }

                        if (blnHotelakOK){
                            chk4.setVisibility(View.VISIBLE);
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
//            mp.stop();
//            mp2.stop();
//            Log.d("mytag","Has ido atras");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void comprobarFin(){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Oso ondo!", Toast.LENGTH_SHORT);
        toast.show();

        if(blnZumeltzegiOK){
            respuesta1.setTextColor(Color.GREEN);
            //respuesta1.setVisibility(View.INVISIBLE);
        }

        if(blnDefentsaOK){
            respuesta2.setTextColor(Color.GREEN);
            //respuesta2.setVisibility(View.INVISIBLE);
        }

        if(blnKondeaOK){
            //respuesta3.setVisibility(View.INVISIBLE);
            respuesta3.setTextColor(Color.GREEN);
        }

        if(blnHotelakOK){
            respuesta4.setTextColor(Color.GREEN);
            //respuesta4.setVisibility(View.INVISIBLE);
        }

        if(blnHotelakOK && blnZumeltzegiOK && blnKondeaOK && blnDefentsaOK){
//            mp2.start();
//            mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                                            public void onCompletion(MediaPlayer mp) {
//                                                if (pag_anterior == 0){
//                                                    int i = 18;
//                                                    db=new MyOpenHelper(cont);
//                                                    db.ActualizarJuego_Id(i);
//                                                    db.close();
//
//                                                    Intent returnIntent = new Intent();
//                                                    returnIntent.putExtra("result",1);
//                                                    setResult(Activity.RESULT_OK,returnIntent);
//                                                    finish();
//                                                }
//                                            }
//                                        }
//            );
        }
    }
}

