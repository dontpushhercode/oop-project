package university_system;

/**
 *
 * Thrown when a student tries to enroll
 * in a course they are already enrolled in.
 */
import java.io.Serializable;
public class AlreadyEnrolledException extends Exception implements Serializable {

    public AlreadyEnrolledException() {
        super("Student is already enrolled in this course");
    }

    public AlreadyEnrolledException(String message) {
        super(message);
    }
}