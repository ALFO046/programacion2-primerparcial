package Programacion.gestion_academica.ui.estudiantes;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import Programacion.gestion_academica.databinding.FragmentAddEditEstudianteBinding;
import Programacion.gestion_academica.model.Estudiante;
import Programacion.gestion_academica.viewmodel.EstudianteViewModel;

public class AddEditEstudianteFragment extends Fragment {

    private FragmentAddEditEstudianteBinding binding;
    private EstudianteViewModel estudianteViewModel;
    private int estudianteId = -1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddEditEstudianteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        estudianteViewModel = new ViewModelProvider(this).get(EstudianteViewModel.class);

        if (getArguments() != null) {
            estudianteId = getArguments().getInt("estudianteId", -1);
            // Simulación de carga para edición, idealmente se observaría un LiveData
            if (estudianteId != -1) {
                binding.etNombre.setText(getArguments().getString("nombre"));
                binding.etApellido.setText(getArguments().getString("apellido"));
                binding.etEmail.setText(getArguments().getString("email"));
            }
        }

        binding.btnGuardar.setOnClickListener(v -> saveEstudiante());
    }

    private void saveEstudiante() {
        String nombre = binding.etNombre.getText().toString().trim();
        String apellido = binding.etApellido.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellido) || TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Complete todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        Estudiante estudiante = new Estudiante(nombre, apellido, email);

        if (estudianteId == -1) {
            estudianteViewModel.insert(estudiante);
            Toast.makeText(getContext(), "Estudiante registrado.", Toast.LENGTH_SHORT).show();
        } else {
            estudiante.setIdEstudiante(estudianteId);
            estudianteViewModel.update(estudiante);
            Toast.makeText(getContext(), "Estudiante actualizado.", Toast.LENGTH_SHORT).show();
        }

        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
