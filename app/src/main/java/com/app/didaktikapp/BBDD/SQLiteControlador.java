package com.app.didaktikapp.BBDD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        return new SQLiteHelper(context, nombrebd, null, 3);
    }

    // Devuelve el id del grupo que ha creado para guardarlo en MapActivity
    public int crearGrupo(String grupo, String[] alumnos) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date time = Calendar.getInstance().getTime();
        String fecha = sdf.format(time);

        db.execSQL("INSERT INTO Grupo(Nombre,Fecha) VALUES('"+grupo+"','"+fecha+"')");

        Cursor c = db.rawQuery("SELECT MAX(IDGrupo) FROM Grupo",null);
        c.moveToFirst();
        int idgrupo = c.getInt(0);

        if (alumnos.length==0) {
            db.execSQL("INSERT INTO Participante(IDGrupo,Nombre) VALUES('"+idgrupo+"','Alumno')");
        } else {
            for (int x=0;x<alumnos.length;x++) {
                db.execSQL("INSERT INTO Participante(IDGrupo,Nombre) VALUES('"+idgrupo+"','"+alumnos[x]+"')");
            }
        }

        c.close();
        db.close();

        return idgrupo;
    }

    //Se le llama nada mas crear un grupo para inicializar las actividades
    public void iniciarActividades(int idgrupo) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        db.execSQL("UPDATE ZumeltzegiDorrea SET completado=0,fotos=0,sopa=0");
        db.execSQL("UPDATE SanMiguelParrokia SET completado=-1,test=0,fotos=0");
        db.execSQL("UPDATE Unibertsitatea SET completado=-1,texto=0,preguntas=0,imagenes=0");
        db.execSQL("UPDATE Trena SET completado=-1,puzzle=0,audio=0,texto=0");
        db.execSQL("UPDATE SanMiguelErrota SET completado=-1,frases=0,video=0,fotos=0");

        db.execSQL("INSERT INTO ActividadZumeltzegi(Completado,IDGrupo,Fase) " +
                "VALUES("+0+","+idgrupo+","+0+")");
        db.execSQL("INSERT INTO ActividadSanMiguel(Completado,IDGrupo,Fase) " +
                "VALUES("+0+","+idgrupo+","+0+")");
        db.execSQL("INSERT INTO ActividadUniversidad(Completado,IDGrupo,Fase) " +
                "VALUES("+0+","+idgrupo+","+0+")");
        db.execSQL("INSERT INTO ActividadTren(Completado,IDGrupo,Fase) " +
                "VALUES("+0+","+idgrupo+","+0+")");
        db.execSQL("INSERT INTO ActividadErrota(Completado,IDGrupo,Fase) " +
                "VALUES("+0+","+idgrupo+","+0+")");

        db.close();


    }

    // Comprueba la disponibilidad de una actividad.
    // Se le pasa el nombre de la tabla y el id del grupo
    public int disponibilidadActividad(String actividad, int idgrupo) {
        int cod = -1;

        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT completado FROM "+actividad+" WHERE IDGrupo = "+idgrupo,null);
        if (c.moveToFirst()) {
            cod = c.getInt(0);
        } else {
            Log.e("SQLite","Fallo obteniendo disponibilidad ("+actividad+")");
        }

        c.close();
        db.close();

        return cod;
    }

    //Igual que el anterior, para marcar que se empieza una actividad
    public void empezarActividad(String actividad, int idgrupo) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        db.execSQL("UPDATE "+actividad+" SET Completado = 1 WHERE IDGrupo = "+idgrupo);

        db.close();
    }

    /*
    * Metodos para actualizar la disponibilidad de los puntos y sus actividades
    *
    * Se pasa el string actualizar para identificar qué acción se ha realizado
    * y actualizar la BD acorde a ello
    *
    * Cuando se finaliza la ultima actividad se habilita el acceso a la siguiente
    *
    * METODOS INUTILES PARA VERSION 3 DE BBDD */
    public void actualizarZumeltzegiDorrea(String actualizar) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        if (db != null){

            switch (actualizar) {
                case "comenzar":
                    db.execSQL("UPDATE ZumeltzegiDorrea SET completado=1");
                    break;
                case "fotos":
                    db.execSQL("UPDATE ZumeltzegiDorrea SET fotos=1");
                    break;
                case "sopa":
                    db.execSQL("UPDATE ZumeltzegiDorrea SET completado=2,sopa=1");
                    db.execSQL("UPDATE SanMiguelParrokia SET completado=0");
                    break;
                default:
                    Log.e("SQLite","Método ("+actualizar+") no encontrado para actualizar Zumeltzegi Dorrea");
                    break;
            }

            db.close();

        } else {
            Log.e("SQLite","Error actualizando Zumeltzegi Dorrea ("+actualizar+")");
        }
    }

    public void actualizarSanMiguelParrokia(String actualizar) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        if (db != null){

            switch (actualizar) {
                case "comenzar":
                    db.execSQL("UPDATE SanMiguelParrokia SET completado=1");
                    break;
                case "test":
                    db.execSQL("UPDATE SanMiguelParrokia SET test=1");
                    break;
                case "fotos":
                    db.execSQL("UPDATE SanMiguelParrokia SET completado=2,fotos=1");
                    db.execSQL("UPDATE Unibertsitatea SET completado=0");
                    break;
                default:
                    Log.e("SQLite","Método ("+actualizar+") no encontrado para actualizar San Miguel Parrokia");
                    break;
            }

            db.close();

        } else {
            Log.e("SQLite","Error actualizando San Miguel Parrokia ("+actualizar+")");
        }
    }

    public void actualizarUnibertsitatea(String actualizar) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        if (db != null){

            switch (actualizar) {
                case "comenzar":
                    db.execSQL("UPDATE Unibertsitatea SET completado=1");
                    break;
                case "texto":
                    db.execSQL("UPDATE Unibertsitatea SET texto=1");
                    break;
                case "preguntas":
                    db.execSQL("UPDATE Unibertsitatea SET preguntas=1");
                    break;
                case "imagenes":
                    db.execSQL("UPDATE Unibertsitatea SET completado=2,imagenes=1");
                    db.execSQL("UPDATE Trena SET completado=0");
                    break;
                default:
                    Log.e("SQLite","Método ("+actualizar+") no encontrado para actualizar Unibertsitatea");
                    break;
            }

            db.close();

        } else {
            Log.e("SQLite","Error actualizando Unibertsitatea ("+actualizar+")");
        }
    }

    public void actualizarTrena(String actualizar) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        if (db != null){

            switch (actualizar) {
                case "comenzar":
                    db.execSQL("UPDATE Trena SET completado=1");
                    break;
                case "puzzle":
                    db.execSQL("UPDATE Trena SET puzzle=1");
                    break;
                case "audio":
                    db.execSQL("UPDATE Trena SET audio=1");
                    break;
                case "texto":
                    db.execSQL("UPDATE Trena SET completado=2,texto=1");
                    db.execSQL("UPDATE SanMiguelErrota SET completado=0");
                    break;
                default:
                    Log.e("SQLite","Método ("+actualizar+") no encontrado para actualizar Trena");
                    break;
            }

            db.close();

        } else {
            Log.e("SQLite","Error actualizando Trena ("+actualizar+")");
        }
    }

    public void actualizarSanMiguelErrota(String actualizar) {
        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getWritableDatabase();

        if (db != null){

            switch (actualizar) {
                case "comenzar":
                    db.execSQL("UPDATE SanMiguelErrota SET completado=1");
                    break;
                case "frases":
                    db.execSQL("UPDATE SanMiguelErrota SET frases=1");
                    break;
                case "video":
                    db.execSQL("UPDATE SanMiguelErrota SET video=1");
                    break;
                case "fotos":
                    db.execSQL("UPDATE Trena SET completado=2,fotos=1");
                    db.execSQL("UPDATE GernikakoArbola SET completado=0");
                    break;
                default:
                    Log.e("SQLite","Método ("+actualizar+") no encontrado para actualizar San Miguel Errota");
                    break;
            }

            db.close();

        } else {
            Log.e("SQLite","Error actualizando San Miguel Errota ("+actualizar+")");
        }
    }

    /* # ACTUALIZAR GERNIKAKO ARBOLA # */

    public boolean zumeltzegiFotos() {
        boolean cod = false;

        SQLiteHelper sqlh = getSQLiteHelper();
        SQLiteDatabase db = sqlh.getReadableDatabase();

        if (db != null){

            Cursor c = db.rawQuery("SELECT fotos FROM ZumeltzegiDorrea",null);
            if (c.moveToFirst()) {
                int estado = c.getInt(0);
                if (estado==1) cod = true;

            } else {
                Log.e("SQLite","Fallo obteniendo estado de fotos de Zumeltzegi");
            }

            db.close();

        } else {
            Log.e("SQLite","Error conectando a la BD");
        }

        return cod;
    }

}
