package com.example.portfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class StoreDataProjectManager extends AppCompatActivity {

    EditText addTitle, addDescription, addTime;
    Button addButton;

    List<ProjectsData> dataArrayList = new ArrayList<>();
    ProjectsDB database;
    ProjectsRecViewAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_data_project_manager);
        addTitle = (EditText) findViewById(R.id.addTitle);
        addDescription = (EditText) findViewById(R.id.addDescription);
        addTime = (EditText) findViewById(R.id.addTime);
        addButton = (Button) findViewById(R.id.addButton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProject);


        database = ProjectsDB.getInstance(this);
        dataArrayList = database.projectsDao().getAll();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ProjectsRecViewAdapter(StoreDataProjectManager.this, dataArrayList);
        adapter.setProjectCardViews(dataArrayList);
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAddTitle = addTitle.getText().toString().trim();
                String newAddDescription = addDescription.getText().toString().trim();
                String newAddTime = addTime.getText().toString().trim();

                if (!newAddTitle.equals("") && !newAddDescription.equals("") && !newAddTime.equals("")) {
                    ProjectsData projectsData = new ProjectsData();

                    projectsData.setProjectTitle(newAddTitle);
                    projectsData.setProjectDescription(newAddDescription);
                    projectsData.setProjectTime(newAddTime);

                    database.projectsDao().insert(projectsData);

                    addTitle.setText("");
                    addDescription.setText("");
                    addTime.setText("");

                    dataArrayList.clear();
                    dataArrayList.addAll(database.projectsDao().getAll());
                    adapter.notifyDataSetChanged();

                }
            }
        });


    }
}