package com.app.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.didaktikapp.Activities.MapActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private Button botonSalir, botonInicio, botonContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_inicio);
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        setContentView(R.layout.activity_main);

        Intent i = new Intent(MainActivity.this,InicioActivity.class);
        startActivity(i);



        layout = findViewById(R.id.layout);

        int[] fondos = {R.drawable.login1,
                        R.drawable.login2,
                        R.drawable.login3,
                        R.drawable.login4};

        int num = (int) (Math.random()*fondos.length);
        layout.setBackgroundResource(fondos[num]);

        botonSalir = findViewById(R.id.btnSalir);
        botonInicio = findViewById(R.id.btnIniciar);
        botonContinuar = findViewById(R.id.btnContinuar);

        eventos();

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

                try {
                    String prg = "import sys";
                    BufferedWriter out = new BufferedWriter(new FileWriter("path/a.py"));
                    out.write(prg);
                    out.close();
                    Process p = Runtime.getRuntime().exec("python path/a.py");
                    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String ret = in.readLine();
                    System.out.println("value is : "+ret);
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

}
