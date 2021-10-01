package com.example.portfolio.projectmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.portfolio.MainActivity;
import com.example.portfolio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProjectManager extends AppCompatActivity {

    private List<ProjectsData> dataArrayList = new ArrayList<>();
    private ProjectsDB database;
    private ProjectsRecViewAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_manager);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProject);
        fab = (FloatingActionButton) findViewById(R.id.fab);


        database = ProjectsDB.getInstance(this);
        dataArrayList = database.projectsDao().getAll();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ProjectsRecViewAdapter(ProjectManager.this, dataArrayList);
        adapter.setProjectCardViews(dataArrayList);
        recyclerView.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Dialog popup to add elements in the Projects database
                Dialog dialog = new Dialog(ProjectManager.this);
                dialog.setContentView(R.layout.dialog_add_project);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, height);
                dialog.show();

                EditText addProjectTitle = dialog.findViewById(R.id.addProjectTitle);
                EditText addProjectDescription = dialog.findViewById(R.id.addProjectDescription);
                EditText addProjectTime = dialog.findViewById(R.id.addProjectTime);
                Button addProjectButton = dialog.findViewById(R.id.addProjectButton);
                Button cancelProjectButton = dialog.findViewById(R.id.cancelProjectButton);

                addProjectButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newAddProjectTitle = addProjectTitle.getText().toString().trim();
                        String newAddProjectDescription = addProjectDescription.getText().toString().trim();
                        String newAddProjectTime = addProjectTime.getText().toString().trim();

                        addProjectTitle.setText(newAddProjectTitle);
                        addProjectDescription.setText(newAddProjectDescription);
                        addProjectTime.setText(newAddProjectTime);

                        if (!newAddProjectTitle.equals("") && !newAddProjectDescription.equals("") && !newAddProjectTime.equals("")) {
                            ProjectsData projectsData = new ProjectsData();

                            projectsData.setProjectTitle(newAddProjectTitle);
                            projectsData.setProjectDescription(newAddProjectDescription);
                            projectsData.setProjectTime(newAddProjectTime);

                            database.projectsDao().insert(projectsData);

                            dataArrayList.clear();
                            dataArrayList.addAll(database.projectsDao().getAll());
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }


                    }
                });

                cancelProjectButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });

    }
}