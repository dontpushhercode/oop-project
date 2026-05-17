package university_system;

import java.io.Serializable;

/**
 * Thrown when attempting to perform an operation on a request with invalid status.
 * For example: cancelling a non-pending request.
 * This is part of RequestException hierarchy.
 */
public class InvalidRequestStatusException extends RequestException implements Serializable {
    private static final long serialVersionUID = 1L;

    public InvalidRequestStatusException() {
        super("Cannot perform operation on request with current status");
    }

    public InvalidRequestStatusException(String message) {
        super(message);
    }
}
