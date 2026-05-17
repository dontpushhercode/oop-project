package university_system;
import java.io.*;
import java.util.*;
/**
 * 
 */
import java.io.Serializable;
public class Teacher extends Employee implements Serializable {
    /**
     * Default constructor
     */
	Teacher() {
	    super();
	}

	Teacher(String firstName, String surName, String username,
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
    private double ratingSum;
    /**
     * 
     */
    private double ratingCount;
    /**
     * 
     */
    double getRating() {
    	 return ratingCount == 0?0:ratingSum/ratingCount;
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
    TeacherType getTeacherType() {
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