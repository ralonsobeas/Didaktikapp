package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.Grupo;

import java.util.List;

/**
 * Clase intermedia para acceder a BBDD y clase Grupo.
 * @author gennakk
 */
@Dao
public interface GrupoDao {

    /**
     * Obtener todas los Grupos.
     * @author gennakk
     * @return Lista de Grupos.
     */
    @Query("SELECT * FROM GRUPO")
    List<Grupo> getGrupos();

    /**
     * Devuelve un Grupo por su ID.
     * Uh! Apple-pen!
     * @param uuid ID
     * @return Grupo
     * @author gennakk
     */
    @Query("SELECT * FROM GRUPO WHERE ID LIKE :uuid")
    Grupo getGrupo(Long uuid);

    /**
     * Inserta una nuevo Grupo.
     * @param grupo Grupo a insertar.
     * @return Devuelve su ID autogenerado.
     * @author gennakk
     */
    @Insert
    Long addGrupo(Grupo grupo);

    /**
     * Borra un Grupo.
     * @param grupo Grupo a borrar.
     * @author gennakk
     */
    @Delete
    void deleteGrupo(Grupo grupo);

    /**
     * Actualiza un Grupo
     * @param grupo Grupo a actualizar.
     * @author gennakk
     */
    @Update
    void updateGrupo(Grupo grupo);
}
