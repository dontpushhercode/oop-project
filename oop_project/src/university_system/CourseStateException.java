package university_system;

import java.io.Serializable;

/**
 * Thrown when attempting to perform operations that violate course state constraints.
 * For example: adding duplicate lessons, managing teachers when not assigned, etc.
 * This is part of CourseException hierarchy.
 */
public class CourseStateException extends CourseException implements Serializable {
    private static final long serialVersionUID = 1L;

    public CourseStateException() {
        super("Invalid course state for this operation");
    }

    public CourseStateException(String message) {
        super(message);
    }
}
