package com.example.portfolio.projectmanager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProjectsData.class, TasksData.class}, version = 3, exportSchema = false)
public abstract class ProjectsDB extends RoomDatabase {

    // Database Instance
    private static ProjectsDB database;

    private static final String DATABASE_NAME = "project";

    public synchronized static ProjectsDB getInstance(Context context) {

        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    ProjectsDB.class, DATABASE_NAME).allowMainThreadQueries().
                    fallbackToDestructiveMigration().build();
        }

        return database;

    }
    public abstract ProjectsDao projectsDao();
    public abstract TasksDao tasksDao();


}
