package university_system;

import java.io.Serializable;

/**
 * Thrown when a teacher does not meet the minimum h-index requirement for research.
 * This is part of ResearchException hierarchy.
 */
public class LowHIndexException extends ResearchException implements Serializable {
    private static final long serialVersionUID = 1L;

    public LowHIndexException() {
        super("Supervisor h-index is too low. Minimum required: 3");
    }

    public LowHIndexException(String message) {
        super(message);
    }
}