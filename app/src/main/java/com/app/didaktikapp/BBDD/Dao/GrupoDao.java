package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.Grupo;

import java.util.List;

@Dao
public interface GrupoDao {

    @Query("SELECT * FROM GRUPO")
    List<Grupo> getGrupos();

    @Query("SELECT * FROM GRUPO WHERE ID LIKE :uuid")
    Grupo getGrupo(String uuid);

    @Insert
    void addGrupo(Grupo grupo);

    @Delete
    void deleteGrupo(Grupo grupo);

    @Update
    void updateGrupo(Grupo grupo);
}
