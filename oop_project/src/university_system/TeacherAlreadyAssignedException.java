package university_system;

import java.io.Serializable;

/**
 * Thrown when attempting to assign a teacher to a course when already assigned.
 * Prefer AlreadyAssignedException when a more general duplicate assignment error is enough.
 */
public class TeacherAlreadyAssignedException extends AlreadyAssignedException implements Serializable {
    private static final long serialVersionUID = 1L;

    public TeacherAlreadyAssignedException() {
        super("Teacher is already assigned");
    }

    public TeacherAlreadyAssignedException(String message) {
        super(message);
    }
}
