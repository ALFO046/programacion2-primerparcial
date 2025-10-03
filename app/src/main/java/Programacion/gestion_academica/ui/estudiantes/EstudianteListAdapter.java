package Programacion.gestion_academica.ui.estudiantes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import Programacion.gestion_academica.databinding.RecyclerviewItemEstudianteBinding;
import Programacion.gestion_academica.model.Estudiante;

public class EstudianteListAdapter extends ListAdapter<Estudiante, EstudianteListAdapter.EstudianteViewHolder> {

    public EstudianteListAdapter(@NonNull DiffUtil.ItemCallback<Estudiante> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public EstudianteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewItemEstudianteBinding binding = RecyclerviewItemEstudianteBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new EstudianteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudianteViewHolder holder, int position) {
        Estudiante current = getItem(position);
        holder.bind(current);
    }

    static class EstudianteViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerviewItemEstudianteBinding binding;

        EstudianteViewHolder(RecyclerviewItemEstudianteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Estudiante estudiante) {
            String nombreCompleto = estudiante.getNombre() + " " + estudiante.getApellido();
            binding.textviewNombreCompleto.setText(nombreCompleto);
            binding.textviewEmail.setText(estudiante.getEmail());
        }
    }

    public static class EstudianteDiff extends DiffUtil.ItemCallback<Estudiante> {

        @Override
        public boolean areItemsTheSame(@NonNull Estudiante oldItem, @NonNull Estudiante newItem) {
            return oldItem.getIdEstudiante() == newItem.getIdEstudiante();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Estudiante oldItem, @NonNull Estudiante newItem) {
            return oldItem.getNombre().equals(newItem.getNombre()) &&
                   oldItem.getApellido().equals(newItem.getApellido()) &&
                   oldItem.getEmail().equals(newItem.getEmail());
        }
    }
}
