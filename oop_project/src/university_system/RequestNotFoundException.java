package university_system;

/**
 *
 * Thrown when a request cannot be found
 * in the system.
 */
import java.io.Serializable;
public class RequestNotFoundException extends Exception implements Serializable {

    public RequestNotFoundException() {
        super("Request not found");
    }

    public RequestNotFoundException(String message) {
        super(message);
    }
}