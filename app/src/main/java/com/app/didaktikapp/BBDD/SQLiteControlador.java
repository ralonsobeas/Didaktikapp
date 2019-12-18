package com.app.didaktikapp.BBDD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.fragment.app.Fragment;

public class SQLiteControlador {

    private String nombrebd;
    private Context context;
    public SQLiteControlador(Context context) {
        this.context = context;
        nombrebd = "DBDidaktikapp";
    }

    public SQLiteControlador(Context context, String nombrebd) {
        this.context = context;
        this.nombrebd = nombrebd;
    }

    private SQLiteHelper getSQLiteHelper() {
        SQLiteHelper sqlh =new SQLiteHelper(context, nombrebd, null, 2);
        return sqlh;
    }

    /*
    * Ponemos -1 o 0 al campo completado,
    * > -1 si ese punto no va a ser accesible
    * > 0 si es accesible
    * > Tendrá 1 cuando esté completado
    *
    * Se pone 0 a los campos de actividades
    * Llevarán un 1 cuando estén completadas*/
    public void iniciarApp() {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        if (db != null){

            db.execSQL("UPDATE ZumeltzegiDorrea SET completado=0,fotos=0,sopa=0");
            db.execSQL("UPDATE SanMiguelParrokia SET completado=-1,test=0,fotos=0");
            db.execSQL("UPDATE Unibertsitatea SET completado=-1,texto=0,preguntas=0,imagenes=0");
            db.execSQL("UPDATE Trena SET completado=-1,puzzle=0,audio=0,texto=0");
            db.execSQL("UPDATE SanMiguelErrota SET completado=-1,frases=0,video=0,fotos=0");
            db.execSQL("UPDATE GernikakoArbola SET completado=-1,texto=0,test=0,puzzle=0");

            Log.i("SQLite","BD reiniciada");
            db.close();

        } else {
            Log.e("SQLite","Error reseteando la BD al iniciar la app");
        }
    }

    /*
    * Se devuelve:
    * -1 si no se puede hacer
    * 0 si no está empezado
    * 1 si está empezado
    * 2 si está terminado*/
    public int disponibilidadZumeltzegiDorrea() {
        int cod = -1;

        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getReadableDatabase();

        if (db != null){

            Cursor c = db.rawQuery("SELECT completado FROM ZumeltzegiDorrea",null);
            if (c.moveToFirst()) {
                cod = c.getInt(0);
            } else {
                Log.e("SQLite","No se han podido obtener datos de Zumeltzegi Dorrea");
            }

            Log.i("SQLite","Datos de Zumeltzegui Dorrea obtenidos");
            db.close();

        } else {
            Log.e("SQLite","Error conectando a la BD pidiendo datos de ubicaciones");
        }

        return cod;
    }

    public int disponibilidadSanMiguelParrokia() {
        int cod = -1;

        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getReadableDatabase();

        if (db != null){

            Cursor c = db.rawQuery("SELECT completado FROM SanMiguelParrokia",null);
            if (c.moveToFirst()) {
                cod = c.getInt(0);
            } else {
                Log.e("SQLite","No se han podido obtener datos de San Miguel Parrokia");
            }

            Log.i("SQLite","Datos de San Miguel Parrokia Dorrea obtenidos");
            db.close();

        } else {
            Log.e("SQLite","Error conectando a la BD pidiendo datos de ubicaciones");
        }

        return cod;
    }

    public int disponibilidadUnibertsitatea() {
        int cod = -1;

        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getReadableDatabase();

        if (db != null){

            Cursor c = db.rawQuery("SELECT completado FROM Unibertsitatea",null);
            if (c.moveToFirst()) {
                cod = c.getInt(0);
            } else {
                Log.e("SQLite","No se han podido obtener datos de Unibertsitatea");
            }

            Log.i("SQLite","Datos de Unibertsitatea obtenidos");
            db.close();

        } else {
            Log.e("SQLite","Error conectando a la BD pidiendo datos de ubicaciones");
        }

        return cod;
    }

    public int disponibilidadTrena() {
        int cod = -1;

        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getReadableDatabase();

        if (db != null){

            Cursor c = db.rawQuery("SELECT completado FROM Trena",null);
            if (c.moveToFirst()) {
                cod = c.getInt(0);
            } else {
                Log.e("SQLite","No se han podido obtener datos de Trena");
            }

            Log.i("SQLite","Datos de Trena obtenidos");
            db.close();

        } else {
            Log.e("SQLite","Error conectando a la BD pidiendo datos de ubicaciones");
        }

        return cod;
    }

    public int disponibilidadSanMiguelErrota() {
        int cod = -1;

        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getReadableDatabase();

        if (db != null){

            Cursor c = db.rawQuery("SELECT completado FROM SanMiguelErrota",null);
            if (c.moveToFirst()) {
                cod = c.getInt(0);
            } else {
                Log.e("SQLite","No se han podido obtener datos de San Miguel Errota");
            }

            Log.i("SQLite","Datos de San Miguel Errota obtenidos");
            db.close();

        } else {
            Log.e("SQLite","Error conectando a la BD pidiendo datos de ubicaciones");
        }

        return cod;
    }

    public int disponibilidadGernikakoArbola() {
        int cod = -1;

        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getReadableDatabase();

        if (db != null){

            Cursor c = db.rawQuery("SELECT completado FROM GernikakoArbola",null);
            if (c.moveToFirst()) {
                cod = c.getInt(0);
            } else {
                Log.e("SQLite","No se han podido obtener datos de Gernikako Arbola");
            }

            Log.i("SQLite","Datos de Gernikako Arbola obtenidos");
            db.close();

        } else {
            Log.e("SQLite","Error conectando a la BD pidiendo datos de ubicaciones");
        }

        return cod;
    }

    public int disponibilidadFragment(Fragment fragment) {
        int cod = -1;
        String tabla="";

        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getReadableDatabase();

        switch (fragment.getClass().getName()){

            case "FragmentZumeltzegi":
                tabla = "ZumeltzegiDorrea";
                break;
            case "FragmentSanMiguel":
                tabla = "SanMiguelParrokia";
                break;
            case "FragmentUnibersitatea":
                tabla = "Unibertsitatea";
                break;
            case "FragmentTrenTexto":
                tabla = "Trena";
                break;
            case "FragmentErrota":
                tabla = "SanMiguelErrota";
                break;

        }

        if (db != null){
            String[] campos = new String[] {"*"};
            Cursor c = db.query(tabla, campos, null, null, null, null, null);

            if (c.moveToFirst()) {
                cod = c.getInt(0);
            } else {
                Log.e("SQLite","No se han podido obtener datos del Fragment "+tabla);
            }

            Log.i("SQLite","Datos de "+tabla+" obtenidos");
            db.close();

        } else {
            Log.e("SQLite","Error conectando a la BD pidiendo datos de ubicaciones");
        }

        return cod;
    }

}
