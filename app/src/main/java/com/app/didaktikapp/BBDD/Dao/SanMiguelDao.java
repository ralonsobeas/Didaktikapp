package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel;

import java.util.List;

/**
 * Clase intermedia para acceder a BBDD y clase ActividadSanMiguel.
 * @author gennakk
 */
@Dao
public interface SanMiguelDao {

    /**
     * Obtener todas las ActividadesSanMiguel.
     * @author gennakk
     * @return Lista de ActividadSanMiguel.
     */
    @Query("SELECT * FROM SANMIGUEL")
    List<ActividadSanMiguel> getSanMigueles();

    /**
     * Devuelve una ActividadSanMiguel por su ID.
     * @param uuid ID
     * @return ActividadSanMiguel
     * @author gennakk
     */
    @Query("SELECT * FROM SANMIGUEL WHERE ID LIKE :uuid")
    ActividadSanMiguel getSanMiguel(Long uuid);

    /**
     * Inserta una nueva ActividadSanMiguel.
     * @param sanMiguel ActividadSanMiguel a insertar.
     * @return Devuelve su ID autogenerado.
     * @author gennakk
     */
    @Insert
    Long addSanMiguel(ActividadSanMiguel sanMiguel);

    /**
     * Borra una ActividadSanMiguel.
     * Apple-pen, pineapple-pen
     * @param sanMiguel ActividadSanMiguel a borrar.
     * @author gennakk
     */
    @Delete
    void deleteSanMiguel(ActividadSanMiguel sanMiguel);

    /**
     * Actualiza una ActividadSanMiguel
     * @param sanMiguel ActividadSanMiguel a actualizar.
     * @author gennakk
     */
    @Update
    void updateSanMiguel(ActividadSanMiguel sanMiguel);
}
