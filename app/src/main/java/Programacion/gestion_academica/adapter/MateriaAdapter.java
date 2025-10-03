package Programacion.gestion_academica.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import Programacion.gestion_academica.databinding.RecyclerviewItemMateriaBinding;
import Programacion.gestion_academica.model.Materia;

public class MateriaAdapter extends ListAdapter<Materia, MateriaAdapter.MateriaViewHolder> {

    private OnItemClickListener listener;

    public MateriaAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Materia> DIFF_CALLBACK = new DiffUtil.ItemCallback<Materia>() {
        @Override
        public boolean areItemsTheSame(@NonNull Materia oldItem, @NonNull Materia newItem) {
            return oldItem.getIdMateria() == newItem.getIdMateria();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Materia oldItem, @NonNull Materia newItem) {
            return oldItem.getNombreMateria().equals(newItem.getNombreMateria()) &&
                    oldItem.getDescripcion().equals(newItem.getDescripcion());
        }
    };

    @NonNull
    @Override
    public MateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewItemMateriaBinding binding = RecyclerviewItemMateriaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MateriaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public Materia getMateriaAt(int position) {
        return getItem(position);
    }

    public interface OnItemClickListener {
        void onItemClick(Materia materia);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class MateriaViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerviewItemMateriaBinding binding;

        public MateriaViewHolder(RecyclerviewItemMateriaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }

        public void bind(Materia materia) {
            binding.textviewNombreMateria.setText(materia.getNombreMateria());
            binding.textviewDescripcionMateria.setText(materia.getDescripcion());
        }
    }
}
