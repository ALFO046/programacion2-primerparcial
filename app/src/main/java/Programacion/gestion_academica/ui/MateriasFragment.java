package com.example.gestionacademica.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gestionacademica.R;
import com.example.gestionacademica.adapter.MateriaAdapter;
import com.example.gestionacademica.databinding.FragmentMateriasBinding;
import com.example.gestionacademica.model.Materia;
import com.example.gestionacademica.viewmodel.MateriaViewModel;

public class MateriasFragment extends Fragment {

    private FragmentMateriasBinding binding;
    private MateriaViewModel materiaViewModel;
    private MateriaAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMateriasBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        materiaViewModel = new ViewModelProvider(this).get(MateriaViewModel.class);

        // Configurar RecyclerView
        binding.recyclerViewMaterias.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewMaterias.setHasFixedSize(true);
        adapter = new MateriaAdapter();
        binding.recyclerViewMaterias.setAdapter(adapter);

        // Observar los datos (Listado)
        materiaViewModel.getAllMaterias().observe(getViewLifecycleOwner(), materias -> {
            adapter.submitList(materias);
        });

        // Click en FAB (Alta)
        binding.fabAgregarMateria.setOnClickListener(v -> {
            // Navegar al fragmento de AddEditMateria (debes crear esta navegación)
            // NavHostFragment.findNavController(this).navigate(R.id.action_materiasFragment_to_addEditMateriaFragment);
            Toast.makeText(getContext(), "Navegar a Agregar Materia (Falta el Navigation Graph)", Toast.LENGTH_LONG).show();

            // DEMO DE ALTA INMEDIATA PARA PRUEBA:
            materiaViewModel.insert(new Materia("Programación " + Math.random(), "PRG-" + (int)(Math.random()*100)));
        });

        // Swipe-to-delete (Baja)
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Materia materiaToDelete = adapter.getMateriaAt(viewHolder.getAdapterPosition());
                materiaViewModel.delete(materiaToDelete);
                Toast.makeText(getContext(), "Materia eliminada", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recyclerViewMaterias);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
