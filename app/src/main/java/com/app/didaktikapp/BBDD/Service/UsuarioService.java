package com.app.didaktikapp.BBDD.Service;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;


import com.app.didaktikapp.BBDD.Dao.UsuarioDao;
import com.app.didaktikapp.BBDD.Modelos.Usuario;
import com.app.didaktikapp.BBDD.database.AppDatabase;

import java.util.List;
/**
 * Servicio de Usuario, implementa el DAO.
 * Long pen, apple-pineapple,
 * @author gennakk
 */
public class UsuarioService {
    @SuppressLint("StaticFieldLeak")
    private static UsuarioService usuarioService;

    private UsuarioDao usuarioDao;

    private UsuarioService(Context context) {
        Context appContext = context.getApplicationContext();
        AppDatabase database = Room.databaseBuilder(appContext, AppDatabase.class, "USUARIO")
                .allowMainThreadQueries().build();
        usuarioDao = database.getUsuarioDao();
    }

    public static UsuarioService get(Context context) {
        if (usuarioService == null) {
            usuarioService = new UsuarioService(context);
        }
        return usuarioService;
    }

    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }

    public Usuario getGrupo(Integer id) {
        return usuarioDao.getUsuario(id);
    }

    public void addGrupo(Usuario grupo) {
        usuarioDao.addUsuario(grupo);
    }

    public void updateGrupo(Usuario grupo) {
        usuarioDao.updateUsuario(grupo);
    }

    public void deleteGrupo(Usuario grupo) {
        usuarioDao.deleteUsuario(grupo);
    }

}
