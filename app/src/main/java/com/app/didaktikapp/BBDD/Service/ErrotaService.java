package com.app.didaktikapp.BBDD.Service;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;

import com.app.didaktikapp.BBDD.Dao.ErrotaDao;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.database.AppDatabase;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;

import java.util.List;

/**
 * Servicio de Errota, implementa el DAO.
 * Apple-pen, pineapple-pen
 * @author gennakk
 */
public class ErrotaService {


    @SuppressLint("StaticFieldLeak")
    private static ErrotaService errotaService;

    private ErrotaDao errotaDao;

    private ErrotaService(@NonNull Application application) {

        errotaDao = DatabaseRepository.getInstance(application).getErrotaDao();

    }

    public static ErrotaService get(Application application) {
        if (errotaService == null) {
            errotaService = new ErrotaService(application);
        }
        return errotaService;
    }

    public List<ActividadErrota> getErrotas() {
        return errotaDao.getErrotas();
    }

    public ActividadErrota getErrota(Long id) {
        return errotaDao.getErrota(id);
    }

    public void addErrota(ActividadErrota errota) {
        errotaDao.addErrota(errota);
    }

    public void updateErrota(ActividadErrota errota) {
        errotaDao.updateErrota(errota);
    }

    public void deleteErrota(ActividadErrota errota) {
        errotaDao.deleteErrota(errota);
    }
}
