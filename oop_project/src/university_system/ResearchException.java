package university_system;

import java.io.Serializable;

/**
 * Exception for research-related errors.
 * Covers: h-index requirements, researcher status, project participation, etc.
 */
public class ResearchException extends UniversityException implements Serializable {
    private static final long serialVersionUID = 1L;

    public ResearchException(String message) {
        super(message);
    }

    public ResearchException(String message, Throwable cause) {
        super(message, cause);
    }
}
