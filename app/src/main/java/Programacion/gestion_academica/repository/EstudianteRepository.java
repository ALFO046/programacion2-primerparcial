package Programacion.gestion_academica.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import Programacion.gestion_academica.dao.EstudianteDao;
import Programacion.gestion_academica.database.AppDatabase;
import Programacion.gestion_academica.model.Estudiante;

public class EstudianteRepository {

    private final EstudianteDao estudianteDao;
    private final LiveData<List<Estudiante>> allEstudiantes;

    public EstudianteRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        estudianteDao = db.estudianteDao();
        allEstudiantes = estudianteDao.getAllEstudiantes();
    }

    public LiveData<List<Estudiante>> getAllEstudiantes() {
        return allEstudiantes;
    }

    public void insert(Estudiante estudiante) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            estudianteDao.insert(estudiante);
        });
    }

    public void update(Estudiante estudiante) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            estudianteDao.update(estudiante);
        });
    }

    public void delete(Estudiante estudiante) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            estudianteDao.delete(estudiante);
        });
    }
}
