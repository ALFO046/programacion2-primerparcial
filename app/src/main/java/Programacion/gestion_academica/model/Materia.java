package Programacion.gestion_academica.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "materias")
public class Materia {

    @PrimaryKey(autoGenerate = true)
    private int idMateria;

    private String nombreMateria;
    private String descripcion;

    public Materia(String nombreMateria, String descripcion) {
        this.nombreMateria = nombreMateria;
        this.descripcion = descripcion;
    }

    // Getters
    public int getIdMateria() {
        return idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Setters
    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }
}
