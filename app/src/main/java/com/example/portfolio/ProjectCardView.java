package com.example.portfolio;

import android.content.Intent;

public class ProjectCardView {
    private String projectName;
    private String imageRes;
    private String projectDescription;
    private Intent buttonRedirect;

    public ProjectCardView(String projectName, String imageRes, String projectDescription, Intent buttonRedirect) {
        this.projectName = projectName;
        this.imageRes = imageRes;
        this.projectDescription = projectDescription;
        this.buttonRedirect = buttonRedirect;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getImageRes() {
        return imageRes;
    }

    public void setImageRes(String imageRes) {
        this.imageRes = imageRes;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Intent getButtonRedirect() {
        return buttonRedirect;
    }

    public void setButtonRedirect(Intent buttonRedirect) {
        this.buttonRedirect = buttonRedirect;
    }

    @Override
    public String toString() {
        return "ProjectCardView{" +
                "projectName='" + projectName + '\'' +
                ", imageRes='" + imageRes + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", buttonRedirect=" + buttonRedirect +
                '}';
    }
}
