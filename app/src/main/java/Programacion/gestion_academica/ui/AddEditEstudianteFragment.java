package Programacion.gestion_academica.ui;

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
        // Usamos el ViewBinding para inflar el layout que creamos antes
        binding = FragmentAddEditEstudianteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        estudianteViewModel = new ViewModelProvider(this).get(EstudianteViewModel.class);

        // Comprobamos si recibimos argumentos (para saber si es edición)
        if (getArguments() != null) {
            estudianteId = getArguments().getInt("estudianteId", -1);
            if (estudianteId != -1) {
                // Modo Edición: cargamos los datos en los campos
                binding.etNombre.setText(getArguments().getString("nombre"));
                binding.etApellido.setText(getArguments().getString("apellido"));
                binding.etEmail.setText(getArguments().getString("email"));
                binding.btnGuardar.setText("Actualizar");
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
            // Modo Creación: insertamos un nuevo estudiante
            estudianteViewModel.insert(estudiante);
            Toast.makeText(getContext(), "Estudiante registrado.", Toast.LENGTH_SHORT).show();
        } else {
            // Modo Edición: actualizamos el estudiante existente
            estudiante.setIdEstudiante(estudianteId);
            estudianteViewModel.update(estudiante);
            Toast.makeText(getContext(), "Estudiante actualizado.", Toast.LENGTH_SHORT).show();
        }

        // Después de guardar, regresamos a la pantalla anterior (la lista)
        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
