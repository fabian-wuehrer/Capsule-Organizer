package de.fabianwuehrer.capsuleorganizer;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        public boolean areItemsTheSame(Capsule oldItem, Capsule newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Capsule oldItem, Capsule newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getCnt() == newItem.getCnt() &&
                    oldItem.getExp_date().equals(newItem.getExp_date());
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
        holder.textViewCount.setText(String.valueOf(currentCapsule.getCnt()));
        holder.textViewExpDate.setText(String.valueOf(getMonthString(currentCapsule.getExp_date().getMonth())) +
                " " + String.valueOf(currentCapsule.getExp_date().getYear()));
        /*
        if (currentCapsule.getCnt()<=0){
            holder.textViewName.setTextColor(Color.GRAY);
            holder.textViewName.setPaintFlags(holder.textViewName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textViewCount.setTextColor(Color.GRAY);
        }
        */
    }

    public Capsule getCapsulesAt(int position){
        return  getItem(position);
    }

    public String getMonthString(int month){
        switch(month){
            case 0: return "December";
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "" + month;
        }
    }

    class CapsuleHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        private TextView textViewName;
        private TextView textViewDescription;
        private TextView textViewCount;
        private TextView textViewExpDate;


        public CapsuleHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewCount = itemView.findViewById(R.id.text_view_count);
            textViewExpDate = itemView.findViewById(R.id.text_view_exp_date);
            itemView.setOnCreateContextMenuListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                    listener.onItemClick(getItem(position));
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(this.getAdapterPosition(), 01, 0, "Remove");
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Capsule capsule);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
