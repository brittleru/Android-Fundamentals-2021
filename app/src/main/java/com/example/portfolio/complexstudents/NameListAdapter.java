package com.example.portfolio.complexstudents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portfolio.R;

import java.util.LinkedList;

public class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.NameViewHolder> {


    private final LinkedList<String> nameList;
    private LayoutInflater inflater;

    public NameListAdapter(Context context, LinkedList<String> nameList) {
        this.inflater = LayoutInflater.from(context);
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public NameListAdapter.NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.namelist_item, parent, false);
        return new NameViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NameListAdapter.NameViewHolder holder, int position) {
        String current = nameList.get(position);
        holder.nameItemView.setText(current);
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }


    public class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView nameItemView;
        final NameListAdapter nameAdapter;

        public NameViewHolder(View itemView, NameListAdapter adapter) {
            super(itemView);
            this.nameItemView = itemView.findViewById(R.id.name);
            this.nameAdapter = adapter;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            // Store the position of the clicked item
            int position = getLayoutPosition();

            // Access the element of that position
            String element = nameList.get(position);

            // Changed the element name
            nameList.set(position, "Clicked! " + element);

            // Update the RecyclerView
            nameAdapter.notifyDataSetChanged();
        }
    }
}
