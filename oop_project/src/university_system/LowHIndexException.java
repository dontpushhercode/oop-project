package university_system;

/**
 *
 * Thrown when a researcher with h-index below 3
 * is assigned as a research supervisor.
 */
public class LowHIndexException extends Exception {

    public LowHIndexException() {
        super("Supervisor h-index is too low. Minimum required: 3");
    }

    public LowHIndexException(String message) {
        super(message);
    }
}