package university_system;
import java.io.*;
import java.util.*;

/**
 * Represents a bachelor student enrolled in the university system.
 * Students in their 4th year are always researchers and must have
 * a research supervisor with h-index of at least 3.
 * Students cannot exceed 21 credits and cannot fail more than 3 times. 
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
        setYear(year);
        this.school = school;
        this.failCount = 0;
    }
    
    /**
     * Academic year of the student (1-4).
     */
    private int year;
    
    /**
     * School the student belongs to.
     */
    private School school;
    
    /**
     * Academic degree of the student.
     */
    private AcademicDegree degree;
    
    /**
     * Research supervisor, required for 4th year students.
     * Supervisor must have h-index of at least 3.
     */
    private Researcher researchSupervisor;
    
    /**
     * Total credits the student is currently enrolled in.
     * Cannot exceed 21.
     */
    private int credits;
    
    /**
     * Number of times the student has failed a course.
     * Cannot exceed 3.
     */
    private int failCount;
    
    /**
     * Returns the academic degree of the student.
     * @return academic degree
     */
    AcademicDegree getDegree() {
        return this.degree;
    }
    
    /**
     * Returns the academic year of the student.
     * @return year (1-4)
     */
    int getYear() {
        return this.year;
    }
    
    /**
     * Returns the school the student belongs to.
     * @return school
     */
    School getSchool() {
        return this.school;
    }
    
    /**
     * Returns the research supervisor of the student.
     * @return research supervisor or null if not assigned
     */
    User getResearchSupervisor() {
        return this.researchSupervisor;
    }

    /**
     * Returns number of times student has failed a course.
     * @return fail count
     */
    public int getFailCount() {
        return this.failCount;
    }
    
    /**
     * Returns whether this student is a researcher.
     * 4th year students are always researchers.
     * @return true if student is a researcher
     */
    @Override
    boolean isResearcher() {
        return this.year == 4 || super.isResearcher();
    }

    /**
     * Sets the academic year of the student.
     * If year is set to 4, research profile is automatically assigned.
     * @param year the new academic year
     */
    void setYear(int year) {
        this.year = year;
        if (year == 4 && getResearchProfile() == null) {
        	setResearchProfile(new Researcher());
        }
    }
    
    /**
     * Sets the school of the student.
     * @param school the school to assign
     */
    void setSchool(School school) {
        this.school = school;
    }
    
    /**
     * Sets the academic degree of the student.
     * @param degree the degree to assign
     */
    void setDegree(AcademicDegree degree) {
        this.degree = degree;
    }
    
    /**
     * Sets the research supervisor for this student.
     * Only allowed for 4th year students.
     * Throws exception if supervisor h-index is less than 3.
     * @param researchSupervisor the supervisor to assign
     * @throws IllegalStateException if student is not in 4th year
     * @throws IllegalArgumentException if supervisor h-index is less than 3
     */
    void setResearchSupervisor(Researcher researchSupervisor) {
        if (this.year != 4) {
            throw new IllegalStateException("Only 4th year students can have a research supervisor");
        }
        if (researchSupervisor.getHIndex() < 3) {
            throw new IllegalArgumentException("Supervisor h-index must be at least 3");
        }
        this.researchSupervisor = researchSupervisor;
    }
    

    /**
     * Registers a course failure for this student.
     * Throws exception if student has already failed 3 times.
     * @throws IllegalStateException if fail count would exceed 3
     */
    void addFail() {
        if (this.failCount >= 3) {
            throw new IllegalStateException("Cannot fail more than 3 times");
        }
        this.failCount++;
    }
    
    /**
     * Returns string representation of this student
     * including school and year.
     * @return string representation
     */
    @Override
    public String toString() {
        return super.toString() + "School: " + school + ", Year: " + year + "\n";
    }
    
    /**
     * Compares this student to another object by id.
     * @param obj the object to compare to
     * @return true if ids are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student other = (Student) obj;
        return this.getId() == other.getId();
    }
    
    /**
     * Returns hash code based on student id.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}