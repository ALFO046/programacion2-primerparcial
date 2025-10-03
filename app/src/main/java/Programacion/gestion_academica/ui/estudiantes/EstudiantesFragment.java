package Programacion.gestion_academica.ui.estudiantes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// Import correcto para la clase R de NUESTRO proyecto
import Programacion.gestion_academica.R;
// Import correcto para NUESTRO adaptador
import Programacion.gestion_academica.adapter.EstudianteAdapter;
// Import correcto para NUESTRA clase de ViewBinding
import Programacion.gestion_academica.databinding.FragmentEstudiantesBinding;
// Import correcto para NUESTRO modelo
import Programacion.gestion_academica.model.Estudiante;
// Import correcto para NUESTRO ViewModel
import Programacion.gestion_academica.viewmodel.EstudianteViewModel;

public class EstudiantesFragment extends Fragment {

    private FragmentEstudiantesBinding binding;
    private EstudianteViewModel estudianteViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEstudiantesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        estudianteViewModel = new ViewModelProvider(this).get(EstudianteViewModel.class);

        final EstudianteAdapter adapter = new EstudianteAdapter();
        binding.recyclerviewEstudiantes.setAdapter(adapter);
        binding.recyclerviewEstudiantes.setLayoutManager(new LinearLayoutManager(getContext()));

        estudianteViewModel.getAllEstudiantes().observe(getViewLifecycleOwner(), adapter::submitList);

        binding.fabAddEstudiante.setOnClickListener(v -> {
            NavHostFragment.findNavController(EstudiantesFragment.this)
                    .navigate(R.id.action_navigation_estudiantes_to_addEditEstudianteFragment);
        });

        adapter.setOnItemClickListener(estudiante -> {
            Bundle bundle = new Bundle();
            bundle.putInt("estudianteId", estudiante.getIdEstudiante());
            bundle.putString("nombre", estudiante.getNombre());
            bundle.putString("apellido", estudiante.getApellido());
            bundle.putString("email", estudiante.getEmail());
            NavHostFragment.findNavController(EstudiantesFragment.this)
                    .navigate(R.id.action_navigation_estudiantes_to_addEditEstudianteFragment, bundle);
        });

        // Tu funcionalidad para borrar deslizando, ahora con los imports correctos.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Estudiante estudianteParaBorrar = adapter.getEstudianteAt(viewHolder.getAdapterPosition());
                estudianteViewModel.delete(estudianteParaBorrar);
                Toast.makeText(getContext(), "Estudiante eliminado", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recyclerviewEstudiantes);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
