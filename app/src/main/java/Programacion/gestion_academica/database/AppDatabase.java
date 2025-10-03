package Programacion.gestion_academica.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Programacion.gestion_academica.dao.CalificacionDao;
import Programacion.gestion_academica.dao.EstudianteDao;
import Programacion.gestion_academica.dao.InscripcionDao;
import Programacion.gestion_academica.dao.MateriaDao;
import Programacion.gestion_academica.model.Calificacion;
import Programacion.gestion_academica.model.Estudiante;
import Programacion.gestion_academica.model.Inscripcion;
import Programacion.gestion_academica.model.Materia;

@Database(entities = {Estudiante.class, Materia.class, Inscripcion.class, Calificacion.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EstudianteDao estudianteDao();
    public abstract MateriaDao materiaDao();
    public abstract InscripcionDao inscripcionDao();
    public abstract CalificacionDao calificacionDao();

    private static volatile AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "gestion_academica_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
