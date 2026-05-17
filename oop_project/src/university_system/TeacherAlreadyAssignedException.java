package university_system;

import java.io.Serializable;

/**
 * Thrown when attempting to assign a teacher to a course when already assigned.
 * This is part of CourseException hierarchy.
 */
public class TeacherAlreadyAssignedException extends CourseException implements Serializable {
    private static final long serialVersionUID = 1L;

    public TeacherAlreadyAssignedException() {
        super("Teacher is already assigned");
    }

    public TeacherAlreadyAssignedException(String message) {
        super(message);
    }
}