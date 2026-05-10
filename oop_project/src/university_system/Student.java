package university_system;
import java.io.*;
import java.util.*;
/**
 * 
 */
public class Student extends User {
    /**
     * Default constructor
     */
    Student() {
        super();
    }
    
    Student(String firstName, String surName, String username,
                   String password, int year, School school) {
        super(firstName, surName, username, password);
        this.year = year;
        this.school = school;
    }
    /**
     * 
     */
    private int year;
    /**
     * 
     */
    private School school;
    /**
     * 
     */
    private AcademicDegree degree;
    /**
     * 
     */
    private Researcher researchProfile;
    /**
     * 
     */
    private User researchSupervisor;
    /**
     * 
     */
    AcademicDegree getDegree() {
        return this.degree;
    }
    /**
     * 
     */
    int getYear() {
        return this.year;
    }
    /**
     * 
     */
    School getSchool() {
        return this.school;
    }
    /**
     * 
     */
    User getResearchSupervisor() {
        return this.researchSupervisor;
    }
    /**
     * 
     */
    void setYear(int year) {
        this.year = year;
    }
    /**
     * 
     */
    void setSchool(School school) {
        this.school = school;
    }
    /**
     * 
     */
    void setDegree(AcademicDegree degree) {
        this.degree = degree;
    }
    /**
     * 
     */
    void setResearchSupervisor(User researchSupervisor) {
        this.researchSupervisor = researchSupervisor;
    }
    
    @Override
    public String toString() {
        return super.toString() + "School: " + school + ", Year: " + year + "\n";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student other = (Student) obj;
        return this.getId() == other.getId();
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}