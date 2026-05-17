package university_system;

import java.io.Serializable;

/**
 * Thrown when a student tries to enroll in a course they are already enrolled in.
 * Prefer AlreadyAssignedException when a more general duplicate assignment error is enough.
 */
public class AlreadyEnrolledException extends AlreadyAssignedException implements Serializable {
    private static final long serialVersionUID = 1L;

    public AlreadyEnrolledException() {
        super("Student is already enrolled in this course");
    }

    public AlreadyEnrolledException(String message) {
        super(message);
    }
}
