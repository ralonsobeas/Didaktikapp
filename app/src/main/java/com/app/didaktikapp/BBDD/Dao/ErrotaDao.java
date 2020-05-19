package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;

import java.util.List;

/**
 * Clase intermedia para acceder a BBDD y clase ActividadErrota.
 * @author gennakk
 */
@Dao
public interface ErrotaDao {

    /**
     * Obtener todas las ActividadesErrota.
     * @author gennakk
     * @return Lista de ActividadErrota.
     */
    @Query("SELECT * FROM ERROTA")
    List<ActividadErrota> getErrotas();

    /**
     * Devuelve una ActividadErrota por su ID.
     * @param uuid ID
     * @return ActividadErrota
     * @author gennakk
     */
    @Query("SELECT * FROM ERROTA WHERE ID LIKE :uuid")
    ActividadErrota getErrota(Long uuid);

    /**
     * Inserta una nueva ActividadErrota.
     * @param errota ActividadErrota a insertar.
     * @return Devuelve su ID autogenerado.
     * @author gennakk
     */
    @Insert
    Long addErrota(ActividadErrota errota);

    /**
     * Borra una ActividadErrota.
     * P-P-A-P
     * @param errota ActividadErrota a borrar.
     * @author gennakk
     */
    @Delete
    void deleteErrota(ActividadErrota errota);

    /**
     * Actualiza una ActividadErrota
     * @param errota ActividadErrota a actualizar.
     * @author gennakk
     */
    @Update
    void updateErrota(ActividadErrota errota);
}
