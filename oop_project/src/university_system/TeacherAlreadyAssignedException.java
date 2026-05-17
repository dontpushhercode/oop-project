package university_system;

/**
 *
 * Thrown when a teacher is already assigned
 * to a course or section.
 */
import java.io.Serializable;
public class TeacherAlreadyAssignedException extends Exception implements Serializable {

    public TeacherAlreadyAssignedException() {
        super("Teacher is already assigned");
    }

    public TeacherAlreadyAssignedException(String message) {
        super(message);
    }
}