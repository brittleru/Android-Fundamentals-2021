package com.example.portfolio.projectmanager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portfolio.R;

import java.util.ArrayList;
import java.util.List;

public class ProjectsRecViewAdapter extends RecyclerView.Adapter<ProjectsRecViewAdapter.ViewHolder> {

    private List<ProjectsData> projectCardViews = new ArrayList<>();
    private List<ProjectsData> dataArrayList;
    private Context context;
    private ProjectsDB database;

    public ProjectsRecViewAdapter (Context context, List<ProjectsData> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProjectsData data = dataArrayList.get(position);
        database = ProjectsDB.getInstance(context);


        holder.projectName.setText(projectCardViews.get(position).getProjectTitle());
        holder.projectDescription.setText(projectCardViews.get(position).getProjectDescription());
        holder.projectTime.setText(projectCardViews.get(position).getProjectTime() + " hours");
        holder.parentProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, projectCardViews.get(position).getProjectTitle() + " Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.buttonTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Adding a new activity for the clicked stuff

                Intent intent;

                intent = new Intent(context, TaskManager.class);
                intent.putExtra("projectTitle", projectCardViews.get(position).getProjectTitle());
                intent.putExtra("projectDescription", projectCardViews.get(position).getProjectDescription());
                intent.putExtra("projectTime", projectCardViews.get(position).getProjectTime());
                intent.putExtra("projectID", projectCardViews.get(position).getID());

                context.startActivity(intent);
//                Toast.makeText(context, projectCardViews.get(position).getProjectTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectsData data1 = dataArrayList.get(holder.getAdapterPosition());
                int newID = data1.getID();
                String newTitle = data1.getProjectTitle();
                String newDescription = data1.getProjectDescription();
                String newTime = data1.getProjectTime();


                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update_project);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, height);
                dialog.show();

                EditText updatedTitle = dialog.findViewById(R.id.updateProjectTitle);
                EditText updatedDescription = dialog.findViewById(R.id.updateProjectDescription);
                EditText updatedTime = dialog.findViewById(R.id.updateProjectTime);
                Button updateButton = dialog.findViewById(R.id.updateButton);
                Button cancelButton = dialog.findViewById(R.id.cancelUpdateButton);

                updatedTitle.setText(newTitle);
                updatedDescription.setText(newDescription);
                updatedTime.setText(newTime);

                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String updatedNewTitle = updatedTitle.getText().toString().trim();
                        String updatedNewDescription = updatedDescription.getText().toString().trim();
                        String updatedNewTime = updatedTime.getText().toString().trim();

                        database.projectsDao().update(newID, updatedNewTitle, updatedNewDescription, updatedNewTime);

                        dataArrayList.clear();
                        dataArrayList.addAll(database.projectsDao().getAll());
                        notifyDataSetChanged();
                    }
                });
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ProjectsData projectsData = dataArrayList.get(holder.getAdapterPosition());

                AlertDialog diaBox = askOption(projectsData, holder);
                diaBox.show();




            }
        });
    }
    private AlertDialog askOption(ProjectsData projectsData, @NonNull ViewHolder holder){
        AlertDialog quittingDialogBox = new AlertDialog.Builder(context)
                // set message, title and icon
                .setTitle("Delete " + projectsData.getProjectTitle())
                .setMessage("Do you want to delete " + projectsData.getProjectTitle() + " forever?")
                .setIcon(R.drawable.ic_delete)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        database.projectsDao().delete(projectsData);
                        int position = holder.getAdapterPosition();
                        dataArrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, dataArrayList.size());

                        dialog.dismiss();
                    }
                })

                .create();

        return quittingDialogBox;
    }


    @Override
    public int getItemCount() {
        return projectCardViews.size();
    }

    public void setProjectCardViews(List<ProjectsData> projectCardViews) {
        this.projectCardViews = projectCardViews;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView projectName, projectDescription, projectTime;
        private CardView parentProject;
        private ImageView editButton, deleteButton;
        private Button buttonTasks;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectName);
            projectDescription = itemView.findViewById(R.id.projectDescription);
            projectTime = itemView.findViewById(R.id.projectHours);
            parentProject = itemView.findViewById(R.id.cardViewProject);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            buttonTasks = itemView.findViewById(R.id.buttonTasks);
            context = itemView.getContext();
        }
    }
}
