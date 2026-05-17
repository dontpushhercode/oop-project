package university_system;

import java.io.Serializable;

/**
 * Thrown when a student attempts an action that requires an approved request.
 * This is part of RequestException hierarchy.
 */
public class NoApprovedRequestException extends RequestException implements Serializable {
    private static final long serialVersionUID = 1L;

    public NoApprovedRequestException() {
        super("No approved registration request for this course");
    }

    public NoApprovedRequestException(String message) {
        super(message);
    }
}