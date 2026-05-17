package university_system;

/**
 *
 * Thrown when a non-researcher tries
 * to join a research project.
 */
import java.io.Serializable;
public class NotAResearcherException extends Exception implements Serializable {

    public NotAResearcherException() {
        super("User is not a researcher and cannot join a research project");
    }

    public NotAResearcherException(String message) {
        super(message);
    }
}