package com.example.mymidexam.modal;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int studentId;
    private String firstname;
    private String lastname;
    private Date dob;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    // Constructors, getters, setters


    public Student() {
    }

    public Student(int id, int studentId, String firstname, String lastname, Date dob, List<Course> courses) {
        this.id = id;
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.courses = courses;
    }

    public Student(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
