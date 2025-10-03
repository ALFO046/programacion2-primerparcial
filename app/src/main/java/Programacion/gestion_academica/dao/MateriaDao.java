package Programacion.gestion_academica.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Programacion.gestion_academica.model.Materia;

@Dao
public interface MateriaDao {

    @Insert
    void insert(Materia materia);

    @Update
    void update(Materia materia);

    @Delete
    void delete(Materia materia);

    @Query("SELECT * FROM materias ORDER BY nombreMateria ASC")
    LiveData<List<Materia>> getAllMaterias();
}
