package com.app.didaktikapp.BBDD.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entidad ActividadUniversitatea que crea la tabla en la BBDD UNIVERSITATEA.
 * Autogenera el ID.
 * I have a pen, I have a apple.
 * @author gennakk
 */
@Entity(tableName = "UNIVERSITATEA")
public class ActividadUniversitatea  extends Actividad{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    @NonNull
    private Long id ;

    @ColumnInfo(name = "ESTADO")
    private Integer estado = 0;

    @ColumnInfo(name = "FRAGMENT")
    private Integer fragment  = 0 ;

    @ColumnInfo(name = "TEST")
    private String test;

    @ColumnInfo(name = "FOTO1")
    private String foto1;

    @ColumnInfo(name = "FOTO2")
    private String foto2;

    @ColumnInfo(name = "FOTO3")
    private String foto3;


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

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }
}
