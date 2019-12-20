package com.app.didaktikapp.BBDD.Modelos;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "USUARIO")
public class Usuario {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "NOMBRE")
    private String nombre;

    @ColumnInfo(name = "IDGRUPO")
    private String idGrupo;

    public Usuario() {
        id = UUID.randomUUID().toString();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }
}
