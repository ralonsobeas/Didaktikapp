package com.app.didaktikapp.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        * Si la BD no está creada, el programa se meterá a esta función y la creará.
        * Al actualizar la versión de la BD (en SQLiteControlador), irá a la función de abajo
        *
        * En la funcion de update se explica la estructura de la BD */
        db.execSQL("DROP TABLE IF EXISTS ZumeltzegiDorrea");
        db.execSQL("DROP TABLE IF EXISTS SanMiguelParrokia");
        db.execSQL("DROP TABLE IF EXISTS Unibertsitatea");
        db.execSQL("DROP TABLE IF EXISTS Trena");
        db.execSQL("DROP TABLE IF EXISTS SanMiguelErrota");
        db.execSQL("DROP TABLE IF EXISTS GernikakoArbola");

        db.execSQL("DROP TABLE IF EXISTS Grupo");
        db.execSQL("CREATE TABLE Grupo(" +
                "IDGrupo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombre TEXT," +
                "Fecha TEXT)");

        db.execSQL("DROP TABLE IF EXISTS Participante");
        db.execSQL("CREATE TABLE Participante(" +
                "IDParticipante INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IDGrupo INTEGER," +
                "Nombre TEXT," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

        db.execSQL("DROP TABLE IF EXISTS ActividadZumeltzegi");
        db.execSQL("CREATE TABLE ActividadZumeltzegi(" +
                "IDActividad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Completado INTEGER," +
                "Foto1 TEXT," +
                "Foto2 TEXT," +
                "IDGrupo INTEGER," +
                "Fase INTEGER," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

        db.execSQL("DROP TABLE IF EXISTS ActividadSanMiguel");
        db.execSQL("CREATE TABLE ActividadSanMiguel(" +
                "IDActividad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Completado INTEGER," +
                "Test DOUBLE," +
                "Foto1 TEXT," +
                "Foto2 TEXT," +
                "Foto3 TEXT," +
                "IDGrupo INTEGER," +
                "Fase INTEGER," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

        db.execSQL("DROP TABLE IF EXISTS ActividadUniversidad");
        db.execSQL("CREATE TABLE ActividadUniversidad(" +
                "IDActividad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Completado INTEGER," +
                "Test DOUBLE," +
                "Foto1 TEXT," +
                "Foto2 TEXT," +
                "Foto3 TEXT," +
                "IDGrupo INTEGER," +
                "Fase INTEGER," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

        db.execSQL("DROP TABLE IF EXISTS ActividadTren");
        db.execSQL("CREATE TABLE ActividadTren(" +
                "IDActividad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Completado INTEGER," +
                "Puzle INTEGER," +
                "Palabras DOUBLE," +
                "IDGrupo INTEGER," +
                "Fase INTEGER," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

        db.execSQL("DROP TABLE IF EXISTS ActividadErrota");
        db.execSQL("CREATE TABLE ActividadErrota(" +
                "IDActividad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Completado INTEGER," +
                "Frases INTEGER," +
                "Foto1 TEXT," +
                "Foto2 TEXT," +
                "IDGrupo INTEGER," +
                "Fase INTEGER," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*
        * Se guardarán un grupo y sus alumnos
        *
        * Cada grupo tendrá unas actividades asignadas, guardadas en distintas tablas
        *
        * Estos comentarios son para que mis compañeros de equipo entiendan la estructura,
        * > pero vosotros los de Txurdinaga tambien podeis usarlos :P */
        db.execSQL("DROP TABLE IF EXISTS ZumeltzegiDorrea");
        db.execSQL("DROP TABLE IF EXISTS SanMiguelParrokia");
        db.execSQL("DROP TABLE IF EXISTS Unibertsitatea");
        db.execSQL("DROP TABLE IF EXISTS Trena");
        db.execSQL("DROP TABLE IF EXISTS SanMiguelErrota");
        db.execSQL("DROP TABLE IF EXISTS GernikakoArbola");

        db.execSQL("DROP TABLE IF EXISTS Grupo");
        db.execSQL("CREATE TABLE Grupo(" +
                "IDGrupo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombre TEXT," +
                "Fecha REAL)");

        db.execSQL("DROP TABLE IF EXISTS Participante");
        db.execSQL("CREATE TABLE Participante(" +
                "IDParticipante INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IDGrupo INTEGER," +
                "Nombre TEXT," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

        db.execSQL("DROP TABLE IF EXISTS ActividadZumeltzegi");
        db.execSQL("CREATE TABLE ActividadZumeltzegi(" +
                "IDActividad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Completado INTEGER," +
                "Foto1 TEXT," +
                "Foto2 TEXT," +
                "IDGrupo INTEGER," +
                "Fase INTEGER," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

        db.execSQL("DROP TABLE IF EXISTS ActividadSanMiguel");
        db.execSQL("CREATE TABLE ActividadSanMiguel(" +
                "IDActividad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Completado INTEGER," +
                "Test DOUBLE," +
                "Foto1 TEXT," +
                "Foto2 TEXT," +
                "Foto3 TEXT," +
                "IDGrupo INTEGER," +
                "Fase INTEGER," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

        db.execSQL("DROP TABLE IF EXISTS ActividadUniversidad");
        db.execSQL("CREATE TABLE ActividadUniversidad(" +
                "IDActividad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Completado INTEGER," +
                "Test DOUBLE," +
                "Foto1 TEXT," +
                "Foto2 TEXT," +
                "Foto3 TEXT," +
                "IDGrupo INTEGER," +
                "Fase INTEGER," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

        db.execSQL("DROP TABLE IF EXISTS ActividadTren");
        db.execSQL("CREATE TABLE ActividadTren(" +
                "IDActividad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Completado INTEGER," +
                "Puzle INTEGER," +
                "Palabras DOUBLE," +
                "IDGrupo INTEGER," +
                "Fase INTEGER," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

        db.execSQL("DROP TABLE IF EXISTS ActividadErrota");
        db.execSQL("CREATE TABLE ActividadErrota(" +
                "IDActividad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Completado INTEGER," +
                "Frases INTEGER," +
                "Foto1 TEXT," +
                "Foto2 TEXT," +
                "IDGrupo INTEGER," +
                "Fase INTEGER," +
                "FOREIGN KEY(IDGrupo) REFERENCES Grupo(IDGrupo))");

    }

}
