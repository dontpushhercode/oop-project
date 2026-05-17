package university_system;

/**
 *
 * Thrown when a request cannot be found
 * in the system.
 */
public class RequestNotFoundException extends Exception {

    public RequestNotFoundException() {
        super("Request not found");
    }

    public RequestNotFoundException(String message) {
        super(message);
    }
}