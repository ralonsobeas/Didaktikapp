package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel;

import java.util.List;

@Dao
public interface SanMiguelDao {
    @Query("SELECT * FROM SANMIGUEL")
    List<ActividadSanMiguel> getSanMigueles();

    @Query("SELECT * FROM SANMIGUEL WHERE ID LIKE :uuid")
    ActividadSanMiguel getSanMiguel(Long uuid);

    @Insert
    Long addSanMiguel(ActividadSanMiguel sanMiguel);

    @Delete
    void deleteSanMiguel(ActividadSanMiguel sanMiguel);

    @Update
    void updateSanMiguel(ActividadSanMiguel sanMiguel);
}
