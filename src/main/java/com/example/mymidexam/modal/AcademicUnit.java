package com.example.mymidexam.modal;

import com.example.mymidexam.enume.AcademicUnitType;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class AcademicUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int academicId;
    private String academicCode;
    private String academicName;
    private AcademicUnitType type;
    @OneToMany(mappedBy = "academicUnit")
    private List<StudentRegistration> studentRegistrations;
    // List of StudentRegistrations associated with this AcademicUnit (Department)


    public AcademicUnit() {
    }

    public AcademicUnit(int academicId, String academicCode, String academicName, AcademicUnitType type, List<StudentRegistration> studentRegistrations) {
        this.academicId = academicId;
        this.academicCode = academicCode;
        this.academicName = academicName;
        this.type = type;
        this.studentRegistrations = studentRegistrations;
    }

    public AcademicUnit(int academicId) {
        this.academicId = academicId;
    }

    public int getAcademicId() {
        return academicId;
    }

    public void setAcademicId(int academicId) {
        this.academicId = academicId;
    }

    public String getAcademicCode() {
        return academicCode;
    }

    public void setAcademicCode(String academicCode) {
        this.academicCode = academicCode;
    }

    public String getAcademicName() {
        return academicName;
    }

    public void setAcademicName(String academicName) {
        this.academicName = academicName;
    }

    public AcademicUnitType getType() {
        return type;
    }

    public void setType(AcademicUnitType type) {
        this.type = type;
    }

    public List<StudentRegistration> getStudentRegistrations() {
        return studentRegistrations;
    }

    public void setStudentRegistrations(List<StudentRegistration> studentRegistrations) {
        this.studentRegistrations = studentRegistrations;
    }
}