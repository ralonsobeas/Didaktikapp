package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadGernika;

import java.util.List;

/**
 * Clase intermedia para acceder a BBDD y clase ActividadGernika.
 * @author gennakk
 */
@Dao
public interface GernikaDao {

    /**
     * Obtener todas las ActividadesGernika.
     * @author gennakk
     * @return Lista de ActividadGernika.
     */
    @Query("SELECT * FROM GERNIKA")
    List<ActividadGernika> getGernikas();

    /**
     * Devuelve una ActividadGernika por su ID.
     * @param uuid ID
     * @return ActividadGernika
     * @author gennakk
     */
    @Query("SELECT * FROM GERNIKA WHERE ID LIKE :uuid")
    ActividadGernika getGernika(Long uuid);

    /**
     * Inserta una nueva ActividadGernika.
     * I have a pen, I have a apple.
     * @param gernika ActividadGernika a insertar.
     * @return Devuelve su ID autogenerado.
     * @author gennakk
     */
    @Insert
    Long addGernika(ActividadGernika gernika);

    /**
     * Borra una ActividadGernika.
     * @param gernika ActividadGernika a borrar.
     * @author gennakk
     */
    @Delete
    void deleteGernika(ActividadGernika gernika);

    /**
     * Actualiza una ActividadGernika
     * @param gernika ActividadGernika a actualizar.
     * @author gennakk
     */
    @Update
    void updateGernika(ActividadGernika gernika);
}
