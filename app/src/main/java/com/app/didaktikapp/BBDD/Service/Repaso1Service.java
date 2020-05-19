package com.app.didaktikapp.BBDD.Service;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;

import com.app.didaktikapp.BBDD.Dao.ErrotaDao;
import com.app.didaktikapp.BBDD.Dao.Repaso1Dao;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso1;
import com.app.didaktikapp.BBDD.database.AppDatabase;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;

import java.util.List;
/**
 * Servicio de Repaso1Service, implementa el DAO.
 * Dance time!
 * @author gennakk
 */
public class Repaso1Service {


    @SuppressLint("StaticFieldLeak")
    private static Repaso1Service repaso1Service;

    private Repaso1Dao repaso1Dao;

    private Repaso1Service(@NonNull Application application) {

        repaso1Dao = DatabaseRepository.getInstance(application).getRepaso1Dao();

    }

    public static Repaso1Service get(Application application) {
        if (repaso1Service == null) {
            repaso1Service = new Repaso1Service(application);
        }
        return repaso1Service;
    }

    public List<ActividadRepaso1> getRepasos1() {
        return repaso1Dao.getRepasos1();
    }

    public ActividadRepaso1 getRepaso(Long id) {
        return repaso1Dao.getRepaso1(id);
    }

    public void addRepaso(ActividadRepaso1 repaso1) {
        repaso1Dao.addRepaso1(repaso1);
    }

    public void updateRepaso(ActividadRepaso1 repaso1) {
        repaso1Dao.updateRepaso1(repaso1);
    }

    public void deleteRepaso(ActividadRepaso1 repaso1) {
        repaso1Dao.deleteRepaso1(repaso1);
    }
}
