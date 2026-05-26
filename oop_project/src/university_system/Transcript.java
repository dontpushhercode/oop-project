package university_system;
import java.util.*;
import java.io.Serializable;

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
    public double getGpa() {
        double total = 0;
        int count = 0;
        for (Enrollment enrollment : getEnrollments()) {
            if (enrollment.getStatus() == EnrollmentStatus.COMPLETED) {
                total += enrollment.getMark().getGpa();
                count++;
            }
        }
        return count == 0 ? 0 : total / count;
    }

}
