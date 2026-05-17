package university_system;

import java.io.Serializable;

/**
 * Thrown when attempting to perform an operation on an enrollment with invalid status.
 * For example: withdrawing from a completed or already withdrawn course.
 * This is part of AcademicException hierarchy.
 */
public class InvalidEnrollmentStatusException extends AcademicException implements Serializable {
    private static final long serialVersionUID = 1L;

    public InvalidEnrollmentStatusException() {
        super("Cannot perform operation on enrollment with current status");
    }

    public InvalidEnrollmentStatusException(String message) {
        super(message);
    }
}
