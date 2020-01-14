package com.app.didaktikapp.BBDD.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SANMIGUEL")
public class ActividadSanMiguel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    @NonNull
    private Long id ;

    @ColumnInfo(name = "ESTADO")
    private Integer estado  = 0 ;

    @ColumnInfo(name = "FRAGMENT")
    private Integer fragment  = 0 ;

    @ColumnInfo(name = "TEST")
    private String test;

    @ColumnInfo(name = "FOTO1")
    private String fotos;



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

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String foto1) {
        this.fotos = foto1;
    }


}
