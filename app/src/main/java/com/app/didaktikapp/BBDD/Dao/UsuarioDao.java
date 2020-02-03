package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.Usuario;

import java.util.List;

/**
 * Clase intermedia para acceder a BBDD y clase Usuario.
 * @author gennakk
 */
@Dao
public interface UsuarioDao {

    /**
     * Obtener todas los Usuarios.
     * Dance time!.
     * @author gennakk
     * @return Lista de Usuario.
     */
    @Query("SELECT * FROM USUARIO")
    List<Usuario> getUsuarios();

    /**
     * Devuelve una Usuario por su ID.
     * @param uuid ID
     * @return ActividadErrota
     * @author gennakk
     */
    @Query("SELECT * FROM USUARIO WHERE ID LIKE :uuid")
    Usuario getUsuario(Integer uuid);

    /**
     * Inserta una nueva Usuario.
     * @param usuario Usuario a insertar.
     * @return Devuelve su ID autogenerado.
     * @author gennakk
     */
    @Insert
    void addUsuario(Usuario usuario);

    /**
     * Borra un Usuario.
     * @param usuario Usuario a borrar.
     * @author gennakk
     */
    @Delete
    void deleteUsuario(Usuario usuario);

    /**
     * Actualiza un Usuario
     * @param usuario Usuario a actualizar.
     * @author gennakk
     */
    @Update
    void updateUsuario(Usuario usuario);

}
