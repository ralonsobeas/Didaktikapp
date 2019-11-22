package com.app.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private Button boton;

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

        boton = findViewById(R.id.btnSalir);

        eventos();
    }

    private void eventos(){
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir(v);
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
