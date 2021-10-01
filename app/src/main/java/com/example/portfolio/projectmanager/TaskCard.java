package com.example.portfolio.projectmanager;

public class TaskCard {

    private String taskTitle;
    private String taskDescription;
    private String taskTime;
    private String taskWorkedTime;
    private boolean isDone;

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

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "TaskCard{" +
                "taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskTime='" + taskTime + '\'' +
                ", taskWorkedTime='" + taskWorkedTime + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
