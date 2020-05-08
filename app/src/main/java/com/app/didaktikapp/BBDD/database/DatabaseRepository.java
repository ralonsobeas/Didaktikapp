package com.app.didaktikapp.BBDD.database;

import android.accounts.Account;
import android.content.Context;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;

import androidx.annotation.VisibleForTesting;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadGernika;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso1;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso2;
import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel;
import com.app.didaktikapp.BBDD.Modelos.ActividadTren;
import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;
import com.app.didaktikapp.BBDD.Modelos.Grupo;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

/**
 * Clase que instancia la BBDD.
 * @author gennakk
 */
public class DatabaseRepository {


    private static AppDatabase appDatabase;

    @VisibleForTesting
    private static final String DATABASE_NAME = "didaktikappBBDD";

    /**
     * Constructor de la clase.
     * @param context Contexto de la aplicación.
     * @author gennakk
     */
    public DatabaseRepository(Context context) {
        appDatabase = getInstance(context);
    }

    /**
     * Genera la BBDD en caso de no existir.
     * @param context Contexto de la aplicación.
     * @return Clase AppDatabase
     */
    public static AppDatabase getInstance(Context context) {
        if (null == appDatabase) {
            appDatabase = buildDatabaseInstance(context);
        }
        return appDatabase;
    }

    /**
     * Construye la BBDD.
     * @param context Contexto de la aplicación.
     * @return Clase AppDatabase.
     * @author gennakk
     */
    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                DATABASE_NAME)
                .addMigrations(MIGRATION_2_3)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build();
    }

    /**
     * Limpia la BBDD.
     * @author gennakk
     */
    public void cleanUp(){
        appDatabase = null;
    }

    /**
     * Crea un Grupo de manera asíncrona.
     * @param nombreGrupo Nombre del grupo.
     * @return ID autogenerado.
     * @author gennakk
     */
    public static Long insertTaskGrupo(String nombreGrupo, String email){


        try {
            return new AsyncTask<Void, Void, Long>() {
                @Override
                protected Long doInBackground(Void... voids) {
                    Grupo grupo = new Grupo();
                    grupo.setNombre(nombreGrupo);
                    grupo.setDeviceId(email);
                    grupo.setFecha( Calendar.getInstance().getTime());
                    ActividadZumeltzegi actividadZumeltzegi = new ActividadZumeltzegi();

                    grupo.setIdZumeltzegi(appDatabase.getZumeltzegiDao().addZumeltzegi(actividadZumeltzegi));
                    grupo.setIdUniversidad(appDatabase.getUniversitateaDao().addUniversitatea(new ActividadUniversitatea()));
                    grupo.setIdTren(appDatabase.getTrenDao().addTren(new ActividadTren()));
                    grupo.setIdParroquia(appDatabase.getSanMiguelDao().addSanMiguel(new ActividadSanMiguel()));
                    grupo.setIdErrota(appDatabase.getErrotaDao().addErrota(new ActividadErrota()));
                    grupo.setIdGernika(appDatabase.getGernikaDao().addGernika(new ActividadGernika()));
                    grupo.setIdRepaso1(appDatabase.getRepaso1Dao().addRepaso1(new ActividadRepaso1()));
                    grupo.setIdRepaso2(appDatabase.getRepaso2Dao().addRepaso2(new ActividadRepaso2()));

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

    /**
     * Busca el estado de Zumeltzegi.
     * @param idgrupo ID del grupo.
     * @return Estado de la actividad Zumeltzegi.
     */
    public static Integer searchEstadoZumeltzegi(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdZumeltzegi();
        return appDatabase.getZumeltzegiDao().getZumeltzegi(id).getEstado();
    }

    /**
     * Busca el estado de SanMiguelDao.
     * @param idgrupo ID del grupo.
     * @return Estado de la actividad SanMiguelDao.
     */
    public static Integer searchEstadoSanMiguel(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdParroquia();
        return appDatabase.getSanMiguelDao().getSanMiguel(id).getEstado();
    }

    /**
     * Busca el estado de UniversitateaDao.
     * @param idgrupo ID del grupo.
     * @return Estado de la actividad UniversitateaDao.
     */
    public static Integer searchEstadoUniversidad(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdUniversidad();
        return appDatabase.getUniversitateaDao().getUniversitatea(id).getEstado();
    }

    /**
     * Busca el estado de TrenDao.
     * @param idgrupo ID del grupo.
     * @return Estado de la actividad TrenDao.
     */
    public static Integer searchEstadoTren(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdTren();
        return appDatabase.getTrenDao().getTren(id).getEstado();
    }

    /**
     * Busca el estado de ErrotaDao.
     * @param idgrupo ID del grupo.
     * @return Estado de la actividad ErrotaDao.
     */
    public static Integer searchEstadoErrota(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdErrota();
        return appDatabase.getErrotaDao().getErrota(id).getEstado();
    }

    /**
     * Busca el estado de GernikaDao.
     * @param idgrupo ID del grupo.
     * @return Estado de la actividad GernikaDao.
     */
    public static Integer searchEstadoGernika(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdGernika();
        return appDatabase.getGernikaDao().getGernika(id).getEstado();
    }

    /**
     * Busca el estado de Repaso1Dao.
     * I have a apple, I have pineapple
     * @param idgrupo ID del grupo.
     * @return Estado de la actividad Repaso1Dao.
     */
    public static Integer searchEstadoRepaso1(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdRepaso1();
        return appDatabase.getRepaso1Dao().getRepaso1(id).getEstado();
    }

    /**
     * Busca el estado de Repaso2Dao.
     * @param idgrupo ID del grupo.
     * @return Estado de la actividad Repaso2Dao.
     */
    public static Integer searchEstadoRepaso2(Long idgrupo){
        Long id = appDatabase.getGrupoDao().getGrupo(idgrupo).getIdRepaso2();
        return appDatabase.getRepaso2Dao().getRepaso2(id).getEstado();
    }


    /**
     * Borra el Grupo y todas las Actividades relaccionadas.
     * @param grupo Objeto Grupo.
     * @author gennakk
     */
    public static void deleteGrupo(Grupo grupo){

        appDatabase.getZumeltzegiDao().deleteZumeltzegi(appDatabase.getZumeltzegiDao().getZumeltzegi(grupo.getIdZumeltzegi()));

        appDatabase.getErrotaDao().deleteErrota(appDatabase.getErrotaDao().getErrota(grupo.getIdErrota()));

        appDatabase.getUniversitateaDao().deleteUniversitatea(appDatabase.getUniversitateaDao().getUniversitatea(grupo.getIdUniversidad()));

        appDatabase.getSanMiguelDao().deleteSanMiguel(appDatabase.getSanMiguelDao().getSanMiguel(grupo.getIdParroquia()));

        appDatabase.getTrenDao().deleteTren(appDatabase.getTrenDao().getTren(grupo.getIdTren()));

        appDatabase.getGernikaDao().deleteGernika(appDatabase.getGernikaDao().getGernika(grupo.getIdGernika()));

        appDatabase.getRepaso1Dao().deleteRepaso1(appDatabase.getRepaso1Dao().getRepaso1(grupo.getIdRepaso1()));

        appDatabase.getRepaso2Dao().deleteRepaso2(appDatabase.getRepaso2Dao().getRepaso2(grupo.getIdRepaso2()));


        appDatabase.getGrupoDao().deleteGrupo(grupo);



    }


    /**
     * Devuelve la BBDD.
     * @return AppDatabase BBDD.
     * @author gennakk
     */
    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    /**
     * Setter de BBBD.
     * @param appDatabase BBDD de la aplicación.
     * @author gennakk
     */
    public static void setAppDatabase(AppDatabase appDatabase) {
        DatabaseRepository.appDatabase = appDatabase;
    }

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE ERROTA");
            database.execSQL("DROP TABLE GERNIKA");
            database.execSQL("DROP TABLE REPASO1");
            database.execSQL("DROP TABLE REPASO2");
            database.execSQL("DROP TABLE SANMIGUEL");
            database.execSQL("DROP TABLE TREN");
            database.execSQL("DROP TABLE UNIVERSITATEA");
            database.execSQL("DROP TABLE ZUMELTZEGI");
            database.execSQL("DROP TABLE GRUPO");
            database.execSQL("DROP TABLE USUARIO");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE ERROTA");
            database.execSQL("DROP TABLE GERNIKA");
            database.execSQL("DROP TABLE REPASO1");
            database.execSQL("DROP TABLE REPASO2");
            database.execSQL("DROP TABLE SANMIGUEL");
            database.execSQL("DROP TABLE TREN");
            database.execSQL("DROP TABLE UNIVERSITATEA");
            database.execSQL("DROP TABLE ZUMELTZEGI");
            database.execSQL("DROP TABLE GRUPO");
            database.execSQL("DROP TABLE USUARIO");
        }
    };
}
