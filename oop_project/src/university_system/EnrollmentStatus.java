package university_system;
/**
 * Represents the status of a student's enrollment.
 *
 * This status describes the current state of an enrollment
 * in a course section (active, completed, or withdrawn).
 */
public enum EnrollmentStatus {
	
	/**
     * The student is currently enrolled in the course.
     */
    ACTIVE,
    
    /**
     * The student has successfully completed the course.
     */
    COMPLETED,
    
    /**
     * The student has withdrawn from the course.
     */
    WITHDRAWN
}