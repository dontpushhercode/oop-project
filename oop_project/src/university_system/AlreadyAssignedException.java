package university_system;

import java.io.Serializable;

/**
 * Thrown when an entity is assigned to a target where that assignment already exists.
 * For example: student to section, teacher to course, teacher to section, lesson to section.
 */
public class AlreadyAssignedException extends UniversityException implements Serializable {
    private static final long serialVersionUID = 1L;

    public AlreadyAssignedException() {
        super("Entity is already assigned");
    }

    public AlreadyAssignedException(String message) {
        super(message);
    }
}
