package com.example.mymidexam.modal;

import com.example.mymidexam.enume.EQualification;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;
    private String firstname;
    private String lastname;
    private EQualification qualification;
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(int teacherId, String firstname, String lastname, EQualification qualification, List<Course> courses) {
        this.teacherId = teacherId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.qualification = qualification;
        this.courses = courses;
    }

    public Teacher(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public EQualification getQualification() {
        return qualification;
    }

    public void setQualification(EQualification qualification) {
        this.qualification = qualification;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

