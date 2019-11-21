package com.app.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

//        ImageView logo = findViewById(R.id.ivLogo);
//
//        logo.setImageResource(R.drawable.espanita);

//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            Log.i("Error","Esperando a cerrar la pantalla de inicio");
//        } finally {
//            finish();
//        }

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    Log.i("Error", "Esperando a cerrar la pantalla de inicio");
                }
                finish();
            }
        };
        splashTread.start();

    }
}
