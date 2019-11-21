package com.app.didaktikapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.app.didaktikapp.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private MapboxMap mapboxMap;

    private Context context;


private static final LatLngBounds ONIATE_BOUNDS = new LatLngBounds.Builder()
        .include(new LatLng(43.042073, -2.422996)) // Northeast
        .include(new LatLng(43.028919, -2.405703)) // Southwest
        .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = getApplicationContext();

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
                .zoom(14)
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
//        Icon iconoverde = iconFactory.fromResource(R.drawable.green_marker);
//        Icon iconogris = iconFactory.fromResource(R.drawable.grey_marker);


        mapboxMap.addMarker(new MarkerOptions()
                .position(new LatLng(43.035000, -2.412889))
                .title("Zumeltzegi dorrea")
                .setIcon(iconorojo));
    }


}
