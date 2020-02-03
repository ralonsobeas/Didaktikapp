package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;

import java.util.List;

/**
 * Clase intermedia para acceder a BBDD y clase ActividadZumeltzegi.
 * @author gennakk
 */
@Dao
public interface ZumeltzegiDao {

    /**
     * Obtener todas las ActividadesZumeltzegi.
     * @author gennakk
     * @return Lista de ActividadesZumeltzegi.
     */
    @Query("SELECT * FROM ZUMELTZEGI")
    List<ActividadZumeltzegi> getZumeltzegis();

    /**
     * Devuelve una ActividadZumeltzegi por su ID.
     * @param uuid ID
     * @return ActividadZumeltzegi
     * @author gennakk
     */
    @Query("SELECT * FROM ZUMELTZEGI WHERE ID LIKE :uuid")
    ActividadZumeltzegi getZumeltzegi(Long uuid);

    /**
     * Inserta una nueva ActividadZumeltzegi.
     * I have a pen, I have a pen.
     * @param zumeltzegi ActividadZumeltzegi a insertar.
     * @return Devuelve su ID autogenerado.
     * @author gennakk
     */
    @Insert
    Long addZumeltzegi(ActividadZumeltzegi zumeltzegi);

    /**
     * Borra una ActividadZumeltzegi.
     * @param zumeltzegi ActividadZumeltzegi a borrar.
     * @author gennakk
     */
    @Delete
    void deleteZumeltzegi(ActividadZumeltzegi zumeltzegi);

    /**
     * Actualiza una ActividadZumeltzegi
     * @param zumeltzegi ActividadZumeltzegi a actualizar.
     * @author gennakk
     */
    @Update
    void updateZumeltzegi(ActividadZumeltzegi zumeltzegi);

    /**
     * Updating only estado
     * By order id
     */
    @Query("UPDATE zumeltzegi SET estado=:new_estado WHERE id = :id")
    void updateEstado(int new_estado, Long id);
}
