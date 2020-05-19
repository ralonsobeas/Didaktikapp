package com.app.didaktikapp.BBDD.Service;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;

import com.app.didaktikapp.BBDD.Dao.ErrotaDao;
import com.app.didaktikapp.BBDD.Dao.Repaso1Dao;
import com.app.didaktikapp.BBDD.Dao.Repaso2Dao;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso1;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso2;
import com.app.didaktikapp.BBDD.database.AppDatabase;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;

import java.util.List;
/**
 * Servicio de Repaso2, implementa el DAO.
 * I have a pen, I have a pen
 * @author gennakk
 */
public class Repaso2Service {


    @SuppressLint("StaticFieldLeak")
    private static Repaso2Service repaso2Service;

    private Repaso2Dao repaso2Dao;

    private Repaso2Service(@NonNull Application application) {

        repaso2Dao = DatabaseRepository.getInstance(application).getRepaso2Dao();

    }

    public static Repaso2Service get(Application application) {
        if (repaso2Service == null) {
            repaso2Service = new Repaso2Service(application);
        }
        return repaso2Service;
    }

    public List<ActividadRepaso2> getRepasos1() {
        return repaso2Dao.getRepasos2();
    }

    public ActividadRepaso2 getRepaso(Long id) {
        return repaso2Dao.getRepaso2(id);
    }

    public void addRepaso2(ActividadRepaso2 repaso2) {
        repaso2Dao.addRepaso2(repaso2);
    }

    public void updateRepaso2(ActividadRepaso2 repaso2) {
        repaso2Dao.updateRepaso2(repaso2);
    }

    public void deleteRepaso2(ActividadRepaso2 repaso2) {
        repaso2Dao.deleteRepaso2(repaso2);
    }
}
