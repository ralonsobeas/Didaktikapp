package com.app.didaktikapp.Modelo;

import androidx.fragment.app.Fragment;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.Objects;

/**
 * Clase que guarda un lugar en el mapa así como sus fragments
 * @author gennakk
 */
public class Lugar {

    private String nombre;
    private LatLng coordenadas;
    private Fragment fragment;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LatLng getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(LatLng coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Lugar(String nombre, LatLng coordenadas) {
        this.nombre = nombre;
        this.coordenadas = coordenadas;
    }



}
