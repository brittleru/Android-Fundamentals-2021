package com.example.portfolio.projectmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.portfolio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TaskManager extends AppCompatActivity {

    private TextView projectDescription, projectTime, tasksTitle;
    private FloatingActionButton fabTasks;
    private RecyclerView recyclerView;

    private List<TasksData> dataArrayList = new ArrayList<>();
    private ProjectsDB database;
    private TasksRecViewAdapter adapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);
        tasksTitle = (TextView) findViewById(R.id.tasksTitle);
        projectDescription = (TextView) findViewById(R.id.tasksProjectDescription);
        projectTime = (TextView) findViewById(R.id.tasksProjectHours);
        fabTasks = (FloatingActionButton) findViewById(R.id.fabTasks);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTask);

        // Intent from the clicked activity and set text to it's data
        Intent in = getIntent();
        String intentName = in.getStringExtra("projectTitle");
        String intentDescription = in.getStringExtra("projectDescription");
        String intentTime = in.getStringExtra("projectTime");
        int intentID = in.getIntExtra("projectID", 0);

        tasksTitle.append(intentName);
        projectDescription.setText(intentDescription);
        projectTime.setText(intentTime + " hours");


        database = ProjectsDB.getInstance(this);
        dataArrayList = database.tasksDao().getAll();

        List<TasksData> tempList = new ArrayList<>();


        for (TasksData tasksData : dataArrayList) {
            if (intentID == tasksData.getProjectTaskID()) {
                tempList.add(tasksData);
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new TasksRecViewAdapter(TaskManager.this, tempList);
        adapter.setProjectCardViews(tempList);
        recyclerView.setAdapter(adapter);



        fabTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dialog popup to add elements in the Tasks database
                Dialog dialog = new Dialog(TaskManager.this);
                dialog.setContentView(R.layout.dialog_add_task);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, height);
                dialog.show();

                EditText addTaskTitle = dialog.findViewById(R.id.addTaskTitle);
                EditText addTaskDescription = dialog.findViewById(R.id.addTaskDescription);
                EditText addTaskTime = dialog.findViewById(R.id.addTaskTime);
                EditText addTaskWorkedTime = dialog.findViewById(R.id.addTaskWorkedTime);
                Button addTaskButton = dialog.findViewById(R.id.addTaskButton);
                Button cancelTaskButton = dialog.findViewById(R.id.cancelTaskButton);

                addTaskButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String newAddTaskTitle = addTaskTitle.getText().toString().trim();
                        String newAddTaskDescription = addTaskDescription.getText().toString().trim();
                        String newAddTaskTime = addTaskTime.getText().toString().trim();
                        String newAddTaskWorkedTime = addTaskWorkedTime.getText().toString().trim();

                        addTaskTitle.setText(newAddTaskTitle);
                        addTaskDescription.setText(newAddTaskDescription);
                        addTaskTime.setText(newAddTaskTime);
                        addTaskWorkedTime.setText(newAddTaskWorkedTime);

                        if (!newAddTaskTitle.equals("") && !newAddTaskDescription.equals("") && !newAddTaskTime.equals("") && !newAddTaskWorkedTime.equals("")) {
                            TasksData tasksData = new TasksData();

                            tasksData.setTaskTitle(newAddTaskTitle);
                            tasksData.setTaskDescription(newAddTaskDescription);
                            tasksData.setTaskTime(newAddTaskTime);
                            tasksData.setTaskWorkedTime(newAddTaskWorkedTime);
                            tasksData.setProjectTaskID(intentID);

                            database.tasksDao().insert(tasksData);

                            dataArrayList.clear();
                            dataArrayList.addAll(database.tasksDao().getAll());
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        dialog.dismiss();

                    }
                });
                cancelTaskButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
            }
        });

    }
}