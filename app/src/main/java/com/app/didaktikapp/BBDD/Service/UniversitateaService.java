package com.app.didaktikapp.BBDD.Service;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.app.didaktikapp.BBDD.Dao.UniversitateaDao;
import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;
import com.app.didaktikapp.BBDD.database.AppDatabase;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;

import java.util.List;
/**
 * Servicio de Universitatea, implementa el DAO.
 * Uh! Apple-pineapple!
 * @author gennakk
 */
public class UniversitateaService {

    @SuppressLint("StaticFieldLeak")
    private static UniversitateaService universitateaService;

    private UniversitateaDao universitateaDao;

    private UniversitateaService(@NonNull Application application) {

        universitateaDao =  DatabaseRepository.getInstance(application).getUniversitateaDao();

    }

    public static UniversitateaService get(Application application) {
        if (universitateaService == null) {
            universitateaService = new UniversitateaService(application);
        }
        return universitateaService;
    }

    public List<ActividadUniversitatea> getUniversitateas() {
        return universitateaDao.getUniversitateas();
    }

    public ActividadUniversitatea getUniversitatea(Long id) {
        return universitateaDao.getUniversitatea(id);
    }

    public void addUniversitatea(ActividadUniversitatea universitatea) {
        universitateaDao.addUniversitatea(universitatea);
    }

    public void updateUniversitatea(ActividadUniversitatea universitatea) {
        universitateaDao.updateUniversitatea(universitatea);
    }

    public void deleteUniversitatea(ActividadUniversitatea universitatea) {
        universitateaDao.deleteUniversitatea(universitatea);
    }
}
