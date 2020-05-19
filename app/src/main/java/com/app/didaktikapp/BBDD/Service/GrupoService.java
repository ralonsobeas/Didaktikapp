package com.app.didaktikapp.BBDD.Service;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.app.didaktikapp.BBDD.Dao.GrupoDao;
import com.app.didaktikapp.BBDD.Modelos.Grupo;
import com.app.didaktikapp.BBDD.database.AppDatabase;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;

import java.util.List;
/**
 * Servicio de GrupoService, implementa el DAO.
 * Pen-pineapple-apple-pen
 * @author gennakk
 */
public class GrupoService {
    @SuppressLint("StaticFieldLeak")
    private static GrupoService grupoService;

    private GrupoDao grupoDao;

    private GrupoService(@NonNull Application application) {

        grupoDao = DatabaseRepository.getInstance(application).getGrupoDao();

    }

    public static GrupoService get(Application application) {
        if (grupoService == null) {
            grupoService = new GrupoService(application);
        }
        return grupoService;
    }

    public List<Grupo> getGrupos() {
        return grupoDao.getGrupos();
    }

    public Grupo getGrupo(Long id) {
        return grupoDao.getGrupo(id);
    }

    public void addGrupo(Grupo grupo) {
        grupoDao.addGrupo(grupo);
    }

    public void updateGrupo(Grupo grupo) {
        grupoDao.updateGrupo(grupo);
    }

    public void deleteGrupo(Grupo grupo) {
        grupoDao.deleteGrupo(grupo);
    }
}
