package com.example.waterreminder;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class DrinkAdapter extends FirestoreRecyclerAdapter<Drink, DrinkAdapter.DrinkHolder> {
    private onItemClickListener listener;

    public DrinkAdapter(@NonNull FirestoreRecyclerOptions options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull DrinkHolder holder, int position, @NonNull Drink model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewDescription.setText(model.getDescription());
        holder.textViewQuantity.setText(String.valueOf(model.getQuantity()));

    }

    @NonNull
    @Override
    public DrinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_item, parent, false);
        return new DrinkHolder(v);
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }


    class DrinkHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewQuantity;

        public DrinkHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_type);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewQuantity = itemView.findViewById(R.id.text_view_quantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION && listener != null)
                    {
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
