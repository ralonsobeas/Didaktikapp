package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso1;

import java.util.List;

@Dao
public interface Repaso1Dao {
    @Query("SELECT * FROM REPASO1")
    List<ActividadRepaso1> getRepasos1();

    @Query("SELECT * FROM ERROTA WHERE ID LIKE :uuid")
    ActividadRepaso1 getRepaso1(Long uuid);

    @Insert
    Long addRepaso1(ActividadRepaso1 repaso1);

    @Delete
    void deleteRepaso1(ActividadRepaso1 repaso1);

    @Update
    void updateRepaso1(ActividadRepaso1 repaso1);
}
