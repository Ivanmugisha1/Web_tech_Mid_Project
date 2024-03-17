package com.example.mymidexam.modal;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class StudentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int registrationId;
    private String registrationCode;
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "academic_unit_id")
    private AcademicUnit academicUnit;

    // Constructors, getters, setters

    public StudentRegistration() {
    }

    public StudentRegistration(int registrationId, String registrationCode, Date registrationDate, Student student, Semester semester, AcademicUnit academicUnit) {
        this.registrationId = registrationId;
        this.registrationCode = registrationCode;
        this.registrationDate = registrationDate;
        this.student = student;
        this.semester = semester;
        this.academicUnit = academicUnit;
    }

    public StudentRegistration(int registrationId) {
        this.registrationId = registrationId;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public AcademicUnit getAcademicUnit() {
        return academicUnit;
    }

    public void setAcademicUnit(AcademicUnit academicUnit) {
        this.academicUnit = academicUnit;
    }

    // Add setters for foreign key ids

    public void setSemesterId(int semesterId) {
        if (this.semester == null) {
            this.semester = new Semester();
        }
        this.semester.setSemesterId(semesterId);
    }

    public void setStudentId(int studentId) {
        if (this.student == null) {
            this.student = new Student();
        }
        this.student.setStudentId(studentId);
    }

    public void setAcademicUnitId(int academicUnitId) {
        if (this.academicUnit == null) {
            this.academicUnit = new AcademicUnit();
        }
        this.academicUnit.setAcademicId(academicUnitId);
    }
}
