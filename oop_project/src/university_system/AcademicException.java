package university_system;

import java.io.Serializable;

/**
 * Exception for academic-related errors.
 * Covers: enrollment, credits, grades, course failures, etc.
 */
public class AcademicException extends UniversityException implements Serializable {
    private static final long serialVersionUID = 1L;

    public AcademicException(String message) {
        super(message);
    }

    public AcademicException(String message, Throwable cause) {
        super(message, cause);
    }
}
