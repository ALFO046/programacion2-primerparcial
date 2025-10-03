package Programacion.gestion_academica.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import Programacion.gestion_academica.dao.MateriaDao;
import Programacion.gestion_academica.database.AppDatabase;
import Programacion.gestion_academica.model.Materia;

public class MateriaRepository {

    private final MateriaDao materiaDao;
    private final LiveData<List<Materia>> allMaterias;

    public MateriaRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        materiaDao = db.materiaDao();
        allMaterias = materiaDao.getAllMaterias();
    }

    public LiveData<List<Materia>> getAllMaterias() {
        return allMaterias;
    }

    public void insert(Materia materia) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            materiaDao.insert(materia);
        });
    }

    public void update(Materia materia) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            materiaDao.update(materia);
        });
    }

    public void delete(Materia materia) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            materiaDao.delete(materia);
        });
    }
}
