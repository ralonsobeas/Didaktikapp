package com.app.didaktikapp.BBDD.Modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.app.didaktikapp.BBDD.TimestampConverter;

import java.util.Date;
import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "GRUPO",
        foreignKeys ={ @ForeignKey(entity = ActividadZumeltzegi.class,
                                parentColumns = "ID",
                                childColumns = "IDZUMELTZEGI",
                                onDelete = CASCADE),
                        @ForeignKey(entity = ActividadUniversitatea.class,
                                parentColumns = "ID",
                                childColumns = "IDUNIVERSIDAD",
                                onDelete = CASCADE),
                        @ForeignKey(entity = ActividadTren.class,
                                parentColumns = "ID",
                                childColumns = "IDTREN",
                                onDelete = CASCADE),
                        @ForeignKey(entity = ActividadSanMiguel.class,
                                parentColumns = "ID",
                                childColumns = "IDPARROQUIA",
                                onDelete = CASCADE),
                        @ForeignKey(entity = ActividadErrota.class,
                                parentColumns = "ID",
                                childColumns = "IDERROTA",
                                onDelete = CASCADE)})
@TypeConverters(TimestampConverter.class)
public class Grupo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    @NonNull
    private Long id ;

    @ColumnInfo(name = "NOMBRE")
    private String nombre;

    @ColumnInfo(name = "FECHA")
    private Date fecha;

    @ColumnInfo(name = "IDZUMELTZEGI")
    private Long idZumeltzegi;

    @ColumnInfo(name = "IDPARROQUIA")
    private Long idParroquia;

    @ColumnInfo(name = "IDUNIVERSIDAD")
    private Long idUniversidad;

    @ColumnInfo(name = "IDERROTA")
    private Long idErrota;

    @ColumnInfo(name = "IDTREN")
    private Long idTren;

    @ColumnInfo(name = "IDGERNIKA")
    private Long idGernika;



    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdZumeltzegi() {
        return idZumeltzegi;
    }

    public void setIdZumeltzegi(Long idZumeltzegi) {
        this.idZumeltzegi = idZumeltzegi;
    }

    public Long getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(Long idParroquia) {
        this.idParroquia = idParroquia;
    }

    public Long getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Long idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public Long getIdErrota() {
        return idErrota;
    }

    public void setIdErrota(Long idErrota) {
        this.idErrota = idErrota;
    }

    public Long getIdGernika() {
        return idGernika;
    }

    public void setIdGernika(Long idGernika) {
        this.idGernika = idGernika;
    }

    public Long getIdTren() {
        return idTren;
    }

    public void setIdTren(Long idTren) {
        this.idTren = idTren;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
