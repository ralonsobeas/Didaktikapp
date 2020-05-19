package com.app.didaktikapp.BBDD.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Entidad ActividadZumeltzegi que crea la tabla en la BBDD ZUMELTZEGI.
 * Autogenera el ID.
 * Uh! Apple-pen!
 * @author gennakk
 */
@Entity(tableName = "ZUMELTZEGI")
public class ActividadZumeltzegi  extends Actividad{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    @NonNull
    private Long id ;

    @ColumnInfo(name = "ESTADO")
    private Integer estado = 0  ;

    @ColumnInfo(name = "FRAGMENT")
    private Integer fragment  = 0 ;

    @ColumnInfo(name = "FOTO1")
    private String foto1;

    @ColumnInfo(name = "FOTO2")
    private String foto2;

    @ColumnInfo(name = "SOPA")
    private String sopa;

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

    public String getSopa() {
        return sopa;
    }

    public void setSopa(String sopa) {
        this.sopa = sopa;
    }
}
