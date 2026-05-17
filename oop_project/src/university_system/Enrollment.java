package university_system;
import java.util.*;

/**
 * Represents a student's enrollment in a course section.
 *
 * Each enrollment uniquely connects a student to a section
 * and stores academic progress information.
 */
public class Enrollment {
	
	/**
     * Counter used to generate unique enrollment IDs.
     */
	private static int counter = 0;
	
	 /**
     * Unique enrollment identifier.
     */
	private int id;
	
	/**
	 * Assigns a unique ID to each new Enrollment object.
	 */
	
	{
		this.id=++counter;
	}

	/**
     * Creates a new enrollment for a student in a course section.
     *
     * The enrollment is initialized with an empty mark and ACTIVE status.
     *
     * @param student the student who is enrolled
     * @param section the section where the student is enrolled
     */
    Enrollment(Student student, Section section) {
    	this.student = student;
		this.section = section;
		this.mark = new Mark();
		this.status = EnrollmentStatus.ACTIVE;
    }


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
     * Returns the enrollment ID.
     *
     * @return the enrollment ID
     */
    int getId() {
		return this.id;
	}
    
    /**
    * Returns the student associated with this enrollment.
    *
    * @return the enrolled student
    */
    Student getStudent() {
    	return this.student;
    }
    /**
    * Returns the section associated with this enrollment.
    *
    * @return the course section
    */
    Section getSection() {
    	return this.section;
    }

    /**
    * Returns the student's mark.
    *
    * @return the academic mark
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
     * Updates the student associated with this enrollment.
     *
     * @param student the new student
     */
    void setStudent(Student student) {
        this.student=student;
    }

     /**
     * Updates the enrollment status.
     *
     * @param status the new enrollment status
     */

    void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    /**
    * Updates the section associated with this enrollment.
    *
    * @param section the new section
    */
    void setSection(Section section) {
    	this.section = section;
    }
    
    /**
     * Updates the student's mark.
     *
     * @param mark the new academic mark
     */
    void setMark(Mark mark) {
        this.mark = mark;
    }
    
    /**
     * Marks this enrollment as completed.
     */
    void completeCourse() {
        this.status = EnrollmentStatus.COMPLETED;
    }
    
    /**
     * Marks this enrollment as withdrawn.
     */
    void withdraw() {
        this.status = EnrollmentStatus.WITHDRAWN;
    }

    /**
     * Returns a string representation of the enrollment.
     *
     * @return enrollment information as a string
     */
    @Override
    public String toString() {
        return "Enrollment: Student: " + this.student.getFullName()
                + ", Section: " + this.section.getCourse().getCourseName()
                + ", Mark: " + this.mark.getTotalPoints()
                + ", Status: " + this.status;
    }

    /**
     * Compares this enrollment with another object.
     *
     * Two enrollments are considered equal if they have the same ID.
     *
     * @param obj the object to compare with
     * @return true if both enrollments have the same ID, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Enrollment other = (Enrollment) obj;

        return this.id == other.id;
    }

    /**
     * Returns hash code based on the enrollment ID.
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}