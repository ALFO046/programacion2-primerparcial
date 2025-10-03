package Programacion.gestion_academica.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "inscripciones",
        primaryKeys = {"idEstudiante", "idMateria"},
        indices = {@Index(value = "idMateria")},
        foreignKeys = {
                @ForeignKey(entity = Estudiante.class,
                        parentColumns = "idEstudiante",
                        childColumns = "idEstudiante",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Materia.class,
                        parentColumns = "idMateria",
                        childColumns = "idMateria",
                        onDelete = ForeignKey.CASCADE)
        })
public class Inscripcion {

    private int idEstudiante;
    private int idMateria;

    public Inscripcion(int idEstudiante, int idMateria) {
        this.idEstudiante = idEstudiante;
        this.idMateria = idMateria;
    }

    // Getters
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public int getIdMateria() {
        return idMateria;
    }
}
