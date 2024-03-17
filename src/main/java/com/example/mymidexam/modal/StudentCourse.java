package com.example.mymidexam.modal;

import jakarta.persistence.*;

@Entity
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentCourseId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Constructors, getters, setters


    public StudentCourse() {
    }

    public StudentCourse(int studentCourseId, Student student, Course course) {
        this.studentCourseId = studentCourseId;
        this.student = student;
        this.course = course;
    }

    public StudentCourse(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public int getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(int studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
