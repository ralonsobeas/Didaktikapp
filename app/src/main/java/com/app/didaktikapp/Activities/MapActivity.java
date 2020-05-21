package com.app.didaktikapp.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel;
import com.app.didaktikapp.BBDD.Modelos.ActividadTren;
import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;
import com.app.didaktikapp.BBDD.Modelos.Grupo;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.app.didaktikapp.FTP.Ftp;
import com.app.didaktikapp.Fragments.FragmentErrepasoBatKotlin;
import com.app.didaktikapp.Fragments.FragmentErrotaFotos;
import com.app.didaktikapp.Fragments.FragmentErrotaTextos;
import com.app.didaktikapp.Fragments.FragmentVideo;
import com.app.didaktikapp.Fragments.FragmentGernikaTexto;
import com.app.didaktikapp.Fragments.FragmentPuzle;
import com.app.didaktikapp.Fragments.FragmentSanMiguel;
import com.app.didaktikapp.Fragments.FragmentSanMiguelImagenes;
import com.app.didaktikapp.Fragments.FragmentSanMiguelTinderKotlin;
import com.app.didaktikapp.Fragments.FragmentUnibertsitateaFotos;
import com.app.didaktikapp.Fragments.FragmentUnibertsitateaPreguntas;
import com.app.didaktikapp.Fragments.FragmentUnibertsitateaTexto;
import com.app.didaktikapp.Fragments.FragmentZumeltzegi;
import com.app.didaktikapp.Modelo.Lugar;
import com.app.didaktikapp.R;

import com.app.didaktikapp.wordsearch.features.gameplay.GamePlayActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;

import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;
import com.mapbox.mapboxsdk.offline.OfflineRegionError;
import com.mapbox.mapboxsdk.offline.OfflineRegionStatus;
import com.mapbox.mapboxsdk.offline.OfflineTilePyramidRegionDefinition;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;


import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineCap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineJoin;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineWidth;


/**
 *
 * Gestiona el mapa, la localización y los lanzamientos de los fragment.
 * @author gennakk
 *
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener
        , FragmentSanMiguel.OnFragmentInteractionListener
        , FragmentSanMiguelImagenes.OnFragmentInteractionListener
        , FragmentZumeltzegi.OnFragmentInteractionListener
        , FragmentSanMiguelTinderKotlin.OnFragmentInteractionListener
        , FragmentErrepasoBatKotlin.OnFragmentInteractionListener
        , FragmentPuzle.OnFragmentInteractionListener{

    private boolean isEndNotified;
    private ProgressBar progressBar;
    private OfflineManager offlineManager;
    private static Resources resources;

    // JSON encoding/decoding
    public static final String JSON_CHARSET = "UTF-8";
    public static final String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";


    private MapView mapView;
    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;

    private static final long DEFAULT_INTERVAL_IN_MILLISECONDS = 100L;
    private static final long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;

    public LocationEngine getLocationEngine() {
        return locationEngine;
    }

    public LocationChangeListeningActivityLocationCallback getCallback() {
        return callback;
    }

    private LocationEngine locationEngine;
    private LocationChangeListeningActivityLocationCallback callback =
            new LocationChangeListeningActivityLocationCallback(this);

    public LocationEngineRequest getLocationEngineRequest() {
        return locationEngineRequest;
    }

    private LocationEngineRequest locationEngineRequest;

    private final LocationEngineResult[] locationEngineResult = new LocationEngineResult[1];


    private String UNIQUE_LAYER_ID = "landuse";
    private Layer layer;

    private Location originLocation;

    private Context context;


    private Long idgrupo ;

    private Grupo grupo;

    public static Grupo GRUPO_S;

    private static final double DISTANCIA_MARKER = 15;

    private static final LatLngBounds ONIATE_BOUNDS = new LatLngBounds.Builder()
        .include(new LatLng(43.042073, -2.422996)) // Northeast
        .include(new LatLng(43.028919, -2.405703)) // Southwest
        .build();

    private static ArrayList<Lugar> listaLugares;

    private List<Point> routeCoordinates;

    private boolean administrador ;


    private Style style;

    public void setStyle(Style style) {
        this.style = style;
    }

    // variables for calculating and drawing a route
    private DirectionsRoute currentRoute;

    private NavigationMapRoute navigationMapRoute;

    private View ventanaMarker;

    private FloatingActionButton btnRepaso1;
    private FloatingActionButton btnRepaso2;

    private MediaPlayer mediaPlayer ;

    private int cont = 0;

    /**
     *
     * Crea la actividad MapActivity
     * @author gennakk
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        this.context = getApplicationContext();
        permissionsManager = new PermissionsManager(this);
        resources = getResources();
        //CARGAR OBJETO GRUPO DE LA BBDD
        idgrupo = getIntent().getExtras().getLong("IDGRUPO");
        administrador = getIntent().getExtras().getBoolean("ADMINISTRADOR");

        //CUIDADO
        //administrador = true;

        grupo = DatabaseRepository.getAppDatabase().getGrupoDao().getGrupo(idgrupo);
        GRUPO_S = grupo;
        generateData();
        cargarListaLugares();

        // Mapbox Access token
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_map);

        btnRepaso1 = findViewById(R.id.btnRepaso1);
        btnRepaso2 = findViewById(R.id.btnRepaso2);

        if(administrador){
            btnRepaso1.setVisibility(View.VISIBLE);
            btnRepaso2.setVisibility(View.VISIBLE);

        }else{
            btnRepaso1.setVisibility(View.INVISIBLE);
            btnRepaso2.setVisibility(View.INVISIBLE);
        }

        btnRepaso1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                lanzarFragment(FragmentErrepasoBatKotlin.newInstance(grupo.getIdRepaso1()));

            }
        });

        btnRepaso2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, GamePlayActivity.class);
                intent.putExtra(GamePlayActivity.EXTRA_ROW_COUNT, 10);
                intent.putExtra(GamePlayActivity.EXTRA_COL_COUNT, 10);
                intent.putExtra(GamePlayActivity.NOMBRE_FRAGMENT, GamePlayActivity.FRAGMENT_ERREPASO2);
                startActivity(intent);


            }
        });



        ventanaMarker = findViewById(R.id.ventana_marker);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        TextView textView = findViewById(R.id.tvAdmin);
        if(administrador){

            textView.setVisibility(View.VISIBLE);

        }else{

            textView.setVisibility(View.INVISIBLE);

        }

        ImageView ivLogo = findViewById(R.id.ivLogo);

        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        //AUDIO DEL PRINCIPIO
        ActividadZumeltzegi actividadZumeltzegi =   DatabaseRepository.getAppDatabase().getZumeltzegiDao().getZumeltzegi(grupo.getIdZumeltzegi());
        int fragment = actividadZumeltzegi.getFragment();

//        if(fragment == 0){
//            mediaPlayer = MediaPlayer.create(context, R.raw.trena);
//            mediaPlayer.start();
//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//
//
//                }
//
//            });
//
//        }



    }

    /**
     *
     * Crea los estilos,los marcadores y descarga el mapa cuando el mapa está listo.
     * Crea los listener de los markers dependiendo de las coordenadas lanza diferentes fragments.
     * Crea capas para las rutas.
     * @author gennakk
     *
     */
    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {

        this.mapboxMap = mapboxMap;



        mapboxMap.setCameraPosition(new CameraPosition.Builder()
                .zoom(10)
                .build());


        // Limites de Getxo
        mapboxMap.setLatLngBoundsForCameraTarget(ONIATE_BOUNDS);

        // Visualise bounds area
        showBoundsArea(mapboxMap);

        crearIconos();


        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {

                //OFFLINE
                // Set up the OfflineManager
                offlineManager = OfflineManager.getInstance(MapActivity.this);


                // Define the offline region
                OfflineTilePyramidRegionDefinition definition = new OfflineTilePyramidRegionDefinition(
                        style.getUri(),
                        ONIATE_BOUNDS,
                        10,
                        20,
                        MapActivity.this.getResources().getDisplayMetrics().density);

                // Set the metadata
                byte[] metadata;
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(JSON_FIELD_REGION_NAME, "Yosemite National Park");
                    String json = jsonObject.toString();
                    metadata = json.getBytes(JSON_CHARSET);
                } catch (Exception exception) {
                    Timber.e("Failed to encode metadata: %s", exception.getMessage());
                    metadata = null;
                }

                // Create the region asynchronously
                if (metadata != null) {
                    offlineManager.createOfflineRegion(
                            definition,
                            metadata,
                            new OfflineManager.CreateOfflineRegionCallback() {
                                @Override
                                public void onCreate(OfflineRegion offlineRegion) {
                                    offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);

                                    // Display the download progress bar
                                    progressBar = findViewById(R.id.progress_bar);
                                    startProgress();

                                    // Monitor the download progress using setObserver
                                    offlineRegion.setObserver(new OfflineRegion.OfflineRegionObserver() {
                                        @Override
                                        public void onStatusChanged(OfflineRegionStatus status) {

                                            // Calculate the download percentage and update the progress bar
                                            double percentage = status.getRequiredResourceCount() >= 0
                                                    ? (100.0 * status.getCompletedResourceCount() / status.getRequiredResourceCount()) :
                                                    0.0;

                                            if (status.isComplete()) {
                                                // Download complete
                                                endProgress("DESCARGADO");
                                            } else if (status.isRequiredResourceCountPrecise()) {
                                                // Switch to determinate state
                                                setPercentage((int) Math.round(percentage));
                                            }
                                        }

                                        @Override
                                        public void onError(OfflineRegionError error) {
                                            // If an error occurs, print to logcat
                                            Timber.e("onError reason: %s", error.getReason());
                                            Timber.e("onError message: %s", error.getMessage());
                                        }

                                        @Override
                                        public void mapboxTileCountLimitExceeded(long limit) {
                                            // Notify if offline region exceeds maximum tile count
                                            Timber.e("Mapbox tile count limit exceeded: %s", limit);
                                        }
                                    });
                                }

                                @Override
                                public void onError(String error) {
                                    Timber.e("Error: %s", error);
                                }
                            });
                }


                // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                enableLocationComponent(style);
                actualizarMarkerLinea(-2.41288, 43.03500, -2.413917, 43.033417);
                // Create the LineString from the list of coordinates and then make a GeoJSON
                // FeatureCollection so we can add the line to our map as a layer.
                style.addSource(new GeoJsonSource("line-source",
                        FeatureCollection.fromFeatures(new Feature[]{Feature.fromGeometry(
                                LineString.fromLngLats(routeCoordinates)
                        )})));


                // The layer properties for our line. This is where we make the line dotted, set the
                // color, etc.
                style.addLayer(new LineLayer("linelayer", "line-source").withProperties(
                        PropertyFactory.lineDasharray(new Float[]{0.01f, 2f}),
                        lineCap(Property.LINE_CAP_ROUND),
                        lineJoin(Property.LINE_JOIN_ROUND),
                        lineWidth(5f),
                        lineColor(getColor(R.color.colorPrimaryDark))

                ));
                LineLayer routeLayer = new LineLayer("aa", "AA");
                routeLayer.setProperties(
                        lineCap(Property.LINE_CAP_ROUND),
                        lineJoin(Property.LINE_JOIN_ROUND),
                        lineWidth(5f),
                        lineColor(Color.parseColor("#009688"))
                );
                style.addLayer(routeLayer);


                setStyle(style);







            }


        });

        //RUTA
        dibujarRuta(1);


        // Lanza los fragments dependiendo de las coordenadas del marcador que se clicken

        mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {


//                IconFactory iconFactory = IconFactory.getInstance(context);
//                Icon iconorojo = iconFactory.fromResource(R.drawable.pin_sinhacer);
//                Icon iconoamarillo = iconFactory.fromResource(R.drawable.pin_empezado);
//                Icon iconoverde = iconFactory.fromResource(R.drawable.pin_hecho);
//                SQLiteControlador sql = new SQLiteControlador(getApplicationContext());

                /*
                * Por cada punto con actividades que hay en el mapa, se comprueba el estado en
                * el que está, si hecho, empezado o deshabilitado, se procede a poner el icono
                * y se determina si se abre la actividad o no*/
                double latitud = 0;
                double longitud = 0;
                if(!administrador) {
                    Location location = locationEngineResult[0].getLastLocation();


                    latitud = location.getLatitude();
                    longitud = location.getLongitude();
                }


                double markerLatitud = marker.getPosition().getLatitude();
                double markerLongitud = marker.getPosition().getLongitude();



                //ZUMELTZEGI DORREA (1)
                if( (administrador && markerLatitud == 43.035000 && markerLongitud == -2.412889) || (distanciaCoord(latitud,longitud,43.035000,-2.412889))&& markerLatitud == 43.035000 && markerLongitud == -2.412889){
                    dibujarRuta(2);

                    ActividadZumeltzegi actividadZumeltzegi =   DatabaseRepository.getAppDatabase().getZumeltzegiDao().getZumeltzegi(grupo.getIdZumeltzegi());
                    int estado = actividadZumeltzegi.getEstado();
                    int fragment = actividadZumeltzegi.getFragment();

                    marker.setIcon(iconoPunto(estado));
                    if (entrarEnPunto(estado)) {
                        switch (fragment){
                            case 0:
                                lanzarFragment(FragmentZumeltzegi.newInstance(grupo.getIdZumeltzegi()));
                                break;
                            case 1:
                                Intent intent = new Intent(context, GamePlayActivity.class);
                                intent.putExtra(GamePlayActivity.EXTRA_ROW_COUNT, 10);
                                intent.putExtra(GamePlayActivity.EXTRA_COL_COUNT, 10);
                                intent.putExtra(GamePlayActivity.NOMBRE_FRAGMENT, GamePlayActivity.FRAGMENT_ZUMELTZEGI);
                                startActivity(intent);
                                break;
                        }
                    }


                }
                //SAN MIGUEL PARROKIA (2)
                else if((administrador && markerLatitud == 43.033417 && markerLongitud == -2.413917) ||  (distanciaCoord(latitud,longitud,43.033417,-2.413917))&& markerLatitud == 43.033417 && markerLongitud == -2.413917){
                    dibujarRuta(3);
                    ActividadSanMiguel actividadSanMiguel = DatabaseRepository.getAppDatabase().getSanMiguelDao().getSanMiguel(grupo.getIdParroquia());
                    int estado = actividadSanMiguel.getEstado();
                    int fragment = actividadSanMiguel.getFragment();



                    marker.setIcon(iconoPunto(estado));
                    if (entrarEnPunto(estado)) {
                        switch (fragment){
                            case 0:
                                lanzarFragment(FragmentVideo.newInstance(grupo.getIdParroquia(),"parrokia"));
                                break;
                            case 1:
                                lanzarFragment(FragmentSanMiguelTinderKotlin.newInstance(grupo.getIdParroquia()));
                                break;
                        }
                    }

                }
                //UNIBERTSITATEA (3)
                else if( (administrador && markerLatitud == 43.033944 && markerLongitud == -2.415361) ||  (distanciaCoord(latitud,longitud,43.033944,-2.415361))&& markerLatitud == 43.033944 && markerLongitud == -2.415361){
                    dibujarRuta(4);
                    ActividadUniversitatea actividadUniversitatea = DatabaseRepository.getAppDatabase().getUniversitateaDao().getUniversitatea(grupo.getIdUniversidad());
                    int estado = actividadUniversitatea.getEstado();
                    int fragment = actividadUniversitatea.getFragment();

                    marker.setIcon(iconoPunto(estado));
                    if (entrarEnPunto(estado)) {
                        switch (fragment){
                            case 0:
                                lanzarFragment(FragmentUnibertsitateaTexto.newInstance(grupo.getIdUniversidad()));
                                break;
                            case 1:
                                lanzarFragment(FragmentUnibertsitateaPreguntas.newInstance(grupo.getIdUniversidad()));
                                break;
                            case 2:
                                lanzarFragment(FragmentUnibertsitateaFotos.newInstance(grupo.getIdUniversidad()));
                                break;

                        }
                    }

                }
                //TRENA (4)
                else if( (administrador && markerLatitud == 43.033833 && markerLongitud == -2.416111) ||  (distanciaCoord(latitud,longitud,43.033833,-2.416111))&& markerLatitud == 43.033833 && markerLongitud == -2.416111){
                    dibujarRuta(5);
                    ActividadTren actividadTren = DatabaseRepository.getAppDatabase().getTrenDao().getTren(grupo.getIdTren());

                    int estado = actividadTren.getEstado();
                    int fragment = actividadTren.getFragment();

                    //FALTA POR HACER BBDD GUARDAR SOPA Y FALTA POR PASAR IDACTIVIDAD

                    marker.setIcon(iconoPunto(estado));
                    if (entrarEnPunto(estado)) {
                        lanzarFragment(FragmentPuzle.newInstance(grupo.getIdTren(),R.drawable.tren));
//                        Intent intent = new Intent(MapActivity.this, PuzzleActivity.class);
//                        startActivity(intent);
                    }

                }
                //SAN MIGUEL ERROTA (5) FALTA BBDD
                else if( (administrador && markerLatitud == 43.032917 && markerLongitud == -2.415750) ||  (distanciaCoord(latitud,longitud,43.032917,-2.415750))&& markerLatitud == 43.032917 && markerLongitud == -2.415750){
                    dibujarRuta(6);
                    ActividadErrota actividadErrota =   DatabaseRepository.getAppDatabase().getErrotaDao().getErrota(grupo.getIdErrota());

                    int estado = actividadErrota.getEstado();
                    int fragment = actividadErrota.getFragment();

                    marker.setIcon(iconoPunto(estado));
                    if (entrarEnPunto(estado)) {
                        lanzarFragment(FragmentErrotaTextos.newInstance(grupo.getIdErrota()));
                    }

                    if (entrarEnPunto(estado)) {
                        switch (fragment){
                            case 0:
                                lanzarFragment(FragmentErrotaTextos.newInstance(grupo.getIdErrota()));
                                break;
                            case 1:
                                lanzarFragment(FragmentVideo.newInstance(grupo.getIdErrota(),"errota"));
                                break;
                            case 2:
                                lanzarFragment(FragmentErrotaFotos.newInstance(grupo.getIdErrota()));
                                break;

                        }
                    }

                }
                //GERNIKA
                else if( (administrador && markerLatitud == 43.032444 && markerLongitud == -2.413722) ||  (distanciaCoord(latitud,longitud,43.032444,-2.413722))&& markerLatitud == 43.032444 && markerLongitud == -2.413722){

                    lanzarFragment(FragmentGernikaTexto.newInstance(grupo.getIdGernika()));



                }
                //ARAOTZ ASUA (sin uso, arriba)
                else if( (administrador && markerLatitud == 43.009139 && markerLongitud == -2.431444) ||  (distanciaCoord(latitud,longitud,43.009139,-2.431444))&& markerLatitud == 43.009139 && markerLongitud == -2.431444){


                }
                //ARRIKRUTZEKO KOBAK (sin uso, en medio)
                else if( (administrador && markerLatitud == 43.000583 && markerLongitud == -2.433250) ||  (distanciaCoord(latitud,longitud,43.000583,-2.433250))&& markerLatitud == 43.000583 && markerLongitud == -2.433250){
//                    marker.setIcon(iconoPunto(2));
//                    Intent intent = new Intent(MapActivity.this, GamePlayActivity.class);
//                    intent.putExtra(GamePlayActivity.EXTRA_ROW_COUNT, 10);
//                    intent.putExtra(GamePlayActivity.EXTRA_COL_COUNT, 10);
//                    intent.putExtra("listaPalabras", "words_errepaso2.xml");
//                    startActivity(intent);

                }
                //ARANTZAZUKO SANTUTEGIA (sin uso, abajo)
                else if( (administrador && markerLatitud == 42.979194 && markerLongitud == -2.398583) ||  (distanciaCoord(latitud,longitud,42.979194,-2.398583))&& markerLatitud == 42.979194 && markerLongitud == -2.398583){
//                    marker.setIcon(iconoPunto(2));
//                    lanzarFragment(FragmentErrepasoBatKotlin.newInstance(grupo.getIdParroquia()));
//                    lanzarFragment(FragmentErrotaFotos.newInstance(grupo.getIdTren()));
                }

                return false;
            }
        });




    }

    /**
     *
     * Crea las rutas
     * @author gennakk
     * @param numruta Número de ruta para crear la ruta correspondiente.
     *
     */
    private void dibujarRuta(int numruta) {

        new LoadGeoJson(MapActivity.this,numruta).execute();


    }


    /**
     *
     * Clase que se encarga de leer el GeoJson con la ruta
     * @author gennakk
     *
     */
    private static class LoadGeoJson extends AsyncTask<Void, Void, FeatureCollection> {

        private WeakReference<MapActivity> weakReference;
        private String json;
        private int numruta;

        /**
         *
         * Constructor de la clase GeoJson. Carga diferentes json dependiendo de numruta.
         * @author gennakk
         * @param activity Actividad MapActivity.
         * @param numruta Número de ruta correspondiente para cargar el json.
         *
         */
        LoadGeoJson(MapActivity activity, int numruta) {
            this.weakReference = new WeakReference<MapActivity>(activity);
            this.numruta = numruta;
            switch(numruta){
                case 1:
                    this.json = "esc-zum.geojson";
                    break;
                case 2:
                    this.json = "zum-sanmig.geojson";
                    break;
                case 3:
                    this.json = "sanmig-univ.geojson";
                    break;
                case 4:
                    this.json = "univ-tren.geojson";
                    break;
                case 5:
                    this.json = "tren-errota.geojson";
                    break;
                case 6:
                    this.json = "errota-gernika.geojson";
                    break;

            }
        }

        /**
         *
         * Lee en background el Json de la ruta
         * @author gennakk
         *
         */
        @Override
        protected FeatureCollection doInBackground(Void... voids) {
            try {
                MapActivity activity = weakReference.get();
                if (activity != null) {
                    InputStream inputStream = activity.getAssets().open(this.json);

                    return FeatureCollection.fromJson(convertStreamToString(inputStream));
                }
            } catch (Exception exception) {

                Timber.e("Exception Loading GeoJSON: %s" , exception.toString());
            }
            return null;
        }

        /**
         *
         * Convierte el Stream a un String
         * @author gennakk
         * @param is InputStream de entrada con el Json
         *
         */
        static String convertStreamToString(InputStream is) {
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }

        /**
         *
         * Despues de ejecutar la carga del json se ejecuta para dibujar la ruta.
         * @author gennakk
         * @param featureCollection Colección para renderizar la ruta.
         */
        @Override
        protected void onPostExecute(@Nullable FeatureCollection featureCollection) {
            super.onPostExecute(featureCollection);
            MapActivity activity = weakReference.get();


            if (activity != null && featureCollection != null) {
                activity.drawLines(featureCollection,this.numruta);
            }
        }
    }

    //http://geojson.io/#map=18/43.03427/-2.41376
    /**
     *
     * Dibuja la línea de la ruta en el mapa
     * @author gennakk
     * @param featureCollection Colección para renderizar la ruta.
     * @param num Recibe un número de ruta para asignar a Source y Layer.
     *
     */
    private void drawLines(@NonNull FeatureCollection featureCollection,int num) {
        if (mapboxMap != null) {
            mapboxMap.getStyle(style -> {
                if (featureCollection.features() != null) {
                    if (featureCollection.features().size() > 0) {
                        if(style.getSource("line-source"+num)==null)
//                            style.removeSource("line-source"+num);
                            style.addSource(new GeoJsonSource("line-source"+num, featureCollection));

                    // The layer properties for our line. This is where we make the line dotted, set the
                    // color, etc.
                        if(style.getLayer("linelayer")!=null)
                            style.removeLayer("linelayer");
                        style.addLayer(new LineLayer("linelayer", "line-source"+num)
                                .withProperties(
                                        PropertyFactory.lineDasharray(new Float[]{0.01f, 2f}),
                                        lineCap(Property.LINE_CAP_ROUND),
                                        lineJoin(Property.LINE_JOIN_ROUND),
                                        lineWidth(5f),
                                        lineColor(getColor(R.color.colorPrimaryDark)),
                                        PropertyFactory.lineOpacity(.7f)));
                    }
                }
            });
        }
    }

    /**
     *
     * Lanza el fragment pasado por parámetro.
     * @author gennakk
     * @param fragment Fragment que se desea lanzar.
     *
     */
    private void lanzarFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.commit();
        transaction.addToBackStack("Fragment");

        btnRepaso1.setVisibility(View.INVISIBLE);
        btnRepaso2.setVisibility(View.INVISIBLE);

        ventanaMarker.setVisibility(View.INVISIBLE);


    }

    /**
     *
     * Devuelve un icono dependiendo del estado
     * @author gennakk
     * @param estado Estado de la actividad.
     * @return Devuelve un Icon con el color correspondiente.
     *
     */
    private Icon iconoPunto(int estado) {
        IconFactory iconFactory = IconFactory.getInstance(context);
        if (estado==-1) return iconFactory.fromResource(R.drawable.pin_sinhacer);
        else if (estado==2) return iconFactory.fromResource(R.drawable.pin_hecho);
        else return iconFactory.fromResource(R.drawable.pin_empezado);
    }

    /**
     *
     * Devuelve si se puede entrar en el estado
     * @author gennakk
     * @return Devuelve true o false dependiendo del valor
     *
     */
    private boolean entrarEnPunto(int estado) {
        return estado == 0 || estado == 1;
    }

    /**
     *
     * Tamaño a mostrar del mapa
     * @author gennakk
     * @param mapboxMap Mapa usado por MapActivity.
     *
     */
    private void showBoundsArea(MapboxMap mapboxMap) {

        //Delimitamos los limites del mapa en la pantalla -> Getxo
        PolygonOptions boundsArea = new PolygonOptions()
                .add(ONIATE_BOUNDS.getNorthWest())
                .add(ONIATE_BOUNDS.getNorthEast())
                .add(ONIATE_BOUNDS.getSouthEast())
                .add(ONIATE_BOUNDS.getSouthWest());
        // Ajusta la transparencia del area seleccionada | 0 = transparente y 1 = opaco
        boundsArea.alpha(0f);
        mapboxMap.addPolygon(boundsArea);
    }

    /**
     *
     * Actualiza la línea de la ruta
     * @author gennakk
     * @param latOrigen Latitud de origen
     * @param lngOrigen Longitud de origen
     * @param latDestino Latitud de destino
     * @param lngDestino Longitud de destino
     *
     */
    public void actualizarMarkerLinea(Double lngOrigen, Double latOrigen, Double lngDestino, Double latDestino) {
        routeCoordinates = new ArrayList<>();
        routeCoordinates.add(Point.fromLngLat(lngOrigen, latOrigen));
        routeCoordinates.add(Point.fromLngLat(lngDestino, latDestino));

        drawNavigationPolylineRoute(routeCoordinates);


    }

    /**
     * Update the GeoJson data that's part of the LineLayer.
     * @author gennakk
     * @param routeCoordinates Lista de puntos con las coordenadas para la ruta.
     *
     */
    private void drawNavigationPolylineRoute(List<Point> routeCoordinates) {

        if (mapboxMap != null) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {


                    GeoJsonSource source = style.getSourceAs("line-source");
                    if (source != null) {
                        source.setGeoJson(FeatureCollection.fromFeatures(new Feature[]{Feature.fromGeometry(
                                LineString.fromLngLats(routeCoordinates))
                        }));
                    }

                }
            });
        }
    }

    /**
     *
     * Crea los iconos de cada marker dependiendo de su estado
     * @author gennakk
     *
     */
    private void crearIconos(){
        IconFactory iconFactory = IconFactory.getInstance(context);
        Icon iconorojo = iconFactory.fromResource(R.drawable.pin_sinhacer);
        Icon iconoamarillo = iconFactory.fromResource(R.drawable.pin_empezado);
//        Icon iconoamarillo = iconFactory.fromResource(R.drawable.yellow_marker);
        Icon iconoverde = iconFactory.fromResource(R.drawable.pin_hecho);
//        Icon iconogris = iconFactory.fromResource(R.drawable.grey_marker);
        Icon[] arrayIconos = {iconorojo,iconoamarillo,iconoverde};

//        for(Lugar lugar : listaLugares) {
//            mapboxMap.addMarker(new MarkerOptions()
//                    .position(lugar.getCoordenadas())
//                    .title(lugar.getNombre())
//                    .setIcon(iconorojo));
//        }

        /*
        * Al cargar el mapa, comprobamos la disponibilidad de los puntos
        * para asignarle el icono correspondiente por si está o no está hecho
        *
        * He comentado el anterior for para no perder el código*/
        for (int x=0;x<listaLugares.size();x++) {
            Icon icono = iconorojo;
            int dis;
            switch (x) {
                case 0:
                    //Selecciona el icono dependiendo del valor del estado que corresponderá al orden en el array de iconos
                    icono  = arrayIconos[DatabaseRepository.searchEstadoZumeltzegi(idgrupo)];
                    break;
                case 1:
                    icono  = arrayIconos[DatabaseRepository.searchEstadoSanMiguel(idgrupo)];
                    break;
                case 2:
                    icono  = arrayIconos[DatabaseRepository.searchEstadoUniversidad(idgrupo)];
                    break;
                case 3:
                    icono  = arrayIconos[DatabaseRepository.searchEstadoTren(idgrupo)];
                    break;
                case 4:
                    icono  = arrayIconos[DatabaseRepository.searchEstadoErrota(idgrupo)];

                    if(icono == iconoverde || administrador){

                        btnRepasoVisibles();

                    }
                    break;
            }
            Lugar lugar = listaLugares.get(x);
            mapboxMap.addMarker(new MarkerOptions()
                    .position(lugar.getCoordenadas())
                    .title(lugar.getNombre())
                    .setIcon(icono));


        }
    }

    /**
     *
     * Hace visible los botones de repaso con una animación.
     * @author gennakk
     *
     */
    private void btnRepasoVisibles(){
        /*
        if(administrador && cont == 1){
            cont++ ;
            return;
        }
        */

        TranslateAnimation animate = new TranslateAnimation(0,0,btnRepaso1.getHeight(),0);
        animate.setDuration(1000);
        animate.setFillAfter(true);
        //btnRepaso1.startAnimation(animate);
        btnRepaso1.setVisibility(View.VISIBLE);

        //btnRepaso2.startAnimation(animate);


        btnRepaso2.setVisibility(View.VISIBLE);
    }

    /**
     *
     * Carga en una lista los lugares para los markers.
     * @author gennakk
     *
     */
    private void cargarListaLugares(){

        listaLugares = new ArrayList<Lugar>();

        listaLugares.add(new Lugar(getString(R.string.nombreLugar1),new LatLng(43.035000, -2.412889)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar2),new LatLng(43.033944, -2.415361)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar3),new LatLng(43.033417, -2.413917)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar4),new LatLng(43.033833, -2.416111)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar5),new LatLng(43.032917,  -2.415750)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar6),new LatLng(42.979194,  -2.398583)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar7),new LatLng(43.009139,  -2.431444)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar8),new LatLng(43.000583, -2.433250)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar9), new LatLng(43.028668, -2.410399)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar10), new LatLng(43.032444, -2.413722)));


    }

    /**
     *
     * Realiza una tarea al recibir los permisos
     * @author gennakk
     *
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    /**
     *
     * Cambia el icono de localización del usuario y trackea la ubicación.
     * @author gennakk
     * @param loadedMapStyle Recibe el estilo del mapa.
     *
     */
    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            // Create and customize the LocationComponent's options
            LocationComponentOptions customLocationComponentOptions = LocationComponentOptions.builder(this)
                    .elevation(5)
                    .accuracyAlpha(.6f)
                    .foregroundDrawable(R.drawable.trotar)
                    .build();

        // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

        // Activate with options
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(this, loadedMapStyle)
                            .locationComponentOptions(customLocationComponentOptions)
                            .build());

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

            initLocationEngine();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }


    }

    /**
     * Set up the LocationEngine and the parameters for querying the device's location.
     * @author gennakk
     *
     */
    @SuppressLint("MissingPermission")
    public void initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(this);

        LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build();

        locationEngine.requestLocationUpdates(request, callback, getMainLooper());
        locationEngine.getLastLocation(callback);
    }

    /**
     *
     * Explicación de los permisos del mapa.
     * @author gennakk
     *
     */
    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    /**
     *
     * Carga el estilo del mapa si hay permisos.
     * @author gennakk
     * @param granted Indica si hay o no permisos.
     */
    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    /**
     *
     * Interacción con fragment.
     * @author gennakk
     * @param fragment Recibe el fragment.
     * @param terminado Recibe si se ha terminado.
     */
    @Override
    public void onFragmentInteraction(Fragment fragment,boolean terminado) {
        if(fragment instanceof FragmentSanMiguelImagenes) {
            Toast.makeText(this,"FRAGMENT",Toast.LENGTH_LONG).show();
            actualizarMarkerLinea(-3.413917, 43.033417, -2.431444, 43.033833);
        }
    }

    /**
     *
     * Interacción con fragment.
     * @author gennakk
     *
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     *
     * Listener de la localización.
     * @author gennakk
     *
     */
    private class LocationChangeListeningActivityLocationCallback
            implements LocationEngineCallback<LocationEngineResult> {

        private final WeakReference<MapActivity> activityWeakReference;

        LocationChangeListeningActivityLocationCallback(MapActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }



        /**
         * The LocationEngineCallback interface's method which fires when the device's location has changed.
         *
         * @param result the LocationEngineResult object which has the last known location within it.
         */
        @Override
        public void onSuccess(LocationEngineResult result) {

            if( locationEngineResult[0] == null)
                locationEngineResult[0] = result;

            MapActivity activity = activityWeakReference.get();

            if (activity != null) {
                Location location = result.getLastLocation();

                if (location == null) {
                    return;
                }

                // Create a Toast which displays the new location's coordinates
                for(Lugar lugar : listaLugares){
                    Location locAnterior = locationEngineResult[0].getLastLocation();
                    LatLng coordenadas = lugar.getCoordenadas();
                    if(locationEngineResult[0] == result
                            && distanciaCoord(location.getLatitude(),location.getLongitude(),coordenadas.getLatitude(),coordenadas.getLongitude())){
                        TranslateAnimation animate = new TranslateAnimation(0,0,ventanaMarker.getHeight(),0);
                        animate.setDuration(1000);
                        animate.setFillAfter(true);
                        ventanaMarker.startAnimation(animate);
                        ventanaMarker.setVisibility(View.VISIBLE);

                    }
                    if(!distanciaCoord(locAnterior.getLatitude(),locAnterior.getLongitude(),coordenadas.getLatitude(),coordenadas.getLongitude())
                        && distanciaCoord(location.getLatitude(),location.getLongitude(),coordenadas.getLatitude(),coordenadas.getLongitude())){
                        ventanaMarker.clearAnimation();
                        TranslateAnimation animate = new TranslateAnimation(0,0,ventanaMarker.getHeight(),0);
                        animate.setDuration(1000);
                        animate.setFillAfter(true);
                        ventanaMarker.startAnimation(animate);
                        ventanaMarker.setVisibility(View.VISIBLE);

                    }

                    if(distanciaCoord(locAnterior.getLatitude(),locAnterior.getLongitude(),coordenadas.getLatitude(),coordenadas.getLongitude())
                            && !distanciaCoord(location.getLatitude(),location.getLongitude(),coordenadas.getLatitude(),coordenadas.getLongitude())){
                        ventanaMarker.clearAnimation();
                        TranslateAnimation animate = new TranslateAnimation(0,0,0,ventanaMarker.getHeight());
                        animate.setDuration(1000);
                        animate.setFillAfter(true);
                        ventanaMarker.startAnimation(animate);
                        ventanaMarker.setVisibility(View.INVISIBLE);

                    }
                }


                double latitud = result.getLastLocation().getLatitude();

                double longitud = result.getLastLocation().getLongitude();

                // Pass the new location to the Maps SDK's LocationComponent
                if (activity.mapboxMap != null && result.getLastLocation() != null) {
                    activity.mapboxMap.getLocationComponent().forceLocationUpdate(result.getLastLocation());
                }


//                //Lanzar fragments cuando la distancia sea corta a los puntos.
//                Toast.makeText(activity,latitud+", "+longitud,Toast.LENGTH_SHORT).show();
//                Log.i("LATITUD",Double.toString(latitud));
//                Log.i("LONGITUD",Double.toString(longitud));
//
//                lanzarFragmentPorDistancia(latitud,longitud);

                locationEngineResult[0] = result;

                crearIconos();
            }
        }



        /**
         * The LocationEngineCallback interface's method which fires when the device's location can't be captured
         *
         * @param exception the exception message
         */
        @Override
        public void onFailure(@NonNull Exception exception) {
            Log.d("LocationChangeActivity", exception.getLocalizedMessage());
            MapActivity activity = activityWeakReference.get();
            if (activity != null) {
                Toast.makeText(activity, exception.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     *
     * Cálculo de distancia entre coordenadas.
     * @author gennakk
     * @param lat1 Latitud del primer punto.
     * @param lng1 Longitud del primer punto.
     * @param lat2 Latitud del segundo punto.
     * @param lng2 Longitud del segundo punto.
     *
     */
    public static boolean distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
        //double radioTierra = 3958.75;//en millas
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia*1000 <= DISTANCIA_MARKER;
    }


    /**
     *
     * Calcula el porcentaje de carga
     * @author gennakk
     * @param percentage Porcentaje.
     */
    private void setPercentage(final int percentage) {
        progressBar.setIndeterminate(false);
        progressBar.setProgress(percentage);
    }

    /**
     *
     * Barra de progreso inicio.
     * @author gennakk
     *
     */
    // Progress bar methods
    private void startProgress() {

        // Start and show the progress bar
        isEndNotified = false;
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     *
     * Barra de progreso final.
     * @author gennakk
     * @param message Mensaje de notificación.
     *
     */
    private void endProgress(final String message) {
        // Don't notify more than once
        if (isEndNotified) {
            return;
        }

        // Stop and hide the progress bar
        isEndNotified = true;
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);

        // Show a toast
//        Toast.makeText(MapActivity.this, message, Toast.LENGTH_LONG).show();
    }


    /**
     *
     * Se lanza cuando carga el fragment.
     * @author gennakk
     * @param fragment Fragment cargado.
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof FragmentSanMiguelImagenes) {
            ((FragmentSanMiguelImagenes) fragment).setmListener(this);
        }
    }




    /**
     *
     * Método onStart de MapActivity, carga al empezar.
     * @author gennakk
     *
     */
    @Override
    @SuppressWarnings( {"MissingPermission"})
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    /**
     *
     * Cambia la localización a una determinada
     * @author gennakk
     *
     */
    public void cambiarLocalizacion(){
        Location targetLocation = new Location("");//provider name is unnecessary
        targetLocation.setLatitude(0.0d);//your coords
        targetLocation.setLongitude(0.0d);

        LocationEngineResult locationEngineResults = LocationEngineResult.create(targetLocation);

        locationEngineResult[0] = locationEngineResults;

        crearIconos();
    }

    /**
     *
     * Método onResume de MapActivity, carga al volver a la actividad.
     * @author gennakk
     *
     */
    @Override
    public void onResume() {

        super.onResume();




        mapView.onResume();
    }

    /**
     *
     * Método onPause de MapActivity, carga al pausar la actividad.
     * Cierra la ventana de ayuda al pulsar si está activa para no solapar con los fragment.
     * @author gennakk
     *
     */
    @Override
    public void onPause() {
        super.onPause();

        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }

        if(ventanaMarker.getVisibility() == View.VISIBLE) {
            ventanaMarker.clearAnimation();
            TranslateAnimation animate = new TranslateAnimation(0, 0, 0, ventanaMarker.getHeight());
            animate.setDuration(1000);
            animate.setFillAfter(true);
            ventanaMarker.startAnimation(animate);
            ventanaMarker.setVisibility(View.INVISIBLE);
        }



        mapView.onPause();
    }


    /**
     *
     * Método onStop de MapActivity, carga al parar la actividad.
     * Para de pedir la localización del usuario.
     * @author gennakk
     *
     */
    @Override
    protected void onStop() {

        super.onStop();

        if (locationEngine != null) {
            locationEngine.removeLocationUpdates(callback);
        }

        mapView.onStop();
    }


    /**
     *
     * PPAP (Pen-Pineapple-Apple-Pen).
     * @author gennakk
     *
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     *
     * Método onDestroy de MapActivity, carga al matar la actividad.
     * @author gennakk
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
        mapView.onDestroy();
    }

    /**
     *
     * En caso de baja memoria se lanza.
     * @author gennakk
     *
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    /**
     *
     * Método que se ejecuta cuando se pulsa el botón de volver.
     * @author gennakk
     *
     */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    private void generateData(){


        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                DatabaseRepository.getAppDatabase().getGrupoDao().getGrupos();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {


                        List<Grupo> responseList;
                        responseList = DatabaseRepository.getAppDatabase().getGrupoDao().getGrupos();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Grupo>>(){}.getType();
                        String json = gson.toJson(responseList, type);
                        Log.v("upload result", "send?");


                        /***  Logic to set Data while creating worker **/
                        Constraints constraints = new Constraints.Builder()
                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                .build();
                        Data.Builder dataBuilder = new Data.Builder();
                        //Add parameter in Data class. just like bundle. You can also add Boolean and Number in parameter.
                        dataBuilder.putString(Ftp.JSON, json);
                        Data data =  dataBuilder.build();

                        OneTimeWorkRequest onetimeJob = new OneTimeWorkRequest.Builder(Ftp.class)
                                .setConstraints(constraints)
                                .setInputData(data).build(); // or PeriodicWorkRequest
                        //enque worker
                        WorkManager.getInstance().enqueue(onetimeJob);


                        Log.v("upload result", "sended");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MapActivity.this, "Empty data",Toast.LENGTH_LONG).show();
                    }
                });
    }
}




