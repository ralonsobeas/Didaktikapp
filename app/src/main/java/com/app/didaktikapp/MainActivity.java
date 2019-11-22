package com.app.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.app.didaktikapp.Activities.MapActivity;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private Button botonSalir, botonInicio;

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
