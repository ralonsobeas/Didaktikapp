package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadTren;

import java.util.List;

@Dao
public interface TrenDao {

    @Query("SELECT * FROM TREN")
    List<ActividadTren> getTrenes();

    @Query("SELECT * FROM TREN WHERE ID LIKE :uuid")
    ActividadTren getTren(Long uuid);

    @Insert
    Long addTren(ActividadTren tren);

    @Delete
    void deleteTren(ActividadTren tren);

    @Update
    void updateTren(ActividadTren tren);
}
