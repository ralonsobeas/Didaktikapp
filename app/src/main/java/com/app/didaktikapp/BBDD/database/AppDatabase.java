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
import com.app.didaktikapp.BBDD.Dao.GrupoDao;
import com.app.didaktikapp.BBDD.Dao.SanMiguelDao;
import com.app.didaktikapp.BBDD.Dao.TrenDao;
import com.app.didaktikapp.BBDD.Dao.UniversitateaDao;
import com.app.didaktikapp.BBDD.Dao.UsuarioDao;
import com.app.didaktikapp.BBDD.Dao.ZumeltzegiDao;
import com.app.didaktikapp.BBDD.Modelos.ActividadErrota;
import com.app.didaktikapp.BBDD.Modelos.ActividadSanMiguel;
import com.app.didaktikapp.BBDD.Modelos.ActividadTren;
import com.app.didaktikapp.BBDD.Modelos.ActividadUniversitatea;
import com.app.didaktikapp.BBDD.Modelos.ActividadZumeltzegi;
import com.app.didaktikapp.BBDD.Modelos.Grupo;
import com.app.didaktikapp.BBDD.Modelos.Usuario;

import java.util.List;


@Database(entities = {Grupo.class,
                    Usuario.class, ActividadZumeltzegi.class,
                    ActividadUniversitatea.class,
                    ActividadTren.class,
                    ActividadSanMiguel.class,
                    ActividadErrota.class},
        version = 2 )
public abstract class AppDatabase extends RoomDatabase {


    public abstract GrupoDao getGrupoDao();

    public abstract UsuarioDao getUsuarioDao();

    public abstract ZumeltzegiDao getZumeltzegiDao();

    public abstract UniversitateaDao getUniversitateaDao();

    public abstract TrenDao getTrenDao();

    public abstract SanMiguelDao getSanMiguelDao();

    public abstract ErrotaDao getErrotaDao();







}
