package university_system;

import java.io.Serializable;

/**
 * Exception for course-related errors.
 * Covers: course registration, capacity issues, course not found, etc.
 */
public class CourseException extends UniversityException implements Serializable {
    private static final long serialVersionUID = 1L;

    public CourseException(String message) {
        super(message);
    }

    public CourseException(String message, Throwable cause) {
        super(message, cause);
    }
}
