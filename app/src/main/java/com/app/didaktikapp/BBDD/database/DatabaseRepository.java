package com.app.didaktikapp.BBDD.database;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;
import androidx.room.Room;

import com.amitshekhar.DebugDB;
import com.amitshekhar.sqlite.DBFactory;
import com.app.didaktikapp.BBDD.Dao.ZumeltzegiDao;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel;
import com.app.didaktikapp.BBDD.Modelos.ActividadTren;
import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;
import com.app.didaktikapp.BBDD.Modelos.Grupo;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class DatabaseRepository {


    private static AppDatabase appDatabase;

    @VisibleForTesting
    private static final String DATABASE_NAME = "basic-sample-db";

    public DatabaseRepository(Context context) {
        appDatabase = getInstance(context);
    }

    public static AppDatabase getInstance(Context context) {
        if (null == appDatabase) {
            appDatabase = buildDatabaseInstance(context);
        }
        return appDatabase;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        appDatabase = null;
    }

    public static Long insertTaskGrupo(String nombreGrupo){


        try {
            return new AsyncTask<Void, Void, Long>() {
                @Override
                protected Long doInBackground(Void... voids) {
                    Grupo grupo = new Grupo();
                    grupo.setNombre(nombreGrupo);
                    grupo.setFecha( Calendar.getInstance().getTime());
                    ActividadZumeltzegi actividadZumeltzegi = new ActividadZumeltzegi();
                    grupo.setIdZumeltzegi(appDatabase.getZumeltzegiDao().addZumeltzegi(actividadZumeltzegi));
                    grupo.setIdUniversidad(appDatabase.getUniversitateaDao().addUniversitatea(new ActividadUniversitatea()));
                    grupo.setIdTren(appDatabase.getTrenDao().addTren(new ActividadTren()));
                    grupo.setIdParroquia(appDatabase.getSanMiguelDao().addSanMiguel(new ActividadSanMiguel()));
                    grupo.setIdErrota(appDatabase.getErrotaDao().addErrota(new ActividadErrota()));
                    return appDatabase.getGrupoDao().addGrupo(grupo);

                }


            }.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static Integer searchEstadoZumeltzegi(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdZumeltzegi();
        return appDatabase.getZumeltzegiDao().getZumeltzegi(id).getEstado();
    }

    public static Integer searchEstadoSanMiguel(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdParroquia();
        return appDatabase.getSanMiguelDao().getSanMiguel(id).getEstado();
    }

    public static Integer searchEstadoUniversidad(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdUniversidad();
        return appDatabase.getUniversitateaDao().getUniversitatea(id).getEstado();
    }

    public static Integer searchEstadoTren(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdTren();
        return appDatabase.getTrenDao().getTren(id).getEstado();
    }

    public static Integer searchEstadoErrota(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdErrota();
        return appDatabase.getErrotaDao().getErrota(id).getEstado();
    }

    public static void deleteGrupo(Grupo grupo){

        appDatabase.getZumeltzegiDao().deleteZumeltzegi(appDatabase.getZumeltzegiDao().getZumeltzegi(grupo.getIdZumeltzegi()));

        appDatabase.getErrotaDao().deleteErrota(appDatabase.getErrotaDao().getErrota(grupo.getIdErrota()));

        appDatabase.getUniversitateaDao().deleteUniversitatea(appDatabase.getUniversitateaDao().getUniversitatea(grupo.getIdUniversidad()));

        appDatabase.getSanMiguelDao().deleteSanMiguel(appDatabase.getSanMiguelDao().getSanMiguel(grupo.getIdParroquia()));

        appDatabase.getTrenDao().deleteTren(appDatabase.getTrenDao().getTren(grupo.getIdTren()));

        appDatabase.getGrupoDao().deleteGrupo(grupo);



    }


    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static void setAppDatabase(AppDatabase appDatabase) {
        DatabaseRepository.appDatabase = appDatabase;
    }
}
