package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;

import java.util.List;

@Dao
public interface ErrotaDao {
    @Query("SELECT * FROM ERROTA")
    List<ActividadErrota> getErrotas();

    @Query("SELECT * FROM ERROTA WHERE ID LIKE :uuid")
    ActividadErrota getErrota(Long uuid);

    @Insert
    Long addErrota(ActividadErrota errota);

    @Delete
    void deleteErrota(ActividadErrota errota);

    @Update
    void updateErrota(ActividadErrota errota);
}
