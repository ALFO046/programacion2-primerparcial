package Programacion.gestion_academica.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "calificaciones",
        primaryKeys = {"idCalificacion"},
        indices = {@Index(value = {"idEstudiante", "idMateria"})},
        foreignKeys = {
                @ForeignKey(entity = Inscripcion.class,
                        parentColumns = {"idEstudiante", "idMateria"},
                        childColumns = {"idEstudiante", "idMateria"},
                        onDelete = ForeignKey.CASCADE)
        })
public class Calificacion {

    private int idCalificacion;

    private int idEstudiante;
    private int idMateria;

    private double nota;
    private String observacion;


    public Calificacion(int idEstudiante, int idMateria, double nota, String observacion) {
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
        this.nota = nota;
        this.observacion = observacion;
    }

    // Getters
    public int getIdCalificacion() {
        return idCalificacion;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public double getNota() {
        return nota;
    }

    public String getObservacion() {
        return observacion;
    }

    // Setters
    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }
}
