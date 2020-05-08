package com.app.didaktikapp.BBDD.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.app.didaktikapp.BBDD.AppExecutors;
import com.app.didaktikapp.BBDD.Dao.ErrotaDao;
import com.app.didaktikapp.BBDD.Dao.GernikaDao;
import com.app.didaktikapp.BBDD.Dao.GrupoDao;
import com.app.didaktikapp.BBDD.Dao.Repaso1Dao;
import com.app.didaktikapp.BBDD.Dao.Repaso2Dao;
import com.app.didaktikapp.BBDD.Dao.SanMiguelDao;
import com.app.didaktikapp.BBDD.Dao.TrenDao;
import com.app.didaktikapp.BBDD.Dao.UniversitateaDao;
import com.app.didaktikapp.BBDD.Dao.UsuarioDao;
import com.app.didaktikapp.BBDD.Dao.ZumeltzegiDao;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadGernika;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso1;
import com.app.didaktikapp.BBDD.Modelos.ActividadRepaso2;
import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel;
import com.app.didaktikapp.BBDD.Modelos.ActividadTren;
import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;
import com.app.didaktikapp.BBDD.Modelos.Grupo;
import com.app.didaktikapp.BBDD.Modelos.Usuario;

import java.util.List;

/**
 * Clase con la BBDD y su estructura de clases.
 * @author gennakk
 */
@Database(entities = {Grupo.class,
                    Usuario.class, ActividadZumeltzegi.class,
                    ActividadUniversitatea.class,
                    ActividadTren.class,
                    ActividadSanMiguel.class,
                    ActividadErrota.class,
                    ActividadGernika.class,
                    ActividadRepaso1.class,
                    ActividadRepaso2.class},
                    version = 4 )
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Getter de GrupoDao.
     * @return GrupoDao
     * @author gennakk
     */
    public abstract GrupoDao getGrupoDao();

    /**
     * Getter de UsuarioDao.
     * Uh! Long pen!
     * @return UsuarioDao
     * @author gennakk
     */
    public abstract UsuarioDao getUsuarioDao();

    /**
     * Getter de ZumeltzegiDao.
     * @return ZumeltzegiDao
     * @author gennakk
     */
    public abstract ZumeltzegiDao getZumeltzegiDao();

    /**
     * Getter de UniversitateaDao.
     * @return UniversitateaDao
     * @author gennakk
     */
    public abstract UniversitateaDao getUniversitateaDao();

    /**
     * Getter de TrenDao.
     * @return TrenDao
     * @author gennakk
     */
    public abstract TrenDao getTrenDao();

    /**
     * Getter de SanMiguelDao.
     * @return SanMiguelDao
     * @author gennakk
     */
    public abstract SanMiguelDao getSanMiguelDao();

    /**
     * Getter de ErrotaDao.
     * @return ErrotaDao
     * @author gennakk
     */
    public abstract ErrotaDao getErrotaDao();

    /**
     * Getter de GernikaDao.
     * @return GernikaDao
     * @author gennakk
     */
    public abstract GernikaDao getGernikaDao();

    /**
     * Getter de Repaso1Dao.
     * @return Repaso1Dao
     * @author gennakk
     */
    public abstract Repaso1Dao getRepaso1Dao();

    /**
     * Getter de Repaso2Dao.
     * @return Repaso2Dao
     * @author gennakk
     */
    public abstract Repaso2Dao getRepaso2Dao();







}
