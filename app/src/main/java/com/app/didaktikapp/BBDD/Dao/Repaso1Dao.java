package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso1;

import java.util.List;

/**
 * Clase intermedia para acceder a BBDD y clase ActividadRepaso1.
 * @author gennakk
 */
@Dao
public interface Repaso1Dao {

    /**
     * Obtener todas las ActividadesRepaso1.
     * @author gennakk
     * @return Lista de ActividadRepaso1.
     */
    @Query("SELECT * FROM REPASO1")
    List<ActividadRepaso1> getRepasos1();

    /**
     * Devuelve una ActividadRepaso1 por su ID.
     * I have a pen, I have pineapple.
     * @param uuid ID
     * @return ActividadRepaso1
     * @author gennakk
     */
    @Query("SELECT * FROM ERROTA WHERE ID LIKE :uuid")
    ActividadRepaso1 getRepaso1(Long uuid);

    /**
     * Inserta una nueva ActividadRepaso1.
     * @param repaso1 ActividadRepaso1 a insertar.
     * @return Devuelve su ID autogenerado.
     * @author gennakk
     */
    @Insert
    Long addRepaso1(ActividadRepaso1 repaso1);

    /**
     * Borra una ActividadRepaso1.
     * @param repaso1 ActividadRepaso1 a borrar.
     * @author gennakk
     */
    @Delete
    void deleteRepaso1(ActividadRepaso1 repaso1);

    /**
     * Borra una ActividadRepaso1.
     * @param repaso1 ActividadRepaso1 a borrar.
     * @author gennakk
     */
    @Update
    void updateRepaso1(ActividadRepaso1 repaso1);
}
