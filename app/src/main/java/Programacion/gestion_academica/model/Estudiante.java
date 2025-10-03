package Programacion.gestion_academica.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "estudiantes")
public class Estudiante {

    @PrimaryKey(autoGenerate = true)
    private int idEstudiante;

    private String nombre;
    private String apellido;
    private String email;

    public Estudiante(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    // Getters
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
}
