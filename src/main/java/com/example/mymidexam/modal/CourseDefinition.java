package com.example.mymidexam.modal;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CourseDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseDefinitionId;
    private String courseDefinitionName;
    private String courseDefinitionDescription;
    @OneToMany(mappedBy = "courseDefinition")
    private List<Course> courses;

    public CourseDefinition() {
    }

    public CourseDefinition(int courseDefinitionId, String courseDefinitionName, String courseDefinitionDescription, List<Course> courses) {
        this.courseDefinitionId = courseDefinitionId;
        this.courseDefinitionName = courseDefinitionName;
        this.courseDefinitionDescription = courseDefinitionDescription;
        this.courses = courses;
    }

    public CourseDefinition(int courseDefinitionId) {
        this.courseDefinitionId = courseDefinitionId;
    }

    public int getCourseDefinitionId() {
        return courseDefinitionId;
    }

    public void setCourseDefinitionId(int courseDefinitionId) {
        this.courseDefinitionId = courseDefinitionId;
    }

    public String getCourseDefinitionName() {
        return courseDefinitionName;
    }

    public void setCourseDefinitionName(String courseDefinitionName) {
        this.courseDefinitionName = courseDefinitionName;
    }

    public String getCourseDefinitionDescription() {
        return courseDefinitionDescription;
    }

    public void setCourseDefinitionDescription(String courseDefinitionDescription) {
        this.courseDefinitionDescription = courseDefinitionDescription;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
