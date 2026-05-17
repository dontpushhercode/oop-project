package university_system;

import java.io.Serializable;

/**
 * Thrown when an enrollment record is not found.
 * This is part of AcademicException hierarchy.
 */
public class EnrollmentNotFoundException extends AcademicException implements Serializable {
    private static final long serialVersionUID = 1L;

    public EnrollmentNotFoundException() {
        super("Enrollment not found");
    }

    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}