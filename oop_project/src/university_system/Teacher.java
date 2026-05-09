package university_system;
import java.io.*;
import java.util.*;
/**
 * 
 */
public class Teacher extends Employee {
    /**
     * Default constructor
     */
	public Teacher() {
	    super();
	}

	public Teacher(String firstName, String surName, String username,
	               String password, TeacherType teacherType, School school) {
	    super();
	    this.teacherType = teacherType;
	    this.school = school;
	}
    /**
     * 
     */
    private TeacherType teacherType;
    /**
     * 
     */
    private School school;
    /**
     * 
     */
    private Researcher researchProfile;
    /**
     * 
     */
    public double getRating() {
        // TODO implement here
        return 0;
    }
    /**
     * 
     */
    public School getSchool() {
        return this.school;
    }
    /**
     * 
     */
    public TeacherType getTeacherType() {
        return this.teacherType;
    }
    /**
     * 
     */
    void setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
    }
    
    void setSchool(School school) {
        this.school = school;
    }
    
    @Override
    public String toString() {
        return super.toString() + "School: " + school + ", Type: " + teacherType + "\n";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher other = (Teacher) obj;
        return this.getId() == other.getId();
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}