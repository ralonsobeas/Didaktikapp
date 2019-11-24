package com.app.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.Call;
import android.transition.Explode;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        ImageView imageViewOniate = findViewById(R.id.ivLogoOniate);
        ImageView imageViewCJ = findViewById(R.id.ivLogoCJ);
        ImageView imageViewUPV = findViewById(R.id.ivLogoUPV);

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
