package university_system;
import java.util.*;

/**
 * 
 */
public class Transcript implements Serializable {
    private static final long serialVersionUID = 1L;

	/**
     * Creates a transcript for the given student.
     *
     * @param student the student whose transcript is being created
     */
    Transcript(Student student) {
    	this.student = student;
    	this.enrollmentService = OfficeRegister.getEnrollmentService();
    }

    /**
     * The student associated with this transcript.
     */
    private final Student student;
    
    /**
     * Service used to retrieve enrollment data for the student.
     */
    private final EnrollmentService enrollmentService;

    /**
     * Retrieves all enrollments of the student.
     *
     * @return list of enrollments for the student
     */
    private List<Enrollment> getEnrollments() {
        return enrollmentService.getStudentEnrollments(student);
    }
    
    /**
     * 
     */
    public void getGpa() {
        // TODO implement here
    }

}
