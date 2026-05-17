package university_system;

import java.io.Serializable;

/**
 * Thrown when a user attempts to submit a request they have already submitted.
 * This is part of RequestException hierarchy.
 */
public class AlreadyRequestedException extends RequestException implements Serializable {
    private static final long serialVersionUID = 1L;

    public AlreadyRequestedException() {
        super("Registration request already submitted for this course");
    }

    public AlreadyRequestedException(String message) {
        super(message);
    }
}