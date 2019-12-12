package com.app.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

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

    public void crearPrueba() {
        SQLiteHelper sqlh =new SQLiteHelper(context, nombrebd, null, 1);
        SQLiteDatabase db = sqlh.getWritableDatabase();

        if (db != null){

            for (int x=1;x>=3;x++) {
                String p = "Prueba ("+x+"/3)";
                db.execSQL("INSERT INTO Prueba (idPrueba, texto) VALUES ("+x+" ,'"+p+"')");
            }

            Log.i("SQLite","Datos de prueba insertados");
            db.close();

        } else {
            Log.e("SQLite","Error abriendo la BD");
        }
    }

}
