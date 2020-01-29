package com.app.didaktikapp.BBDD.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "REPASO1")
public class ActividadRepaso1 {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    @NonNull
    private Long id ;

    @ColumnInfo(name = "ESTADO")
    private Integer estado  = 0 ;

    @ColumnInfo(name = "FRAGMENT")
    private Integer fragment  = 0 ;

    @ColumnInfo(name = "FRASES")
    private String frases;


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

    public String getFrases() {
        return frases;
    }

    public void setFrases(String frases) {
        this.frases = frases;
    }

}
