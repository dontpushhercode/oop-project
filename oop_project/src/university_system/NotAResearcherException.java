package university_system;

/**
 *
 * Thrown when a non-researcher tries
 * to join a research project.
 */
public class NotAResearcherException extends Exception {

    public NotAResearcherException() {
        super("User is not a researcher and cannot join a research project");
    }

    public NotAResearcherException(String message) {
        super(message);
    }
}