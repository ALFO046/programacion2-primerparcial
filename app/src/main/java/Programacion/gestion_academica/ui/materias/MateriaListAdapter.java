package Programacion.gestion_academica.ui.materias;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import Programacion.gestion_academica.databinding.RecyclerviewItemMateriaBinding;
import Programacion.gestion_academica.model.Materia;

public class MateriaListAdapter extends ListAdapter<Materia, MateriaListAdapter.MateriaViewHolder> {

    public MateriaListAdapter(@NonNull DiffUtil.ItemCallback<Materia> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewItemMateriaBinding binding = RecyclerviewItemMateriaBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new MateriaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaViewHolder holder, int position) {
        Materia current = getItem(position);
        holder.bind(current);
    }

    static class MateriaViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerviewItemMateriaBinding binding;

        MateriaViewHolder(RecyclerviewItemMateriaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Materia materia) {
            binding.textviewNombreMateria.setText(materia.getNombreMateria());
            binding.textviewDescripcionMateria.setText(materia.getDescripcion());
        }
    }

    public static class MateriaDiff extends DiffUtil.ItemCallback<Materia> {

        @Override
        public boolean areItemsTheSame(@NonNull Materia oldItem, @NonNull Materia newItem) {
            return oldItem.getIdMateria() == newItem.getIdMateria();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Materia oldItem, @NonNull Materia newItem) {
            return oldItem.getNombreMateria().equals(newItem.getNombreMateria()) &&
                   oldItem.getDescripcion().equals(newItem.getDescripcion());
        }
    }
}
