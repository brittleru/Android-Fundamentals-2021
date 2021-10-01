package com.example.portfolio.complexstudents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.portfolio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.Objects;

public class Students extends AppCompatActivity {

    private final LinkedList<String> nameList = new LinkedList<>();
    private RecyclerView recyclerView;
    private NameListAdapter adapter;
    private FloatingActionButton fab;
    private FloatingActionButton deleteFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        for (int i = 0; i < 20; i++) {
            nameList.addLast("FirstName " + i + "\nLastName " + i);
        }

        recyclerView = findViewById(R.id.recyclerViewId);

        // Create adapter and give it data
        adapter = new NameListAdapter(this, nameList);

        // Connect adapter and RecyclerView
        recyclerView.setAdapter(adapter);

        // Set a default layout manager for adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = (FloatingActionButton) findViewById(R.id.fab);
        deleteFab = (FloatingActionButton) findViewById(R.id.deleteFab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nameListSize = nameList.size();
                nameList.addLast("+ FirstName " + nameListSize + "\n+ LastName " + nameListSize);
                Objects.requireNonNull(recyclerView.getAdapter()).notifyItemInserted(nameListSize);
                recyclerView.smoothScrollToPosition(nameListSize);
            }
        });
        deleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lastItem = nameList.size() - 1;
                nameList.remove(lastItem);
                recyclerView.getAdapter().notifyItemRemoved(lastItem);
                recyclerView.getAdapter().notifyItemRangeChanged(lastItem, nameList.size());

                recyclerView.smoothScrollToPosition(lastItem);
            }
        });
    }

}