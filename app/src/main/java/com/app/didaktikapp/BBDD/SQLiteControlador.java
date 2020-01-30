package com.app.didaktikapp.BBDD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
//    METODO DISPONIBILIDAD FRAGMENT, USA LAS TABLAS VIEJAS
//    public int disponibilidadFragment(Fragment fragment) {
//        int cod = -1;
//        String tabla="";
//
//        SQLiteHelper sqlh = getSQLiteHelper();
//        SQLiteDatabase db = sqlh.getReadableDatabase();
//
//        switch (fragment.getClass().getName()){
//
//            case "FragmentZumeltzegi":
//                tabla = "ZumeltzegiDorrea";
//                break;
//            case "FragmentSanMiguel":
//                tabla = "SanMiguelParrokia";
//                break;
//            case "FragmentUnibersitatea":
//                tabla = "Unibertsitatea";
//                break;
//            case "FragmentTrenTexto":
//                tabla = "Trena";
//                break;
//            case "FragmentErrotaTextos":
//                tabla = "SanMiguelErrotaTextos";
//                break;
//
//        }
//
//        if (db != null){
//            String[] campos = new String[] {"*"};
//            Cursor c = db.query(tabla, campos, null, null, null, null, null);
//
//            if (c.moveToFirst()) {
//                cod = c.getInt(0);
//            } else {
//                Log.e("SQLite","No se han podido obtener datos del Fragment "+tabla);
//            }
//
//            Log.i("SQLite","Datos de "+tabla+" obtenidos");
//            db.close();
//
//        } else {
//            Log.e("SQLite","Error conectando a la BD");
//        }
//
//        return cod;
//    }

}
