package com.app.didaktikapp;

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
        db.execSQL("DROP TABLE IF EXISTS Prueba");
        db.execSQL("CREATE TABLE Prueba (idPrueba INTEGER PRIMARY KEY, texto TEXT)");


        //Temporal
        db.execSQL("DROP TABLE IF EXISTS ZumeltzegiDorrea");
        db.execSQL("CREATE TABLE ZumeltzegiDorrea(completado INTEGER PRIMARY KEY, fotos INTEGER, sopa INTEGER)");
        db.execSQL("INSERT INTO ZumeltzegiDorrea(completado,fotos,sopa) VALUES(-1,0,0)");

        db.execSQL("DROP TABLE IF EXISTS SanMiguelParrokia");
        db.execSQL("CREATE TABLE SanMiguelParrokia(completado INTEGER PRIMARY KEY, test INTEGER, fotos INTEGER)");
        db.execSQL("INSERT INTO SanMiguelParrokia(completado,test,fotos) VALUES(-1,0,0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS Prueba");
//        db.execSQL("CREATE TABLE Prueba (idPrueba INTEGER PRIMARY KEY, texto TEXT)");
    }

}
