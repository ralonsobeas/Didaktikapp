package com.app.didaktikapp.FTP;

import com.app.didaktikapp.BBDD.Modelos.Actividad;
import com.app.didaktikapp.BBDD.Modelos.Grupo;

public class GrupoActividad {

    private Grupo grupo;
    private Actividad actividad;

    public GrupoActividad(Grupo grupo, Actividad actividad){

        this.grupo = grupo;
        this.actividad = actividad;

    }
}
