package com.example.portfolio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> implements Filterable {

    private ArrayList<ProjectCardView> projectCardViews;
    private ArrayList<ProjectCardView> projectCardViewsAllItems;

    private Context context;

    public ProjectsAdapter(Context context, ArrayList<ProjectCardView> projectCardViews) {
        this.context = context;
        this.projectCardViews = projectCardViews;
        projectCardViewsAllItems = new ArrayList<>(this.projectCardViews);
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
//        Toast.makeText(context, "getMinLines(): " + holder.projectText.getMinLines() + " getMaxLines():" + holder.projectText.getMaxLines(), Toast.LENGTH_SHORT).show();

        holder.projectText.post(new Runnable() {
            @Override
            public void run() {
                int lineCounter = holder.projectText.getLineCount();

                if (lineCounter <= 5) {
                    holder.showText.setVisibility(View.GONE);
                    holder.hideText.setVisibility(View.GONE);

                } else {
                    holder.showText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.showText.setVisibility(View.INVISIBLE);
                            holder.hideText.setVisibility(View.VISIBLE);
                            holder.projectText.setMaxLines(Integer.MAX_VALUE);
                        }
                    });

                    holder.hideText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.hideText.setVisibility(View.INVISIBLE);
                            holder.showText.setVisibility(View.VISIBLE);
                            holder.projectText.setMaxLines(5);
                        }
                    });
                }
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

    @Override
    public Filter getFilter() {
        return projectsFilter;
    }

    private Filter projectsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ProjectCardView> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(projectCardViewsAllItems);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ProjectCardView item : projectCardViewsAllItems) {
                    if (item.getProjectName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            projectCardViews.clear();
            projectCardViews.addAll((ArrayList<ProjectCardView>) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView projectName, projectText;
        private Button buttonRedirect;
        private CardView projectCard;
        private ImageView projectImage, showText, hideText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectName);
            buttonRedirect = itemView.findViewById(R.id.projectButton);
            projectText = itemView.findViewById(R.id.projectText);
            projectCard = itemView.findViewById(R.id.projectCard);
            projectImage = itemView.findViewById(R.id.projectImage);
            showText = itemView.findViewById(R.id.showText);
            hideText = itemView.findViewById(R.id.hideText);
            context = itemView.getContext();
        }

    }
}