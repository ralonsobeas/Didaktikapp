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

/**
 * Clase intermedia para acceder a BBDD y clase ActividadRepaso2.
 * @author gennakk
 */
@Dao
public interface Repaso2Dao {

    /**
     * Obtener todas las ActividadesRepaso2.
     * Uh! Pineapple-pen!
     * @author gennakk
     * @return Lista de ActividadRepaso2.
     */
    @Query("SELECT * FROM REPASO2")
    List<ActividadRepaso2> getRepasos2();

    /**
     * Devuelve una ActividadRepaso2 por su ID.
     * @param uuid ID
     * @return ActividadRepaso2
     * @author gennakk
     */
    @Query("SELECT * FROM ERROTA WHERE ID LIKE :uuid")
    ActividadRepaso2 getRepaso2(Long uuid);

    /**
     * Inserta una nueva ActividadRepaso2.
     * @param repaso2 ActividadRepaso2 a insertar.
     * @return Devuelve su ID autogenerado.
     * @author gennakk
     */
    @Insert
    Long addRepaso2(ActividadRepaso2 repaso2);

    /**
     * Borra una ActividadRepaso2.
     * @param repaso2 ActividadRepaso2 a borrar.
     * @author gennakk
     */
    @Delete
    void deleteRepaso2(ActividadRepaso2 repaso2);

    /**
     * Actualiza una ActividadRepaso2
     * @param repaso2 ActividadRepaso2 a actualizar.
     * @author gennakk
     */
    @Update
    void updateRepaso2(ActividadRepaso2 repaso2);
}
