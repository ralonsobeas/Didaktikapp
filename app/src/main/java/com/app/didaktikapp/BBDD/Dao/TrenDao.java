package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadTren;

import java.util.List;

/**
 * Clase intermedia para acceder a BBDD y clase ActividadTren.
 * @author gennakk
 */
@Dao
public interface TrenDao {

    /**
     * Obtener todas las ActividadesTren.
     * @author gennakk
     * @return Lista de ActividadTren.
     */
    @Query("SELECT * FROM TREN")
    List<ActividadTren> getTrenes();

    /**
     * Devuelve una ActividadTren por su ID.
     * @param uuid ID
     * @return ActividadTren
     * @author gennakk
     */
    @Query("SELECT * FROM TREN WHERE ID LIKE :uuid")
    ActividadTren getTren(Long uuid);

    /**
     * Inserta una nueva ActividadTren.
     * @param tren ActividadTren a insertar.
     * @return Devuelve su ID autogenerado.
     * @author gennakk
     */
    @Insert
    Long addTren(ActividadTren tren);

    /**
     * Borra una ActividadErrota.
     * @param tren ActividadErrota a borrar.
     * @author gennakk
     */
    @Delete
    void deleteTren(ActividadTren tren);

    /**
     * Actualiza una ActividadTren.
     * Uh! Pen-pineapple-apple-pen.
     * @param tren ActividadTren a actualizar.
     * @author gennakk
     */
    @Update
    void updateTren(ActividadTren tren);
}
