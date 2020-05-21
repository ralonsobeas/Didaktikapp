package com.app.didaktikapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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


import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.Grupo;

import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.CircleMenu.CircleMenuView;

import com.app.didaktikapp.FTP.Ftp;
import com.app.didaktikapp.FlatDialog.FlatDialog;
import com.facebook.stetho.Stetho;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.markushi.ui.CircleButton;

/**
 * Activity principal encargada de lanzar el mapa, seguir una partida guardada y mostrar ayudas,
 * así como manejar la base de datos y mandar información por FTP
 * @author gennakk
 */
public class MainActivity extends AppCompatActivity  {

    private final int RC_SIGN_IN = 111;

    private ConstraintLayout layout;
    private CircleButton  botonInicio,botonContinuar;
    private Button botonSalir,botonFTP;
    private DatabaseRepository databaseRepository;

    private boolean administrador = false;

    private String email;

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount account;

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        account = GoogleSignIn.getLastSignedInAccount(this);
        if(account == null){
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        Stetho.initializeWithDefaults(this);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
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



        TextView tv= findViewById(R.id.tvTitulo);
        Typeface type =  ResourcesCompat.getFont(this, R.font.youthtouch);
        tv.setTypeface(type);
        tv.setText(Html.fromHtml(getString(R.string.html_app_name)));

        botonFTP = findViewById(R.id.botonFTP);
        botonFTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoFTP();
            }
        });
        if(administrador){
            botonFTP.setVisibility(View.VISIBLE);
        }else{
            botonFTP.setVisibility(View.INVISIBLE);
        }




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
            //Modo Admin
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
        StyleableToast.makeText(getApplicationContext(), getString(R.string.activarAdmin), Toast.LENGTH_LONG, R.style.mytoast).show();
        administrador = true;
        botonFTP.setVisibility(View.VISIBLE);
    }

    private void dialogoCambiarIdioma(){

        final FlatDialog flatDialog = new FlatDialog(MainActivity.this);
        flatDialog.setTitle(getString(R.string.ayudaTitulo))
                .setBackgroundColor(Color.parseColor("#2B82C5"))
                .setSubtitle(getString(R.string.ayudaSubtitulo))
                .setFirstButtonText(getString(R.string.ayudaCastellano))
                .setFirstButtonColor(Color.parseColor("#FAFAFA"))
                .setFirstButtonTextColor(Color.parseColor("#2B82C5"))
                .setSecondButtonText(getString(R.string.ayudaEuskera))
                .setSecondButtonColor(Color.parseColor("#FAFAFA"))
                .setSecondButtonTextColor(Color.parseColor("#2B82C5"))
                .setThirdButtonText(getString(R.string.Cancelar))
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
//Lengua
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
        flatDialog.setTitle(getString(R.string.elegirGrupoTitulo))
                .setBackgroundColor(Color.parseColor("#2B82C5"))
                .setSubtitle(getString(R.string.elegirGrupoSubtitulo))
                .setFirstTextFieldHint("Nombre del grupo")
                .withTextViewAdapter(adapter)
                .setFirstButtonText(getString(R.string.Comenzar))
                .setFirstButtonColor(Color.parseColor("#FAFAFA"))
                .setFirstButtonTextColor(Color.parseColor("#2B82C5"))
                .setSecondButtonText(getString(R.string.Eliminar))
                .setSecondButtonColor(Color.parseColor("#FAFAFA"))
                .setSecondButtonTextColor(Color.parseColor("#2B82C5"))
                .setThirdButtonText(getString(R.string.Cancelar))
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

                        databaseRepository = new DatabaseRepository(MainActivity.this);
                        //BBDD

                        //SE CREA EL GRUPO Y TODOS LOS FRAGMENTS CON SU ESTADO Y FRAGMENT = 0

                        if(grupoSeleccionado[0]!=null && grupoSeleccionado[0].toString().equals(flatDialog.getFirstTextField())) {
                            i.putExtra("IDGRUPO", grupoSeleccionado[0].getId());
                            i.putExtra("ADMINISTRADOR", administrador);

                            startActivity(i);
                            flatDialog.dismiss();
                        }else{
                            StyleableToast.makeText(getApplicationContext(), getString(R.string.elegirGrupoToastSeleccion), Toast.LENGTH_SHORT, R.style.mytoastIncorrecta  ).show();

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
                            StyleableToast.makeText(getApplicationContext(), getString(R.string.elegirGrupoToastEliminado), Toast.LENGTH_SHORT, R.style.mytoastCorrecta  ).show();

                        }else{
                            StyleableToast.makeText(getApplicationContext(), getString(R.string.elegirGrupoToastSeleccion), Toast.LENGTH_SHORT, R.style.mytoastIncorrecta  ).show();

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
        flatDialog.setTitle(getString(R.string.crearGrupoTitulo))
                .setBackgroundColor(Color.parseColor("#2B82C5"))
                .setSubtitle(getString(R.string.crearGrupoSubtitulo))
                .setFirstTextFieldHint(getString(R.string.crearGrupoHint))
                .setFirstButtonText(getString(R.string.Comenzar))
                .setFirstButtonColor(Color.parseColor("#FAFAFA"))
                .setFirstButtonTextColor(Color.parseColor("#2B82C5"))
                .setSecondButtonText(getString(R.string.Cancelar))
                .setSecondButtonColor(Color.parseColor("#ab000d"))
                .setSecondButtonTextColor(Color.parseColor("#FAFAFA"))
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, MapActivity.class);


                        //BBDD
                        databaseRepository = new DatabaseRepository(MainActivity.this);
                        email = null;



                        email = account.getEmail();
                        Log.i("EMAIL",email+"...");
                        if(email!=null) {
                            i.putExtra("IDGRUPO", DatabaseRepository.insertTaskGrupo(flatDialog.getFirstTextField(), email));
                            i.putExtra("ADMINISTRADOR", administrador);
                            startActivity(i);
                            flatDialog.dismiss();
                        }
                       /* while(email==null) {
                            //SE CREA EL GRUPO Y TODOS LOS FRAGMENTS CON SU ESTADO Y FRAGMENT = 0
                            // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
                             gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                    .requestEmail()
                                    .build();

                            // Build a GoogleSignInClient with the options specified by gso.
                             mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);

                             signInIntent = mGoogleSignInClient.getSignInIntent();
                            startActivityForResult(signInIntent, RC_SIGN_IN);

                            if(email!=null) {
                                i.putExtra("IDGRUPO", DatabaseRepository.insertTaskGrupo(flatDialog.getFirstTextField(), email));
                                i.putExtra("ADMINISTRADOR", administrador);
                                startActivity(i);
                                flatDialog.dismiss();
                            }
                        } */
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

    private void googleAcc(){

    }

//Prueba eventos
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
        TextView tvTitulo = findViewById(R.id.tvTitulo);
        CircleMenuView circleMenuView = findViewById(R.id.circle_menu);
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
        config.setPerformClick(false);
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
                java.util.Random rndGenerator = new java.util.Random();

                SpotlightSequence.getInstance(MainActivity.this,config)
                        .addSpotlight(boton1, getString(R.string.ayudaAnimTituloComienzo), getString(R.string.ayudaAnimSubtituloComienzo), "circleMenuView1" + rndGenerator.nextInt(999999999))
                        .addSpotlight(boton2,  getString(R.string.ayudaAnimTituloContinuar),  getString(R.string.ayudaAnimSubtituloContinuar), "circleMenuView2" + rndGenerator.nextInt(999999999))
                        .addSpotlight(boton3, getString(R.string.ayudaAnimTituloSalir), getString(R.string.ayudaAnimSubtituloSalir), "circleMenuView3" + rndGenerator.nextInt(999999999))
                        .addSpotlight(boton4, getString(R.string.ayudaAnimTituloAyuda), getString(R.string.ayudaAnimSubtituloAyuda), "circleMenuView4" + rndGenerator.nextInt(999999999))
                        .addSpotlight(boton5, getString(R.string.ayudaAnimTituloIdioma), getString(R.string.ayudaAnimSubtituloIdioma), "circleMenuView5" + rndGenerator.nextInt(999999999))
                        .startSequence();
            }
        }, 400);









    }

    private void dialogoFTP(){



        final com.app.didaktikapp.FlatDialog.FlatDialog flatDialog = new com.app.didaktikapp.FlatDialog.FlatDialog(MainActivity.this);
        flatDialog.setTitle(getString(R.string.TituloServidor))
                .setBackgroundColor(Color.parseColor("#2B82C5"))
                .setSubtitle(getString(R.string.SubituloServidor))
                .setFirstTextFieldHint(getString(R.string.HintServidor))
                .setFirstButtonText(getString(R.string.cambiar))
                .setFirstButtonColor(Color.parseColor("#FAFAFA"))
                .setFirstButtonTextColor(Color.parseColor("#2B82C5"))
                .setSecondButtonText(getString(R.string.Cancelar))
                .setSecondButtonColor(Color.parseColor("#ab000d"))
                .setSecondButtonTextColor(Color.parseColor("#FAFAFA"))
                .withFirstButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //cambiar ip servidor

                        String ip = flatDialog.getFirstTextField();

                        String regex = "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";

                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(ip);

                        if(matcher.matches()) {
                            Context context = getApplicationContext();
                            SharedPreferences sharedPref = context.getSharedPreferences(
                                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(getString(R.string.servidor), ip);
                            editor.apply();
                            StyleableToast.makeText(getApplicationContext(), getString(R.string.ipCorrecta), Toast.LENGTH_LONG, R.style.mytoastCorrecta).show();
                        }else{
                            StyleableToast.makeText(getApplicationContext(), getString(R.string.ipIncorrecta), Toast.LENGTH_LONG, R.style.mytoastIncorrecta).show();

                        }
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("SIGN","LOGIN0"+requestCode);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        //LOG IN
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            Log.i("SIGN","LOGIN1");
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);
            Log.i("SIGN","LOGIN2");
            // Signed in successfully, show authenticated UI.
            email = account.getEmail();
            Log.i("SIGN","LOGIN3"+email);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.i("SIGN", "signInResult:failed code=" + e.getStatusCode());
            email = null;
        }
    }
}
