package Programacion.gestion_academica.ui.materias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import Programacion.gestion_academica.adapter.MateriaAdapter;
import Programacion.gestion_academica.databinding.FragmentMateriasBinding;
import Programacion.gestion_academica.viewmodel.MateriaViewModel;

public class MateriasFragment extends Fragment {

    private FragmentMateriasBinding binding;
    private MateriaViewModel materiaViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMateriasBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        materiaViewModel = new ViewModelProvider(this).get(MateriaViewModel.class);

        final MateriaAdapter adapter = new MateriaAdapter();
        binding.recyclerviewMaterias.setAdapter(adapter);
        binding.recyclerviewMaterias.setLayoutManager(new LinearLayoutManager(getContext()));

        materiaViewModel.getAllMaterias().observe(getViewLifecycleOwner(), materias -> {
            adapter.submitList(materias);
        });

        binding.fabAddMateria.setOnClickListener(v -> {
            // Aquí irá la lógica para añadir una nueva materia
            Toast.makeText(getContext(), "Añadir nueva materia...", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
