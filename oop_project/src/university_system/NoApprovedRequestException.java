package university_system;

/**
 *
 * Thrown when a student tries to enroll in a course
 * without an approved registration request.
 */
import java.io.Serializable;
public class NoApprovedRequestException extends Exception implements Serializable {

    public NoApprovedRequestException() {
        super("No approved registration request for this course");
    }

    public NoApprovedRequestException(String message) {
        super(message);
    }
}