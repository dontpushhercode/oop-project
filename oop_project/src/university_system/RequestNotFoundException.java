package university_system;

import java.io.Serializable;

/**
 * Thrown when a request is not found in the system.
 * This is part of RequestException hierarchy.
 */
public class RequestNotFoundException extends RequestException implements Serializable {
    private static final long serialVersionUID = 1L;

    public RequestNotFoundException() {
        super("Request not found");
    }

    public RequestNotFoundException(String message) {
        super(message);
    }
}