package com.example.gestionacademica.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.gestionacademica.databinding.FragmentInscripcionesBinding; // Importa el binding

public class InscripcionesFragment extends Fragment {

    private FragmentInscripcionesBinding binding; // Variable para ViewBinding

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout usando ViewBinding (Requisito 4)
        binding = FragmentInscripcionesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Limpiar el binding
    }
}