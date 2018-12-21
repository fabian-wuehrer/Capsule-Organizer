package de.fabianwuehrer.capsuleorganizer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CapsuleAdapter extends ListAdapter<Capsule, CapsuleAdapter.CapsuleHolder> {

    public CapsuleAdapter() {
        super(DIFF_CALLBACK);
    }
    private OnItemClickListener listener;

    private static final DiffUtil.ItemCallback<Capsule> DIFF_CALLBACK = new DiffUtil.ItemCallback<Capsule>() {
        @Override
        public boolean areItemsTheSame(@NonNull Capsule oldItem, @NonNull Capsule newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Capsule oldItem, @NonNull Capsule newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getCount() == newItem.getCount();
        }
    };

    @NonNull
    @Override
    public CapsuleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.capsule_item, parent, false);
        return new CapsuleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CapsuleHolder holder, int position) {
        Capsule currentCapsule = getItem(position);
        holder.textViewName.setText(currentCapsule.getName());
        holder.textViewDescription.setText(currentCapsule.getDescription());
        holder.textViewCount.setText(String.valueOf(currentCapsule.getCount()));
    }

    public Capsule getCapsulesAt(int position){
        return  getItem(position);
    }

    class CapsuleHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewCount;


        public CapsuleHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewCount = itemView.findViewById(R.id.text_view_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Capsule capsule);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
