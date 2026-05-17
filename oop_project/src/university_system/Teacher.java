package university_system;
import java.io.*;
import java.util.*;
/**
 * Represents a teacher in the university system.
 * Teachers who are professors are always researchers.
 * Teachers can view and manage courses, view students, put marks,
 * send messages and complaints to other employees.
 */
public class Teacher extends Employee {
    /**
     * Default constructor
     */
	Teacher() {
	    super();
	}
	
	 /**
     * Constructor that initializes teacher with personal info, type and school.
     * If teacher type is PROFESSOR, research profile is automatically assigned.
     * @param firstName first name of the teacher
     * @param surName last name of the teacher
     * @param username username for authentication
     * @param password password for authentication
     * @param teacherType type/title of the teacher
     * @param school school the teacher belongs to
     */

	Teacher(String firstName, String surName, String username,
	        String password, TeacherType teacherType, School school) {
	    super(firstName, surName, password, username, DepartmentType.EDUCATION); 
	    this.teacherType = teacherType;
	    this.school = school;
	    this.ratingSum = 0;
	    this.ratingCount = 0;
	    if (teacherType == TeacherType.PROFESSOR) {
	    	setResearchProfile(new Researcher());
	    }
	}
	
    /**
     * Type/title of the teacher (TUTOR, LECTURER, SENIOR_LECTURER, PROFESSOR, etc).
     */
    private TeacherType teacherType;
    
    /**
     * School the teacher belongs to.
     */
    private School school;
    
    /**
     * Sum of all ratings given to this teacher.
     */
    private double ratingSum;
    /**
     * Number of ratings received by this teacher.
     */
    private double ratingCount;
    
    /**
     * Returns the average rating of this teacher.
     * Returns 0 if no ratings have been given yet.
     * @return average rating as double
     */
    double getRating() {
    	 return ratingCount == 0?0:ratingSum/ratingCount;
    }

    /**
     * Adds a rating score to this teacher.
     * @param score the rating score to add
     */
    void addRating(double score) {
        this.ratingSum += score;
        this.ratingCount++;
    }
    
    /**
     * Returns the school this teacher belongs to.
     * @return school of the teacher
     */
    School getSchool() {
        return this.school;
    }
    
    /**
     * Returns the type/title of this teacher.
     * @return teacher type
     */
    TeacherType getTeacherType() {
        return this.teacherType;
    }
    
    /**
     * Sets the type/title of this teacher.
     * If set to PROFESSOR, research profile is automatically assigned.
     * @param teacherType the new teacher type
     */
    void setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
        if (teacherType == TeacherType.PROFESSOR && getResearchProfile() == null) {
        	setResearchProfile(new Researcher());
        }
        
    }
   
    /**
     * Sets the school of this teacher.
     * @param school the school to assign
     */
    void setSchool(School school) {
        this.school = school;
    }
    
    /**
     * Returns string representation of this teacher
     * including school and type.
     * @return string representation
     */
    @Override
    public String toString() {
        return super.toString() + "School: " + school + ", Type: " + teacherType + "\n";
    }
    
    /**
     * Compares this teacher to another object by id.
     * @param obj the object to compare to
     * @return true if ids are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher other = (Teacher) obj;
        return this.getId() == other.getId();
    }
    
    /**
     * Returns hash code based on teacher id.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}