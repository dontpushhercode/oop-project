package university_system;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Enrollment {
	private static int counter = 0;
	
	private int id;
	
	{
		this.id=++counter;
	}

    /**
     * Default constructor
     */
    Enrollment(Student student, Section section) {
    	this.student = student;
		this.section = section;
		this.mark = new Mark();
		this.status = EnrollmentStatus.ACTIVE;
    }

    /**
     * 
     */
    private Student student;

    /**
     * 
     */
    private Section section;

    /**
     * 
     */
    private Mark mark;

    /**
     * 
     */
    private EnrollmentStatus status;







    /**
     * 
     */
    int getId() {
		return this.id;
	}
    
    Student getStudent() {
    	return this.student;
    }

    /**
     * 
     */
    Section getSection() {
    	return this.section;
    }

    /**
     * 
     */
    Mark getMark() {
    	return this.mark;
    }

    /**
     * 
     */
    EnrollmentStatus getStatus() {
    	return this.status;
    }

    /**
     * 
     */
    void completeCourse() {
		this.status = EnrollmentStatus.COMPLETED;
	}

    /**
     * 
     */
    void setStudent(Student student) {
        this.student=student
    }

    /**
     * 
     */
    void setMark(Mark mark) {
    	this.mark = mark;
    }

    /**
     * 
     */
    void setSection(Section sec) {
    	this.section = sec;
    }
    
    void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
    
    void withdraw() {
	    this.status = EnrollmentStatus.WITHDRAWN;
	}
    
    @Override
    public String toString() {
        return "Enrollment for " + this.student + " in " + this.section +
                ", status: " + this.status +
                ", id: " + this.id + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Enrollment enrollment = (Enrollment) obj;

        return id == enrollment.id &&
                Objects.equals(student, enrollment.student) &&
                Objects.equals(section, enrollment.section) &&
                Objects.equals(mark, enrollment.mark) &&
                status == enrollment.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, section, mark, status);
    }

}