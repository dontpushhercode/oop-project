package university_system;
import java.io.*;
import java.util.*;

/**
 * Represents a student's enrollment in a course section.
 *
 * Each enrollment uniquely connects a student to a section
 * and stores academic progress information.
 */
public class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;
	
	{
		this.id=++counter;
	}
    
    /**
     * Counter used for automatic enrollment id generation.
     */
    private static int counter;
    
    /**
     * Unique enrollment identifier.
     */
    private int id;

    /**
     * Student associated with the enrollment.
     */
    private Student student;

    /**
     * Course section associated with the enrollment.
     */
    private Section section;

    /**
     * Academic mark information for the enrollment.
     */
    private Mark mark;

    /**
     * Current enrollment status.
     */
    private EnrollmentStatus status;
    
    /**
     * Creates a new enrollment for a student in a course section.
     *
     * The enrollment is initialized with:
     *
     * an automatically generated id
     * an empty mark record
     * ACTIVE status
     *
     *
     * @param student enrolled student
     * @param section course section
     */
    Enrollment(Student student, Section section) {
    	this.student = student;
    	this.section = section;
    	this.mark = new Mark();
    	this.status = EnrollmentStatus.ACTIVE;
    }

    /**
     * Returns the enrollment identifier.
     *
     * @return enrollment id
     */
    int getId() {
    	return this.id;
    }

    /**
     * Keeps generated enrollment ids unique after deserialization.
     */
    static void syncCounter(int maxId) {
        counter = Math.max(counter, maxId);
    }
    
    /**
     * Returns the enrolled student.
     *
     * @return associated student
     */
    Student getStudent() {
        return this.student;
    }

    /**
     * Returns the associated course section.
     *
     * @return course section
     */
    Section getSection() {
        return this.section;
    }

    /**
     * Returns the student's academic mark.
     *
     * @return enrollment mark
     */
    Mark getMark() {
        return this.mark;
    }

    /**
     * Returns the current enrollment status.
     *
     * @return enrollment status
     */
    EnrollmentStatus getStatus() {
        return this.status;
    }

    /**
     * Updates the enrollment status.
     *
     * @param status new enrollment status
     */
    void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    /**
     * Updates the enrolled student.
     *
     * @param student new student
     */
    void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Updates the enrollment mark.
     *
     * @param mark new academic mark
     */
    void setMark(Mark mark) {
        this.mark = mark;
    }

    /**
     * Updates the associated course section.
     *
     * @param section new course section
     */
    void setSection(Section section) {
        this.section = section;
    }
    
    /**
     * Marks the enrollment as withdrawn.
     */
    void withdraw() {
	    this.status = EnrollmentStatus.WITHDRAWN;
	}
    
    /**
     * Returns a string representation of the enrollment.
     *
     * @return enrollment information as string
     */
    @Override
    public String toString() {
    	return "Enrollment: Student: " + this.student.getFullName()+", Section: " 
    			+ this.section.getCourseName() + ", Mark: " + mark.getTotalPoints()
    			+ ", Status: " + this.status;
    }

    /**
     * Compares enrollments by their unique identifier.
     *
     * @param obj object to compare
     * @return true if enrollments have the same id
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Enrollment other = (Enrollment) obj;
        return this.getId() == other.getId();
    }
    
    /**
     * Returns hash code based on enrollment id.
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
    	return Integer.hashCode(this.getId());
    }
    
}
