package com.example.portfolio;

public class ProjectCard {

    private String projectTitle;
    private String projectDescription;
    private String projectTime;

    public ProjectCard(String projectTitle, String projectDescription, String projectTime) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.projectTime = projectTime;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(String projectTime) {
        this.projectTime = projectTime;
    }

    @Override
    public String toString() {
        return "ProjectCardView{" +
                "projectTitle='" + projectTitle + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectTime='" + projectTime + '\'' +
                '}';
    }
}
