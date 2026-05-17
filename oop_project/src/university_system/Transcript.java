package university_system;
import java.util.*;

/**
 * Represents an academic transcript for a student.
 *
 * The Transcript provides access to a student's enrollments
 * and calculates academic performance metrics such as GPA.
 *
 * This class retrieves enrollment data through the EnrollmentService
 * provided by the OfficeRegister.
 */
public class Transcript {

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
     * Calculates the Grade Point Average (GPA) of the student.
     *
     * Only enrollments with status COMPLETED are included in the calculation.
     * If the student has no completed enrollments, GPA is 0.
     *
     * @return calculated GPA value
     */
    double getGpa() {
    	double total = 0;
        int count = 0;
        for (Enrollment e : getEnrollments()) {
            if (e.getStatus() == EnrollmentStatus.COMPLETED) {
                total += e.getMark().getTotalPoints();
                count++;
            }
        }
        return count == 0?0:total/count;
    }

}