package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;

import java.util.List;

/**
 * Clase intermedia para acceder a BBDD y clase ActividadUniversitatea.
 * @author gennakk
 */
@Dao
public interface UniversitateaDao {

    /**
     * Obtener todas las ActividadesUniversitatea.
     * @author gennakk
     * @return Lista de ActividadUniversitatea.
     */
    @Query("SELECT * FROM UNIVERSITATEA")
    List<ActividadUniversitatea> getUniversitateas();

    /**
     * Devuelve una ActividadUniversitatea por su ID.
     * @param uuid ID
     * @return ActividadUniversitatea
     * @author gennakk
     */
    @Query("SELECT * FROM UNIVERSITATEA WHERE ID LIKE :uuid")
    ActividadUniversitatea getUniversitatea(Long uuid);

    /**
     * Inserta una nueva ActividadUniversitatea.
     * Pen-pineapple-apple-pen.
     * @param universitatea ActividadUniversitatea a insertar.
     * @return Devuelve su ID autogenerado.
     * @author gennakk
     */
    @Insert
    Long addUniversitatea(ActividadUniversitatea universitatea);

    /**
     * Borra una ActividadUniversitatea.
     * @param universitatea ActividadUniversitatea a borrar.
     * @author gennakk
     */
    @Delete
    void deleteUniversitatea(ActividadUniversitatea universitatea);

    /**
     * Actualiza una ActividadUniversitatea
     * @param universitatea ActividadUniversitatea a actualizar.
     * @author gennakk
     */
    @Update
    void updateUniversitatea(ActividadUniversitatea universitatea);
}
