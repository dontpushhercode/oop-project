package university_system;

/**
 *
 * Thrown when a researcher with h-index below 3
 * is assigned as a research supervisor.
 */
import java.io.Serializable;
public class LowHIndexException extends Exception implements Serializable {

    public LowHIndexException() {
        super("Supervisor h-index is too low. Minimum required: 3");
    }

    public LowHIndexException(String message) {
        super(message);
    }
}