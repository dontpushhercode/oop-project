package university_system;

import java.io.Serializable;

/**
 * Thrown when a non-researcher user attempts to perform research-related actions.
 * This is part of ResearchException hierarchy.
 */
public class NotAResearcherException extends ResearchException implements Serializable {
    private static final long serialVersionUID = 1L;

    public NotAResearcherException() {
        super("User is not a researcher and cannot join a research project");
    }

    public NotAResearcherException(String message) {
        super(message);
    }
}