package Programacion.gestion_academica.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import Programacion.gestion_academica.databinding.RecyclerviewItemEstudianteBinding;
import Programacion.gestion_academica.model.Estudiante;

public class EstudianteAdapter extends ListAdapter<Estudiante, EstudianteAdapter.EstudianteViewHolder> {

    private OnItemClickListener listener;

    public EstudianteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Estudiante> DIFF_CALLBACK = new DiffUtil.ItemCallback<Estudiante>() {
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
    };

    @NonNull
    @Override
    public EstudianteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewItemEstudianteBinding binding = RecyclerviewItemEstudianteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EstudianteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudianteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public Estudiante getEstudianteAt(int position) {
        return getItem(position);
    }

    public interface OnItemClickListener {
        void onItemClick(Estudiante estudiante);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class EstudianteViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerviewItemEstudianteBinding binding;

        public EstudianteViewHolder(RecyclerviewItemEstudianteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }

        public void bind(Estudiante estudiante) {
            String nombreCompleto = estudiante.getNombre() + " " + estudiante.getApellido();
            binding.textviewNombreCompleto.setText(nombreCompleto);
            binding.textviewEmail.setText(estudiante.getEmail());
        }
    }
}
