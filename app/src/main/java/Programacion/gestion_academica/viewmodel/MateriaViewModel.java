package Programacion.gestion_academica.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import Programacion.gestion_academica.model.Materia;
import Programacion.gestion_academica.repository.MateriaRepository;

public class MateriaViewModel extends AndroidViewModel {

    private final MateriaRepository repository;
    private final LiveData<List<Materia>> allMaterias;

    public MateriaViewModel(@NonNull Application application) {
        super(application);
        repository = new MateriaRepository(application);
        allMaterias = repository.getAllMaterias();
    }

    public LiveData<List<Materia>> getAllMaterias() {
        return allMaterias;
    }

    public void insert(Materia materia) {
        repository.insert(materia);
    }

    public void update(Materia materia) {
        repository.update(materia);
    }

    public void delete(Materia materia) {
        repository.delete(materia);
    }
}
