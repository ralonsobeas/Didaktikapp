package com.app.didaktikapp.BBDD.Service;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;

import com.app.didaktikapp.BBDD.Dao.ErrotaDao;
import com.app.didaktikapp.BBDD.Dao.GernikaDao;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadGernika;
import com.app.didaktikapp.BBDD.database.AppDatabase;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;

import java.util.List;
/**
 * Servicio de Gernika, implementa el DAO.
 * Uh! Pen-pineapple-apple-pen
 * @author gennakk
 */
public class GernikaService {


    @SuppressLint("StaticFieldLeak")
    private static GernikaService gernikaService;

    private GernikaDao gernikaDao;

    private GernikaService(@NonNull Application application) {

        gernikaDao = DatabaseRepository.getInstance(application).getGernikaDao();

    }

    public static GernikaService get(Application application) {
        if (gernikaService == null) {
            gernikaService = new GernikaService(application);
        }
        return gernikaService;
    }

    public List<ActividadGernika> getGernikas() {
        return gernikaDao.getGernikas();
    }

    public ActividadGernika getGernika(Long id) {
        return gernikaDao.getGernika(id);
    }

    public void addGernika(ActividadGernika  gernika) {
        gernikaDao.addGernika(gernika);
    }

    public void updateGernika(ActividadGernika gernika) {
        gernikaDao.updateGernika(gernika);
    }

    public void deleteGernika(ActividadGernika gernika) {
        gernikaDao.deleteGernika(gernika);
    }
}
