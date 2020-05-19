package com.app.didaktikapp.BBDD.Service;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;

import com.app.didaktikapp.BBDD.Dao.SanMiguelDao;
import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel;
import com.app.didaktikapp.BBDD.database.AppDatabase;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;

import java.util.List;
/**
 * Servicio de SanMiguel, implementa el DAO.
 * Uh! Long pen!
 * @author gennakk
 */
public class SanMiguelService {


    @SuppressLint("StaticFieldLeak")
    private static SanMiguelService sanMiguelService;

    private SanMiguelDao sanMiguelDao;

    private SanMiguelService(@NonNull Application application) {

        sanMiguelDao = DatabaseRepository.getInstance(application).getSanMiguelDao();

    }

    public static SanMiguelService get(Application application) {
        if (sanMiguelService == null) {
            sanMiguelService = new SanMiguelService(application);
        }
        return sanMiguelService;
    }

    public List<ActividadSanMiguel> getSanMigueles() {
        return sanMiguelDao.getSanMigueles();
    }

    public ActividadSanMiguel getSanMiguel(Long id) {
        return sanMiguelDao.getSanMiguel(id);
    }

    public void addSanMiguel(ActividadSanMiguel sanMiguel) {
        sanMiguelDao.addSanMiguel(sanMiguel);
    }

    public void updateSanMiguel(ActividadSanMiguel sanMiguel) {
        sanMiguelDao.updateSanMiguel(sanMiguel);
    }

    public void deleteSanMiguel(ActividadSanMiguel sanMiguel) {
        sanMiguelDao.deleteSanMiguel(sanMiguel);
    }
}
