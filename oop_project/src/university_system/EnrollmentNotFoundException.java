package university_system;

/**
 *
 * Thrown when an enrollment record
 * cannot be found in the system.
 */
public class EnrollmentNotFoundException extends Exception {

    public EnrollmentNotFoundException() {
        super("Enrollment not found");
    }

    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}