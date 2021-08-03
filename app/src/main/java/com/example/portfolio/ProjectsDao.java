package com.example.portfolio;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProjectsDao {

    @Insert(onConflict = REPLACE)
    void insert(ProjectsData projectsData);

    @Delete
    void delete(ProjectsData projectsData);

    @Query("UPDATE table_project SET title = :newTitle, description = :newDescription, time = :newHour WHERE ID = :newID")
    void update(int newID, String newTitle, String newDescription, String newHour);

    @Query("SELECT * FROM table_project")
    List<ProjectsData> getAll();
}
