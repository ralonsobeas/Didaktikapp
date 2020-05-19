package com.app.didaktikapp.BBDD.Modelos;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Entidad Usuario que crea la tabla en la BBDD USUARIO. Con sus correspondientes dependencias.
 * Autogenera el ID.
 * Uh! Pineapple-pen!
 * @author gennakk
 */
@Entity(tableName = "USUARIO",
        foreignKeys =@ForeignKey(entity = Grupo.class,
                parentColumns = "ID",
                childColumns = "IDGRUPO",
                onDelete = CASCADE))
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;

    @ColumnInfo(name = "NOMBRE")
    private String nombre;

    @ColumnInfo(name = "IDGRUPO")
    private Long idGrupo;


    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }
}
