package com.example.portfolio.projectmanager;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_task")
public class TasksData implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int taskID;

    @ColumnInfo(name = "title")
    private String taskTitle;

    @ColumnInfo(name = "description")
    private String taskDescription;

    @ColumnInfo(name = "time")
    private String taskTime;

    @ColumnInfo(name = "worked")
    private String taskWorkedTime;

    @ColumnInfo(name = "projectTaskID")
    private int projectTaskID;

    @ColumnInfo(name = "done")
    private boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskWorkedTime() {
        return taskWorkedTime;
    }

    public void setTaskWorkedTime(String taskWorkedTime) {
        this.taskWorkedTime = taskWorkedTime;
    }

    public int getProjectTaskID() {
        return projectTaskID;
    }

    public void setProjectTaskID(int projectTaskID) {
        this.projectTaskID = projectTaskID;
    }
}
