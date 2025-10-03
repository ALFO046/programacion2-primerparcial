package Programacion.gestion_academica.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import Programacion.gestion_academica.model.Inscripcion;

@Dao
public interface InscripcionDao {

    @Insert
    void insert(Inscripcion inscripcion);

    @Query("DELETE FROM inscripciones WHERE idEstudiante = :idEstudiante AND idMateria = :idMateria")
    void delete(int idEstudiante, int idMateria);
}
