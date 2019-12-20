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
import com.app.didaktikapp.BBDD.Dao.GrupoDao;
import com.app.didaktikapp.BBDD.Dao.UsuarioDao;
import com.app.didaktikapp.BBDD.Modelos.Grupo;
import com.app.didaktikapp.BBDD.Modelos.Usuario;

import java.util.List;


@Database(entities = {Grupo.class, Usuario.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "basic-sample-db";


    public abstract GrupoDao getGrupoDao();

    public abstract UsuarioDao getUsuarioDao();

    private static AppDatabase appDB;

    public static AppDatabase getInstance(Context context) {
        if (null == appDB) {
            appDB = buildDatabaseInstance(context);
        }
        return appDB;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        appDB = null;
    }


}
