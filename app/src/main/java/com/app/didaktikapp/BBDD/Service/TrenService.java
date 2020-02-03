package com.app.didaktikapp.BBDD.Service;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;

import com.app.didaktikapp.BBDD.Dao.TrenDao;
import com.app.didaktikapp.BBDD.Modelos.ActividadTren;
import com.app.didaktikapp.BBDD.database.AppDatabase;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;

import java.util.List;
/**
 * Servicio de Tren, implementa el DAO.
 * I have a apple, I have pineapple
 * @author gennakk
 */
public class TrenService {

    @SuppressLint("StaticFieldLeak")
    private static TrenService trenService;

    private TrenDao trenDao;

    private TrenService(@NonNull Application application) {

        trenDao = DatabaseRepository.getInstance(application).getTrenDao();

    }

    public static TrenService get(Application application) {
        if (trenService == null) {
            trenService = new TrenService(application);
        }
        return trenService;
    }

    public List<ActividadTren> getTrenes() {
        return trenDao.getTrenes();
    }

    public ActividadTren getTren(Long id) {
        return trenDao.getTren(id);
    }

    public void addTren(ActividadTren tren) {
        trenDao.addTren(tren);
    }

    public void updateTren(ActividadTren tren) {
        trenDao.updateTren(tren);
    }

    public void deleteTren(ActividadTren tren) {
        trenDao.deleteTren(tren);
    }
}
