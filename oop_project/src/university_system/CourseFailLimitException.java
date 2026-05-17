package university_system;

import java.io.Serializable;

/**
 * Thrown when a student attempts to enroll in a course after failing it too many times.
 * This is part of AcademicException hierarchy.
 */
public class CourseFailLimitException extends AcademicException implements Serializable {
    private static final long serialVersionUID = 1L;

    public CourseFailLimitException() {
        super("Course fail limit exceeded. Cannot fail the same course more than 3 times");
    }

    public CourseFailLimitException(String message) {
        super(message);
    }
}