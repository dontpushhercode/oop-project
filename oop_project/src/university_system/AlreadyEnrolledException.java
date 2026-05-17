package university_system;

/**
 *
 * Thrown when a student tries to enroll
 * in a course they are already enrolled in.
 */
public class AlreadyEnrolledException extends Exception {

    public AlreadyEnrolledException() {
        super("Student is already enrolled in this course");
    }

    public AlreadyEnrolledException(String message) {
        super(message);
    }
}