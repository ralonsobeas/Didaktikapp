package com.app.didaktikapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import com.app.didaktikapp.FlatDialog.FlatDialog;
import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.muddzdev.styleabletoast.StyleableToast;
import com.wooplr.spotlight.SpotlightConfig;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;
import com.wooplr.spotlight.shape.Circle;
import com.wooplr.spotlight.utils.SpotlightSequence;
import com.wooplr.spotlight.utils.Utils;


import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity  {

    private ConstraintLayout layout;
    private CircleButton  botonInicio,botonContinuar;
    private Button botonSalir;
    private DatabaseRepository databaseRepository;

    private boolean administrador = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        Stetho.initializeWithDefaults(this);


//        //BBDD
//        databaseRepository = new DatabaseRepository(MainActivity.this);
//
//        //SE CREA EL GRUPO Y TODOS LOS FRAGMENTS CON SU ESTADO Y FRAGMENT = 0
//        DatabaseRepository.insertTaskGrupo("NOMBREDEPRUEBA");


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

        boolean[] pulsadoAdministrador = {false,false,false};

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
//                        SQLiteControlador sql = new SQLiteControlador(getApplicationContext());
//                        sql.iniciarApp();

                        dialogoCrearGrupo();



                        break;
                    case 1:
                        //startActivity(i);
                        dialogoElegirGrupo();

                        break;
                    case 2:
                        finish();
                        break;

                    case 3:
                        inicioAyuda();
                        break;
                    case 4:
                        dialogoCambiarIdioma();
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
                switch (index){
                    case 0:
                        pulsadoAdministrador[0] = true;
                        break;
                    case 1:
                        if(pulsadoAdministrador[0]){
                            pulsadoAdministrador[1]=true;
                        }else{
                            pulsadoAdministrador[0] = false;
                        }
                        break;
                    case 2:
                        if(pulsadoAdministrador[1]){
                            pulsadoAdministrador[2]=true;
                        }else{
                            pulsadoAdministrador[0] = false;
                            pulsadoAdministrador[1] = false;
                        }
                        break;

                    case 3:
                        if(pulsadoAdministrador[2]) {
                            activarModoAdministrador();
                        }
                        pulsadoAdministrador[0] = false;
                        pulsadoAdministrador[1] = false;
                        pulsadoAdministrador[2] = false;

                        break;

                }
            }
        });

//        GifImageView gifImageView = findViewById(R.id.gif);



    }

    private void activarModoAdministrador(){
        StyleableToast.makeText(getApplicationContext(), "Modo administrador activado", Toast.LENGTH_LONG, R.style.mytoast).show();
        administrador = true;
    }

    private void dialogoCambiarIdioma(){

        final FlatDialog flatDialog = new FlatDialog(MainActivity.this);
        flatDialog.setTitle("Cambiar idioma")
                .setBackgroundColor(Color.parseColor("#2B82C5"))
                .setSubtitle("cambia el idioma")
                .setFirstButtonText("Castellano")
                .setFirstButtonColor(Color.parseColor("#FAFAFA"))
                .setFirstButtonTextColor(Color.parseColor("#2B82C5"))
                .setSecondButtonText("Euskera")
                .setSecondButtonColor(Color.parseColor("#FAFAFA"))
                .setSecondButtonTextColor(Color.parseColor("#2B82C5"))
                .setThirdButtonText("Cancelar")
                .setThirdButtonColor(Color.parseColor("#ab000d"))
                .setThirdButtonTextColor(Color.parseColor("#FAFAFA"))
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setLocale("es");
                    }
                })
                .withSecondButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setLocale("eu");
                    }
                })
                .withThirdButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flatDialog.dismiss();
                    }
                })
                .show();

    }

    private void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }

    private void dialogoElegirGrupo(){
        //BBDD
        databaseRepository = new DatabaseRepository(MainActivity.this);

        List<Grupo> listaGrupos = DatabaseRepository.getAppDatabase().getGrupoDao().getGrupos();

        Grupo[] arrayGrupos =  new Grupo[listaGrupos.size()];
        listaGrupos.toArray(arrayGrupos);

        ArrayAdapter<Grupo> adapter = new ArrayAdapter<Grupo>(this,
                android.R.layout.simple_dropdown_item_1line, arrayGrupos );

        final Grupo[] grupoSeleccionado = new Grupo[1];


        final com.app.didaktikapp.FlatDialog.FlatDialog flatDialog = new com.app.didaktikapp.FlatDialog.FlatDialog(MainActivity.this);
        flatDialog.setTitle("Elegir grupo")
                .setBackgroundColor(Color.parseColor("#2B82C5"))
                .setSubtitle("introduce el nombre del grupo")
                .setFirstTextFieldHint("Nombre del grupo")
                .withTextViewAdapter(adapter)
                .setFirstButtonText("Comenzar")
                .setFirstButtonColor(Color.parseColor("#FAFAFA"))
                .setFirstButtonTextColor(Color.parseColor("#2B82C5"))
                .setSecondButtonText("Eliminar")
                .setSecondButtonColor(Color.parseColor("#FAFAFA"))
                .setSecondButtonTextColor(Color.parseColor("#2B82C5"))
                .setThirdButtonText("Cancelar")
                .setThirdButtonColor(Color.parseColor("#ab000d"))
                .setThirdButtonTextColor(Color.parseColor("#FAFAFA"))
                .withTextViewAdapterListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object item = parent.getItemAtPosition(position);
                        if (item instanceof Grupo){
                            Grupo grupo=(Grupo) item;
                            grupoSeleccionado[0] = grupo;
                        }
                    }
                })
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, MapActivity.class);


                        //BBDD

                        //SE CREA EL GRUPO Y TODOS LOS FRAGMENTS CON SU ESTADO Y FRAGMENT = 0

                        if(grupoSeleccionado[0]!=null && grupoSeleccionado[0].toString().equals(flatDialog.getFirstTextField())) {
                            i.putExtra("IDGRUPO", grupoSeleccionado[0].getId());

                            startActivity(i);
                            flatDialog.dismiss();
                        }else{
                            StyleableToast.makeText(getApplicationContext(), "Seleccione grupo", Toast.LENGTH_SHORT, R.style.mytoastIncorrecta  ).show();

                        }


                    }
                })
                .withSecondButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MainActivity.this, MapActivity.class);


                        //BBDD

                        //SE CREA EL GRUPO Y TODOS LOS FRAGMENTS CON SU ESTADO Y FRAGMENT = 0

                        if(grupoSeleccionado[0]!=null && grupoSeleccionado[0].toString().equals(flatDialog.getFirstTextField())) {
                            DatabaseRepository.deleteGrupo(grupoSeleccionado[0]);
                            flatDialog.getFirst_edit_text().setText("");
                            StyleableToast.makeText(getApplicationContext(), "Grupo eliminado", Toast.LENGTH_SHORT, R.style.mytoastCorrecta  ).show();

                        }else{
                            StyleableToast.makeText(getApplicationContext(), "Seleccione grupo", Toast.LENGTH_SHORT, R.style.mytoastIncorrecta  ).show();

                        }


                    }
                })
                .withThirdButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog.dismiss();
                    }
                })
                .show();
    }



    private void dialogoCrearGrupo(){
        final FlatDialog flatDialog = new FlatDialog(MainActivity.this);
        flatDialog.setTitle("Crear grupo")
                .setBackgroundColor(Color.parseColor("#2B82C5"))
                .setSubtitle("introduce el nombre del grupo")
                .setFirstTextFieldHint("Nombre del grupo")
                .setFirstButtonText("Comenzar")
                .setFirstButtonColor(Color.parseColor("#FAFAFA"))
                .setFirstButtonTextColor(Color.parseColor("#2B82C5"))
                .setSecondButtonText("Cancelar")
                .setSecondButtonColor(Color.parseColor("#ab000d"))
                .setSecondButtonTextColor(Color.parseColor("#FAFAFA"))
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, MapActivity.class);


                        //BBDD
                        databaseRepository = new DatabaseRepository(MainActivity.this);

                        //SE CREA EL GRUPO Y TODOS LOS FRAGMENTS CON SU ESTADO Y FRAGMENT = 0


                        i.putExtra("IDGRUPO",DatabaseRepository.insertTaskGrupo(flatDialog.getFirstTextField()));
                        i.putExtra("ADMINISTRADOR",administrador);
                        startActivity(i);
                        flatDialog.dismiss();
                    }
                })
                .withSecondButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog.dismiss();
                    }
                })
                .show();
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
        FloatingActionButton boton5 = findViewById(4);

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
                        .addSpotlight(boton5, "Idioma ", "Cambia el idioma", "circleMenuView5")
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
