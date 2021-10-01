package com.example.portfolio.projectmanager;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TasksDao {

    @Insert(onConflict = REPLACE)
    void insert(TasksData tasksData);

    @Delete
    void delete(TasksData tasksData);

    @Query("UPDATE table_task SET title = :newTitle, description = :newDescription, time = :newHour, worked = :newWorked WHERE taskID = :newID")
    void update(int newID, String newTitle, String newDescription, String newHour, String newWorked);

    @Query("SELECT * FROM table_task")
    List<TasksData> getAll();



}
