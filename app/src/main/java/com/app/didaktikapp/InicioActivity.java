package com.app.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Activity que se muestra al lanzar la aplicación
 * @author gennakk
 */
public class InicioActivity extends AppCompatActivity {

    private ImageView imageViewOniate,imageViewCJ,imageViewUPV,imageViewLetrasOniate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        imageViewOniate = findViewById(R.id.ivLogoOniate);
        imageViewLetrasOniate = findViewById(R.id.ivLetrasOniate);
        imageViewCJ = findViewById(R.id.ivLogoCJ);
        imageViewUPV = findViewById(R.id.ivLogoUPV);

        animation();

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
                    Thread.sleep(4500);

                    Intent i = new Intent(InicioActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    Log.i("Error", "Esperando a cerrar la pantalla de inicio");
                }


            }
        };
        splashTread.start();




    }

    private void animation() {

        Animation  rotateAnimation= AnimationUtils.loadAnimation(this,R.anim.rotate);
        imageViewOniate.startAnimation(rotateAnimation);
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation  animationmove= new TranslateAnimation(Animation.ABSOLUTE,-250,Animation.ABSOLUTE,Animation.ABSOLUTE);
                animationmove.setDuration(1000);
                animationmove.setFillAfter(true);
                imageViewOniate.startAnimation(animationmove);

                Animation myFadeInAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fadein);
                myFadeInAnimation.setStartOffset(500);
                imageViewLetrasOniate.startAnimation(myFadeInAnimation);

                imageViewCJ.startAnimation(myFadeInAnimation);

                imageViewUPV.startAnimation(myFadeInAnimation);

            }
        });


    }





}
