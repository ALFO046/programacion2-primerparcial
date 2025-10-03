package Programacion.gestion_academica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import Programacion.gestion_academica.model.Estudiante;
import Programacion.gestion_academica.repository.EstudianteRepository;

public class EstudianteViewModel extends AndroidViewModel {

    private final EstudianteRepository repository;
    private final LiveData<List<Estudiante>> allEstudiantes;

    public EstudianteViewModel(@NonNull Application application) {
        super(application);
        repository = new EstudianteRepository(application);
        allEstudiantes = repository.getAllEstudiantes();
    }

    public LiveData<List<Estudiante>> getAllEstudiantes() {
        return allEstudiantes;
    }

    public void insert(Estudiante estudiante) {
        repository.insert(estudiante);
    }

    public void update(Estudiante estudiante) {
        repository.update(estudiante);
    }

    public void delete(Estudiante estudiante) {
        repository.delete(estudiante);
    }
}
