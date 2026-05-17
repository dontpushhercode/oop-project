package university_system;

/**
 *
 * Thrown when a student tries to submit
 * a duplicate registration request.
 */
public class AlreadyRequestedException extends Exception {

    public AlreadyRequestedException() {
        super("Registration request already submitted for this course");
    }

    public AlreadyRequestedException(String message) {
        super(message);
    }
}