package com.app.didaktikapp.BBDD.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;


@Entity(tableName = "GRUPO")
public class Grupo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    @NonNull
    private Integer id ;

    @ColumnInfo(name = "NOMBRE")
    private String nombre;

    @ColumnInfo(name = "FECHA")
    private String fecha;

    @ColumnInfo(name = "IDZUMELTZEGI")
    private String idZumeltzegi;

    @ColumnInfo(name = "IDPARROQUIA")
    private String idParroquia;

    @ColumnInfo(name = "IDUNIVERSIDAD")
    private String idUniversidad;

    @ColumnInfo(name = "IDERROTA")
    private String idErrota;

    @ColumnInfo(name = "IDGERNIKA")
    private String idGernika;



    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdZumeltzegi() {
        return idZumeltzegi;
    }

    public void setIdZumeltzegi(String idZumeltzegi) {
        this.idZumeltzegi = idZumeltzegi;
    }

    public String getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(String idParroquia) {
        this.idParroquia = idParroquia;
    }

    public String getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(String idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public String getIdErrota() {
        return idErrota;
    }

    public void setIdErrota(String idErrota) {
        this.idErrota = idErrota;
    }

    public String getIdGernika() {
        return idGernika;
    }

    public void setIdGernika(String idGernika) {
        this.idGernika = idGernika;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", idZumeltzegi='" + idZumeltzegi + '\'' +
                ", idParroquia='" + idParroquia + '\'' +
                ", idUniversidad='" + idUniversidad + '\'' +
                ", idErrota='" + idErrota + '\'' +
                ", idGernika='" + idGernika + '\'' +
                '}';
    }
}
