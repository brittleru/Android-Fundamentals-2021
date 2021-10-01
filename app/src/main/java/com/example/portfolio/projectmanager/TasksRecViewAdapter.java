package com.example.portfolio.projectmanager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portfolio.R;

import java.util.ArrayList;
import java.util.List;

public class TasksRecViewAdapter extends RecyclerView.Adapter<TasksRecViewAdapter.ViewHolder> {
    private List<TasksData> tasksCardViews = new ArrayList<>();
    private List<TasksData> dataArrayList;
    private Context context;
    private ProjectsDB database;


    public TasksRecViewAdapter(Context context, List<TasksData> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TasksData data = dataArrayList.get(position);
        database = ProjectsDB.getInstance(context);


        holder.taskName.setText(tasksCardViews.get(position).getTaskTitle());
        holder.taskDescription.setText(tasksCardViews.get(position).getTaskDescription());
        holder.taskTime.setText(tasksCardViews.get(position).getTaskTime() + " mins");
        holder.taskWorkedTime.setText(tasksCardViews.get(position).getTaskWorkedTime() + " mins");

        holder.parentTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doneSwitcher(tasksCardViews, position);
                if (tasksCardViews.get(position).isDone()) {
                    holder.inProgressText.setText("Done");
                    holder.inProgressText.setTextColor(context.getResources().getColor(R.color.green));
                }
                else {
                    holder.inProgressText.setText("In Progress");
                    holder.inProgressText.setTextColor(context.getResources().getColor(R.color.purple_200));
                }
//                Toast.makeText(context, tasksCardViews.get(position).getProjectTaskID() + " Clicked", Toast.LENGTH_SHORT).show();

            }
        });


        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TasksData data1 = dataArrayList.get(holder.getAdapterPosition());
                int newID = data1.getTaskID();
                String newTitle = data1.getTaskTitle();
                String newDescription = data1.getTaskDescription();
                String newTime = data1.getTaskTime();
                String newWorkedTime = data1.getTaskWorkedTime();


                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update_task);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, height);
                dialog.show();

                EditText updatedTitle = dialog.findViewById(R.id.updateTaskTitle);
                EditText updatedDescription = dialog.findViewById(R.id.updateTaskDescription);
                EditText updatedTime = dialog.findViewById(R.id.updateTaskTime);
                EditText updatedWorkedTime = dialog.findViewById(R.id.updateTaskWorkedTime);
                Button updateButton = dialog.findViewById(R.id.updateTaskButton);
                Button cancelButton = dialog.findViewById(R.id.updateCancelTaskButton);

                updatedTitle.setText(newTitle);
                updatedDescription.setText(newDescription);
                updatedTime.setText(newTime);
                updatedWorkedTime.setText(newWorkedTime);

                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String updatedNewTitle = updatedTitle.getText().toString().trim();
                        String updatedNewDescription = updatedDescription.getText().toString().trim();
                        String updatedNewTime = updatedTime.getText().toString().trim();
                        String updatedNewWorkedTime = updatedWorkedTime.getText().toString().trim();

                        database.tasksDao().update(newID, updatedNewTitle, updatedNewDescription, updatedNewTime, updatedNewWorkedTime);

                        dataArrayList.clear();
                        dataArrayList.addAll(database.tasksDao().getAll());

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
                TasksData tasksData = dataArrayList.get(holder.getAdapterPosition());

                AlertDialog diaBox = askOption(tasksData, holder);
                diaBox.show();

            }
        });

    }

    private AlertDialog askOption(TasksData tasksData, @NonNull ViewHolder holder) {
        AlertDialog quittingDialogBox = new AlertDialog.Builder(context)
                // set message, title and icon
                .setTitle("Delete " + tasksData.getTaskTitle())
                .setMessage("Do you want to delete " + tasksData.getTaskTitle() + " forever?")
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

                        database.tasksDao().delete(tasksData);
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

    private void doneSwitcher (List<TasksData> cardView, int position) {
        if (cardView.get(position).isDone()) {
            cardView.get(position).setDone(false);
        }
        else {
            cardView.get(position).setDone(true);
        }
    }

    @Override
    public int getItemCount() {
        return tasksCardViews.size();
    }

    public void setProjectCardViews(List<TasksData> tasksCardViews) {
        this.tasksCardViews = tasksCardViews;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView taskName, taskDescription, taskTime, taskWorkedTime, inProgressText;
        private CardView parentTask;
        private ImageView editButton, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            taskDescription = itemView.findViewById(R.id.taskDescription);
            taskTime = itemView.findViewById(R.id.taskHours);
            taskWorkedTime = itemView.findViewById(R.id.taskWorkedTime);
            parentTask = itemView.findViewById(R.id.cardViewTask);
            editButton = itemView.findViewById(R.id.taskEditButton);
            deleteButton = itemView.findViewById(R.id.taskDeleteButton);
            inProgressText = itemView.findViewById(R.id.inProgressText);
            context = itemView.getContext();
        }
    }
}
