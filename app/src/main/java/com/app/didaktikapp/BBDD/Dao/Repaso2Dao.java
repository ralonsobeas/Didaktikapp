package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso1;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso2;

import java.util.List;

@Dao
public interface Repaso2Dao {
    @Query("SELECT * FROM REPASO2")
    List<ActividadRepaso2> getRepasos2();

    @Query("SELECT * FROM ERROTA WHERE ID LIKE :uuid")
    ActividadRepaso2 getRepaso2(Long uuid);

    @Insert
    Long addRepaso2(ActividadRepaso2 repaso2);

    @Delete
    void deleteRepaso2(ActividadRepaso2 repaso2);

    @Update
    void updateRepaso2(ActividadRepaso2 repaso2);
}
