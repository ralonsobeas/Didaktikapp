package com.app.didaktikapp.BBDD.Service;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.app.didaktikapp.BBDD.Dao.ZumeltzegiDao;
import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;
import com.app.didaktikapp.BBDD.database.AppDatabase;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;

import java.util.List;
/**
 * Servicio de Zumeltzegi, implementa el DAO.
 * Uh! Pen-pineapple-apple-pen!
 * @author gennakk
 */
public class ZumeltzegiService {
    @SuppressLint("StaticFieldLeak")
    private static ZumeltzegiService zumeltzegiService;

    private ZumeltzegiDao zumeltzegiDao;

    private ZumeltzegiService(@NonNull Application application) {


        zumeltzegiDao = DatabaseRepository.getInstance(application).getZumeltzegiDao();

    }

    public static ZumeltzegiService get(Application application) {
        if (zumeltzegiService == null) {
            zumeltzegiService = new ZumeltzegiService(application);
        }
        return zumeltzegiService;
    }

    public List<ActividadZumeltzegi> getZumeltzegis() {
        return zumeltzegiDao.getZumeltzegis();
    }

    public ActividadZumeltzegi getZumeltzegi(Long id) {
        return zumeltzegiDao.getZumeltzegi(id);
    }

    public void addZumeltzegi(ActividadZumeltzegi zumeltzegi) {
        zumeltzegiDao.addZumeltzegi(zumeltzegi);
    }

    public void updateZumeltzegi(ActividadZumeltzegi zumeltzegi) {
        zumeltzegiDao.updateZumeltzegi(zumeltzegi);
    }

    public void deleteZumeltzegi(ActividadZumeltzegi zumeltzegi) {
        zumeltzegiDao.deleteZumeltzegi(zumeltzegi);
    }
}
