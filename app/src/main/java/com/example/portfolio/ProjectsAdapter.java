package com.example.portfolio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> {

    private ArrayList<ProjectCardView> projectCardViews = new ArrayList<>();
    private Context context;

    public ProjectsAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ProjectsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsAdapter.ViewHolder holder, int position) {
        holder.projectName.setText(projectCardViews.get(position).getProjectName());
        holder.buttonRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(projectCardViews.get(position).getButtonRedirect());
            }
        });
        holder.projectText.setText(projectCardViews.get(position).getProjectDescription());
        holder.projectCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, projectCardViews.get(position).getProjectName() + " Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // For image loading
        Glide.with(context).asBitmap().load(projectCardViews.get(position).getImageRes()).into(holder.projectImage);
    }

    @Override
    public int getItemCount() {
        return projectCardViews.size();
    }

    public void setProjectCardViews(ArrayList<ProjectCardView> projectCardViews) {
        this.projectCardViews = projectCardViews;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView projectName, projectText;
        private Button buttonRedirect;
        private CardView projectCard;
        private ImageView projectImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectName);
            buttonRedirect = itemView.findViewById(R.id.projectButton);
            projectText = itemView.findViewById(R.id.projectText);
            projectCard = itemView.findViewById(R.id.projectCard);
            projectImage = itemView.findViewById(R.id.projectImage);
        }

    }
}