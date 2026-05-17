package university_system;

/**
 *
 * Thrown when an enrollment record
 * cannot be found in the system.
 */
import java.io.Serializable;
public class EnrollmentNotFoundException extends Exception implements Serializable {

    public EnrollmentNotFoundException() {
        super("Enrollment not found");
    }

    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}