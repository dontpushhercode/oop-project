package university_system;

/**
 *
 * Thrown when a teacher is already assigned
 * to a course or section.
 */
public class TeacherAlreadyAssignedException extends Exception {

    public TeacherAlreadyAssignedException() {
        super("Teacher is already assigned");
    }

    public TeacherAlreadyAssignedException(String message) {
        super(message);
    }
}