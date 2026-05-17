package university_system;

import java.io.Serializable;

/**
 * Thrown when a student tries to enroll in a course they are already enrolled in.
 * This is part of AcademicException hierarchy.
 */
public class AlreadyEnrolledException extends AcademicException implements Serializable {
    private static final long serialVersionUID = 1L;

    public AlreadyEnrolledException() {
        super("Student is already enrolled in this course");
    }

    public AlreadyEnrolledException(String message) {
        super(message);
    }
}