package com.example.gestionacademica.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gestionacademica.R;
import com.example.gestionacademica.adapter.EstudianteAdapter;
import com.example.gestionacademica.databinding.FragmentEstudiantesBinding;
import com.example.gestionacademica.model.Estudiante;
import com.example.gestionacademica.viewmodel.EstudianteViewModel;

public class EstudiantesFragment extends Fragment {

    private FragmentEstudiantesBinding binding;
    private EstudianteViewModel estudianteViewModel;
    private EstudianteAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEstudiantesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar ViewModel
        estudianteViewModel = new ViewModelProvider(this).get(EstudianteViewModel.class);

        // Configurar RecyclerView (Requisito 5)
        binding.recyclerViewEstudiantes.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewEstudiantes.setHasFixedSize(true);
        adapter = new EstudianteAdapter();
        binding.recyclerViewEstudiantes.setAdapter(adapter);

        // Observar los datos del LiveData (Listado - Requisito 2)
        estudianteViewModel.getAllEstudiantes().observe(getViewLifecycleOwner(), estudiantes -> {
            adapter.submitList(estudiantes);
        });

        // Click en FAB (Alta - Requisito 2)
        binding.fabAgregarEstudiante.setOnClickListener(v -> {
            // Navegar a AddEditFragment, pasando ID -1 para indicar "Nuevo"
            Bundle bundle = new Bundle();
            bundle.putInt("estudianteId", -1);
            NavHostFragment.findNavController(this).navigate(R.id.action_estudiantesFragment_to_addEditEstudianteFragment, bundle);
        });

        // Click en un item para editar (Modificación - Requisito 2)
        adapter.setOnItemClickListener(estudiante -> {
            Bundle bundle = new Bundle();
            bundle.putInt("estudianteId", estudiante.getIdEstudiante());
            NavHostFragment.findNavController(this).navigate(R.id.action_estudiantesFragment_to_addEditEstudianteFragment, bundle);
        });

        // Swipe-to-delete (Baja - Requisito 2)
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Estudiante estudianteToDelete = adapter.getEstudianteAt(viewHolder.getAdapterPosition());
                estudianteViewModel.delete(estudianteToDelete);
                Toast.makeText(getContext(), "Estudiante eliminado.", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recyclerViewEstudiantes);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}