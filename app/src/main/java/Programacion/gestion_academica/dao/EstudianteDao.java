package Programacion.gestion_academica.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Programacion.gestion_academica.model.Estudiante;

@Dao
public interface EstudianteDao {

    @Insert
    void insert(Estudiante estudiante);

    @Update
    void update(Estudiante estudiante);

    @Delete
    void delete(Estudiante estudiante);

    @Query("DELETE FROM estudiantes")
    void deleteAllEstudiantes();

    @Query("SELECT * FROM estudiantes ORDER BY apellido, nombre ASC")
    LiveData<List<Estudiante>> getAllEstudiantes();
}
