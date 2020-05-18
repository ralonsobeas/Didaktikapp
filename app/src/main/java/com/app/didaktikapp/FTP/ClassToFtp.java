package com.app.didaktikapp.FTP;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.app.didaktikapp.Activities.MapActivity;
import com.app.didaktikapp.BBDD.Modelos.Actividad;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadGernika;
import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.Modelos.Grupo;
import com.app.didaktikapp.BBDD.database.DatabaseRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Clase para recoger datos de clases convertir a Json y enviar peticion
 * @author gennakk
 */
public class ClassToFtp {

    public static final int TIPO_ERROTA = 1;

    public static final int TIPO_GERNIKA = 2;

    public static final int TIPO_REPASO1 = 3;

    public static final int TIPO_REPASO2 = 4;

    public static final int TIPO_SANMIGUEL = 5;

    public static final int TIPO_TREN = 6;

    public static final int TIPO_UNIVERSITATEA = 7;

    public static final int TIPO_ZUMELTZEGI = 8;

    /**
     * Mandar peticion cuando haya internet
     * @param context
     * @param tipo
     */
    public static void send(Context context,int tipo){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                DatabaseRepository.getAppDatabase().getGrupoDao().getGrupo(MapActivity.GRUPO_S.getId());
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {



                        GrupoActividad grupoActividad = obtenerActividadGrupo(tipo);
                        ArrayList<GrupoActividad> returnList = new ArrayList<GrupoActividad>();
                        returnList.add(grupoActividad);
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<GrupoActividad>>(){}.getType();
                        String json = gson.toJson(returnList, type);
                        Log.v("upload result", "send?");


                        /***  Logic to set Data while creating worker **/
                        Constraints constraints = new Constraints.Builder()
                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                .build();
                        Data.Builder dataBuilder = new Data.Builder();
                        //Add parameter in Data class. just like bundle. You can also add Boolean and Number in parameter.
                        dataBuilder.putString(Ftp.JSON, json);
                        Log.i("JSON",json);


                        Data data =  dataBuilder.build();

                        OneTimeWorkRequest onetimeJob = new OneTimeWorkRequest.Builder(Ftp.class)
                                .setConstraints(constraints)
                                .setInputData(data).build(); // or PeriodicWorkRequest
                        //enque worker
                        WorkManager.getInstance().enqueue(onetimeJob);


                        Log.v("upload result", "sended");

                        if(grupoActividad.getActividad() instanceof ActividadUniversitatea){



                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Empty data",Toast.LENGTH_LONG).show();
                    }
                });
    }


    /**
     * Obtener tipo de actividad
     * @param tipo
     * @return
     */
    private static GrupoActividad obtenerActividadGrupo(int tipo){
        Actividad responseList = null;
        if(tipo == TIPO_ERROTA) {
            responseList = DatabaseRepository.getAppDatabase().getErrotaDao().getErrota(MapActivity.GRUPO_S.getId());
        }
        if(tipo == TIPO_GERNIKA) {
            responseList = DatabaseRepository.getAppDatabase().getGernikaDao().getGernika(MapActivity.GRUPO_S.getId());
        }
        if(tipo == TIPO_REPASO1) {
            responseList = DatabaseRepository.getAppDatabase().getRepaso1Dao().getRepaso1(MapActivity.GRUPO_S.getId());
        }
        if(tipo == TIPO_REPASO2) {
            responseList = DatabaseRepository.getAppDatabase().getRepaso2Dao().getRepaso2(MapActivity.GRUPO_S.getId());
        }
        if(tipo == TIPO_SANMIGUEL) {
            responseList = DatabaseRepository.getAppDatabase().getSanMiguelDao().getSanMiguel(MapActivity.GRUPO_S.getId());
        }
        if(tipo == TIPO_TREN) {
            responseList = DatabaseRepository.getAppDatabase().getTrenDao().getTren(MapActivity.GRUPO_S.getId());
        }
        if(tipo == TIPO_UNIVERSITATEA) {
            responseList = DatabaseRepository.getAppDatabase().getUniversitateaDao().getUniversitatea(MapActivity.GRUPO_S.getId());
        }
        if(tipo == TIPO_ZUMELTZEGI) {
            responseList = DatabaseRepository.getAppDatabase().getZumeltzegiDao().getZumeltzegi(MapActivity.GRUPO_S.getId());
        }

        responseList.setTipo(tipo);
        return new GrupoActividad(MapActivity.GRUPO_S, responseList);

    }




}
