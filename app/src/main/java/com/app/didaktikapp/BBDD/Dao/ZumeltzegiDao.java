package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;

import java.util.List;

@Dao
public interface ZumeltzegiDao {

    @Query("SELECT * FROM ZUMELTZEGI")
    List<ActividadZumeltzegi> getZumeltzegis();

    @Query("SELECT * FROM ZUMELTZEGI WHERE ID LIKE :uuid")
    ActividadZumeltzegi getZumeltzegi(Long uuid);

    @Insert
    Long addZumeltzegi(ActividadZumeltzegi zumeltzegi);

    @Delete
    void deleteZumeltzegi(ActividadZumeltzegi zumeltzegi);

    @Update
    void updateZumeltzegi(ActividadZumeltzegi zumeltzegi);
}
