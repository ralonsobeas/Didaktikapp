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
        db.execSQL("CREATE TABLE ZumeltzegiDorrea(completado INTEGER PRIMARY KEY, fotos INTEGER, sopa INTEGER)");
        db.execSQL("INSERT INTO ZumeltzegiDorrea(completado,fotos,sopa) VALUES(0,0,0)");

        db.execSQL("DROP TABLE IF EXISTS SanMiguelParrokia");
        db.execSQL("CREATE TABLE SanMiguelParrokia(completado INTEGER PRIMARY KEY, test INTEGER, fotos INTEGER)");
        db.execSQL("INSERT INTO SanMiguelParrokia(completado,test,fotos) VALUES(-1,0,0)");

        db.execSQL("DROP TABLE IF EXISTS Unibertsitatea");
        db.execSQL("CREATE TABLE Unibertsitatea(completado INTEGER PRIMARY KEY, texto INTEGER, preguntas INTEGER, imagenes INTEGER)");
        db.execSQL("INSERT INTO Unibertsitatea(completado,texto,preguntas,imagenes) VALUES(-1,0,0,0)");

        db.execSQL("DROP TABLE IF EXISTS Trena");
        db.execSQL("CREATE TABLE Trena(completado INTEGER PRIMARY KEY, puzzle INTEGER, audio INTEGER, texto INTEGER)");
        db.execSQL("INSERT INTO Trena(completado,puzzle,audio,texto) VALUES(-1,0,0,0)");

        //!!!
        db.execSQL("DROP TABLE IF EXISTS SanMiguelErrota");
        db.execSQL("CREATE TABLE SanMiguelErrota(completado INTEGER PRIMARY KEY, frases INTEGER, video INTEGER, fotos INTEGER)");
        db.execSQL("INSERT INTO SanMiguelErrota(completado,frases,video,fotos) VALUES(-1,0,0,0)");

        //!!!
        db.execSQL("DROP TABLE IF EXISTS GernikakoArbola");
        db.execSQL("CREATE TABLE GernikakoArbola(completado INTEGER PRIMARY KEY, texto INTEGER, test INTEGER, puzzle INTEGER)");
        db.execSQL("INSERT INTO GernikakoArbola(completado,texto,test,puzzle) VALUES(-1,0,0,0)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*
        * ESTO ESTÁ SUJETO A CAMBIOS
        *
        * Se guardarán en Integers la accesibilidad de los puntos
        * > y el estado actual de sus actividades
        *
        * El campo completado de cada tabla corresponde a la disponibilidad de un punto
        * > El valor será -1 si el punto aún no es accesible
        * > El valor será 0 si el punto es accesible
        * > El valor será 1 si el punto está empezado
        * > El valor será 2 si el punto está terminado (y no accesible)
        *
        * El resto de campos corresponde a las actividades de cada punto
        * > Tendrán el valor 0 si no están hechas
        * > Tendrán el valor 1 si están hechas (y no accesibles)
        *
        * Estos comentarios son para que mis compañeros de equipo entiendan la estructura,
        * > pero vosotros los de Txurdinaga tambien podeis usarlos :P */
        db.execSQL("DROP TABLE IF EXISTS ZumeltzegiDorrea");
        db.execSQL("CREATE TABLE ZumeltzegiDorrea(completado INTEGER PRIMARY KEY, fotos INTEGER, sopa INTEGER)");
        db.execSQL("INSERT INTO ZumeltzegiDorrea(completado,fotos,sopa) VALUES(0,0,0)");

        db.execSQL("DROP TABLE IF EXISTS SanMiguelParrokia");
        db.execSQL("CREATE TABLE SanMiguelParrokia(completado INTEGER PRIMARY KEY, test INTEGER, fotos INTEGER)");
        db.execSQL("INSERT INTO SanMiguelParrokia(completado,test,fotos) VALUES(-1,0,0)");

        db.execSQL("DROP TABLE IF EXISTS Unibertsitatea");
        db.execSQL("CREATE TABLE Unibertsitatea(completado INTEGER PRIMARY KEY, texto INTEGER, preguntas INTEGER, imagenes INTEGER)");
        db.execSQL("INSERT INTO Unibertsitatea(completado,texto,preguntas,imagenes) VALUES(-1,0,0,0)");

        db.execSQL("DROP TABLE IF EXISTS Trena");
        db.execSQL("CREATE TABLE Trena(completado INTEGER PRIMARY KEY, puzzle INTEGER, audio INTEGER, texto INTEGER)");
        db.execSQL("INSERT INTO Trena(completado,puzzle,audio,texto) VALUES(-1,0,0,0)");

        //!!!
        db.execSQL("DROP TABLE IF EXISTS SanMiguelErrota");
        db.execSQL("CREATE TABLE SanMiguelErrota(completado INTEGER PRIMARY KEY, frases INTEGER, video INTEGER, fotos INTEGER)");
        db.execSQL("INSERT INTO SanMiguelErrota(completado,frases,video,fotos) VALUES(-1,0,0,0)");

        //!!!
        db.execSQL("DROP TABLE IF EXISTS GernikakoArbola");
        db.execSQL("CREATE TABLE GernikakoArbola(completado INTEGER PRIMARY KEY, texto INTEGER, test INTEGER, puzzle INTEGER)");
        db.execSQL("INSERT INTO GernikakoArbola(completado,texto,test,puzzle) VALUES(-1,0,0,0)");
    }

}
