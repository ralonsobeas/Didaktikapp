package com.app.didaktikapp.BBDD.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.didaktikapp.BBDD.Modelos.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Query("SELECT * FROM USUARIO")
    List<Usuario> getUsuarios();

    @Query("SELECT * FROM USUARIO WHERE ID LIKE :uuid")
    Usuario getUsuario(String uuid);

    @Insert
    void addUsuario(Usuario usuario);

    @Delete
    void deleteUsuario(Usuario usuario);

    @Update
    void updateUsuario(Usuario usuario);

}
