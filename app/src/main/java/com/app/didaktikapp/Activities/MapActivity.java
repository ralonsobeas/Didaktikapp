package com.app.didaktikapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.app.didaktikapp.Location.LocationListeningCallback;
import com.app.didaktikapp.Modelo.Lugar;
import com.app.didaktikapp.R;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineProvider;

import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
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
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.Layer;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, PermissionsListener {

    private MapView mapView;
    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;

    private LocationEngine locationEngine;
    private LocationEngineRequest locationEngineRequest;
    private LocationListeningCallback callback;

    private String UNIQUE_LAYER_ID = "landuse";
    private Layer layer;

    private Location originLocation;

    private Context context;


private static final LatLngBounds ONIATE_BOUNDS = new LatLngBounds.Builder()
        .include(new LatLng(43.042073, -2.422996)) // Northeast
        .include(new LatLng(43.028919, -2.405703)) // Southwest
        .build();

    private ArrayList<Lugar> listaLugares;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = getApplicationContext();
        permissionsManager = new PermissionsManager(this);


        cargarListaLugares();

        // Mapbox Access token
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_map);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);



    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {

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

                // Map is set up and the style has loaded. Now you can add data or make other map adjustments


            }
        });





        mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                IconFactory iconFactory = IconFactory.getInstance(context);
                Icon iconoverde = iconFactory.fromResource(R.drawable.pin_hecho);
                if(marker.getPosition().getLatitude()==43.035000 && marker.getPosition().getLongitude()==-2.412889){
                    marker.setIcon(iconoverde);
                }
                return false;
            }
        });

        enableLocation();


    }

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

    private void crearIconos(){
        IconFactory iconFactory = IconFactory.getInstance(context);
        Icon iconorojo = iconFactory.fromResource(R.drawable.pin2);
//        Icon iconoamarillo = iconFactory.fromResource(R.drawable.yellow_marker);
        Icon iconoverde = iconFactory.fromResource(R.drawable.pin_hecho);
//        Icon iconogris = iconFactory.fromResource(R.drawable.grey_marker);


        for(Lugar lugar : listaLugares) {
            mapboxMap.addMarker(new MarkerOptions()
                    .position(lugar.getCoordenadas())
                    .title(lugar.getNombre())
                    .setIcon(iconorojo));
        }
    }

    private void cargarListaLugares(){

        this.listaLugares = new ArrayList<Lugar>();

        listaLugares.add(new Lugar(getString(R.string.nombreLugar1),new LatLng(43.035000, -2.412889)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar2),new LatLng(43.033944, -2.415361)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar3),new LatLng(43.033417, -2.413917)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar4),new LatLng(43.033833, -2.416111)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar5),new LatLng(43.032917,  -2.415750)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar6),new LatLng(42.979194,  -2.398583)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar7),new LatLng(43.009139,  -2.431444)));
        listaLugares.add(new Lugar(getString(R.string.nombreLugar8),new LatLng(43.000583, -2.433250)));

    }


    private void enableLocation(){
        //condicion para comprobar los permisos que necesita la aplicacion
        if(PermissionsManager.areLocationPermissionsGranted(this)){
            initializeLocationEngine();
            initializeLocationLayer();
        }else{
            //La aplicacion no tiene permisos, se piden
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @SuppressWarnings("MissingPermission")
    private void initializeLocationEngine(){

        callback = new LocationListeningCallback(this);

        long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
        long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;

        locationEngine = LocationEngineProvider.getBestLocationEngine(context);

        LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_NO_POWER)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME)
                .build();

        locationEngine.requestLocationUpdates(request, callback, getMainLooper());
        locationEngine.getLastLocation(callback);




    }


    @SuppressWarnings("MissingPermission")
    private void initializeLocationLayer(){

//
//        //OPCIONES DEL ZOOM
//        LocationComponent options = LocationComponentOptions.builder(this)
//                .maxZoom(17)
//                .minZoom(12)
//                .build();
//
//
//
//        //locationPlayerPlugin, el punto de localizacion. En estas líneas le damos formato.
//        locationLayerPlugin = new LocationLayerPlugin(mapView, mapboxMap, locationEngine, options);
//        //locationLayerPlugin = new LocationLayerPlugin(mapView, mapboxMap, locationEngine);
//
//        locationLayerPlugin.setLocationLayerEnabled(true);
//        locationLayerPlugin.setCameraMode(CameraMode.TRACKING_COMPASS);
//        locationLayerPlugin.setRenderMode(RenderMode.COMPASS);

        LocationComponent locationComponent ;

        LocationComponentOptions locationComponentOptions = LocationComponentOptions.builder(this)
                .layerBelow(UNIQUE_LAYER_ID)
                .bearingTintColor(Color.RED)
                .build();

//-------------------------------------------------------_____----------------------_________------------
//        LocationComponentActivationOptions locationComponentActivationOptions = LocationComponentActivationOptions
//                .builder(this, mapboxMap.getStyle() )
//                .locationComponentOptions(locationComponentOptions)
//                .useDefaultLocationEngine(true)
//                .build();
//
//        locationComponent = mapboxMap.getLocationComponent();
//        locationComponent.activateLocationComponent(locationComponentActivationOptions);


    }


    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {

    }


    @Override
    protected void onStop() {
        super.onStop();

        if (locationEngine != null) {
            locationEngine.removeLocationUpdates(callback);
        }

        mapView.onStop();
    }
}
