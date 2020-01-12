package com.app.didaktikapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.AppExecutors;
import com.app.didaktikapp.BBDD.Modelos.Grupo;
import com.app.didaktikapp.BBDD.SQLiteControlador;
import com.app.didaktikapp.BBDD.Service.GrupoService;
import com.app.didaktikapp.BBDD.database.AppDatabase;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.CircleMenu.CircleMenuView;

import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;
import com.wooplr.spotlight.shape.Circle;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;


import java.lang.ref.WeakReference;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity  {

    private ConstraintLayout layout;
    private CircleButton  botonInicio,botonContinuar;
    private Button botonSalir;
    private DatabaseRepository databaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        Stetho.initializeWithDefaults(this);


        //BBDD
        databaseRepository = new DatabaseRepository(MainActivity.this);

        //SE CREA EL GRUPO Y TODOS LOS FRAGMENTS CON SU ESTADO Y FRAGMENT = 0
        DatabaseRepository.insertTaskGrupo("NOMBREDEPRUEBA");


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
        layout = findViewById(R.id.layout);



        TextView tv=(TextView)findViewById(R.id.tvTitulo);
        Typeface type =  ResourcesCompat.getFont(this, R.font.youthtouch);
        tv.setTypeface(type);
        tv.setText(Html.fromHtml(getString(R.string.html_app_name)));






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
                Intent i = new Intent(MainActivity.this, MapActivity.class);
                switch (index){
                    case 0:
//                        SQLiteControlador sql = new SQLiteControlador(getApplicationContext());
//                        sql.iniciarApp();

                        startActivity(i);

                        break;
                    case 1:
                        //startActivity(i);


                        break;
                    case 2:
                        finish();
                        break;

                    case 3:
                        inicioAyuda();
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

    private void inicioAyuda(){
        PreferencesManager mPreferencesManager = new PreferencesManager(MainActivity.this);
        mPreferencesManager.resetAll();
        TextView tvTitulo = (TextView)findViewById(R.id.tvTitulo);
        CircleMenuView circleMenuView = (CircleMenuView) findViewById(R.id.circle_menu);
        FloatingActionButton boton1 = findViewById(0);
        FloatingActionButton boton2 = findViewById(1);
        FloatingActionButton boton3 = findViewById(2);
        FloatingActionButton boton4 = findViewById(3);

        circleMenuView.open(true);




        SpotlightConfig config = new SpotlightConfig();
        config.setMaskColor( Color.parseColor("#E63A3A3A"));
        config.setIntroAnimationDuration(400);
        config.setFadingTextDuration(400);
        config.setPadding(20);
        config.setDismissOnTouch(true);
        config.setDismissOnBackpress(true);
        config.setPerformClick(true);
        config.setHeadingTvSize(24);
        config.setHeadingTvColor(Color.parseColor("#2B82C5"));
        config.setSubHeadingTvSize(24);
        config.setSubHeadingTvColor(Color.parseColor("#FAFAFA"));
        config.setLineAnimationDuration(300);
        config.setLineStroke(Utils.dpToPx(4));
        config.setLineAndArcColor( Color.parseColor("#2B82C5"));
        config.setShowTargetArc(true);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SpotlightSequence.getInstance(MainActivity.this,config)
                        .addSpotlight(boton1, "Comienzo ", "Inicia la partida", "circleMenuView1")
                        .addSpotlight(boton2, "Continuar ", "Continúa una partida", "circleMenuView2")
                        .addSpotlight(boton3, "Salir ", "Salir del juego", "circleMenuView3")
                        .addSpotlight(boton4, "Ayuda ", "Una pequeña ayuda", "circleMenuView4")

                        .startSequence();
            }
        }, 400);





    }




    private static class InsertTask extends AsyncTask<Void,Void,Boolean> {

        private WeakReference<MainActivity> activityReference;
        private Object object;

        // only retain a weak reference to the activity
        InsertTask(MainActivity context, Object object) {
            activityReference = new WeakReference<>(context);
            this.object = object;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected Boolean doInBackground(Void... objs) {
            if(object instanceof Grupo)
                DatabaseRepository.getAppDatabase().getGrupoDao().addGrupo((Grupo)object);
            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {
            if (bool){
                Log.i("AÑADIDO","AÑADIDO");
            }
        }

    }
}
