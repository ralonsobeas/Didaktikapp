package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;

import java.util.List;

@Dao
public interface UniversitateaDao {

    @Query("SELECT * FROM UNIVERSITATEA")
    List<ActividadUniversitatea> getUniversitateas();

    @Query("SELECT * FROM UNIVERSITATEA WHERE ID LIKE :uuid")
    ActividadUniversitatea getUniversitatea(Long uuid);

    @Insert
    Long addUniversitatea(ActividadUniversitatea universitatea);

    @Delete
    void deleteUniversitatea(ActividadUniversitatea universitatea);

    @Update
    void updateUniversitatea(ActividadUniversitatea universitatea);
}
