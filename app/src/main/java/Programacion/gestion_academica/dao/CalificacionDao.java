package Programacion.gestion_academica.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Programacion.gestion_academica.model.Calificacion;

@Dao
public interface CalificacionDao {

    @Insert
    void insert(Calificacion calificacion);

    @Update
    void update(Calificacion calificacion);

    @Query("SELECT * FROM calificaciones WHERE idEstudiante = :idEstudiante")
    LiveData<List<Calificacion>> getCalificacionesByEstudiante(int idEstudiante);

}
