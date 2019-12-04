package com.app.didaktikapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.CircleMenu.CircleMenuView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import at.markushi.ui.CircleButton;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private CircleButton  botonInicio,botonContinuar;
    private Button botonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        // "context" must be an Activity, Service or Application object from your app.
//        if (! Python.isStarted()) {
//            Python.start(new AndroidPlatform(getBaseContext()));
//        }
//        Python py = Python.getInstance();
//        PyObject sys = py.getModule("prueba").callAttr("main");
//        setContentView(R.layout.activity_inicio);
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        setContentView(R.layout.activity_main);

        TextView tv=(TextView)findViewById(R.id.tvTitulo);
        Typeface type =  ResourcesCompat.getFont(this, R.font.youthtouch);
        tv.setTypeface(type);
        tv.setText(Html.fromHtml(getString(R.string.html_app_name)));





//        layout = findViewById(R.id.layout);
//
//        int[] fondos = {R.drawable.login1,
//                        R.drawable.login2,
//                        R.drawable.login3,
//                        R.drawable.login4};
//
//        int num = (int) (Math.random()*fondos.length);
//        layout.setBackgroundResource(fondos[num]);

//        botonSalir = findViewById(R.id.btnSalir);
//        botonInicio = findViewById(R.id.btnIniciar);
//        botonContinuar = findViewById(R.id.btnContinuar);
//
//        eventos();

        final CircleMenuView menu = findViewById(R.id.circle_menu);
        menu.setEventListener(new CircleMenuView.EventListener() {
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationStart");
            }

            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationEnd");
            }

            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationStart");
            }

            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationEnd");
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationStart| index: " + index);

            }

            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationEnd| index: " + index);
                switch (index){
                    case 0:
                        Intent i = new Intent(MainActivity.this, MapActivity.class);
                        startActivity(i);

                        break;
                    case 1:
                        break;
                    case 2:
                        finish();
                        break;
                }

            }

            @Override
            public boolean onButtonLongClick(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClick| index: " + index);
                return true;
            }

            @Override
            public void onButtonLongClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonLongClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationEnd| index: " + index);
            }
        });

//        GifImageView gifImageView = findViewById(R.id.gif);



    }


    private void eventos(){
        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir(v);
            }
        });
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapActivity.class);
                startActivity(i);

            }
        });
        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PRUEBA PARA PYTHON
//
//                try {
//                    String prg = "import sys";
//                    BufferedWriter out = new BufferedWriter(new FileWriter("path/a.py"));
//                    out.write(prg);
//                    out.close();
//                    Process p = Runtime.getRuntime().exec("python path/a.py");
//                    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//                    String ret = in.readLine();
//                    System.out.println("value is : "+ret);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        });

    }



    private void iniciar(View v){
        //carga el mapa
    }

    private void continuar(View v){
        //carga el estado en el que estuviesen
    }

    private void salir(View v){
        finish();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    public void finish() {
        super.finish();

    }
}
