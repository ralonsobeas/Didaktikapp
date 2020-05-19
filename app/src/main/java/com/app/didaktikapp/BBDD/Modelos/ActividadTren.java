package com.app.didaktikapp.BBDD.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidad ActividadTren que crea la tabla en la BBDD TREN.
 * Autogenera el ID.
 * P-P-A-P
 * @author gennakk
 */
@Entity(tableName = "TREN")
public class ActividadTren  extends Actividad{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    @NonNull
    private Long id ;

    @ColumnInfo(name = "ESTADO")
    private Integer estado  = 0 ;

    @ColumnInfo(name = "FRAGMENT")
    private Integer fragment = 0 ;

    @ColumnInfo(name = "PUZLE")
    private String puzle;

    @ColumnInfo(name = "PALABRA")
    private String palabra;


    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getFragment() {
        return fragment;
    }

    public void setFragment(Integer fragment) {
        this.fragment = fragment;
    }

    public String getPuzle() {
        return puzle;
    }

    public void setPuzle(String puzle) {
        this.puzle = puzle;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
}
