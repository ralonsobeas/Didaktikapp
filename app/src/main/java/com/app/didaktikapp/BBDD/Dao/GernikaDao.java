package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadGernika;

import java.util.List;

@Dao
public interface GernikaDao {
    @Query("SELECT * FROM GERNIKA")
    List<ActividadGernika> getGernikas();

    @Query("SELECT * FROM GERNIKA WHERE ID LIKE :uuid")
    ActividadGernika getGernika(Long uuid);

    @Insert
    Long addGernika(ActividadGernika gernika);

    @Delete
    void deleteGernika(ActividadGernika gernika);

    @Update
    void updateGernika(ActividadGernika gernika);
}
