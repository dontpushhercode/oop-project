package university_system;

import java.io.Serializable;

/**
 * Thrown when a user tries to perform an action without the required permissions.
 * This is part of AuthenticationException hierarchy.
 */
public class NoPermissionException extends AuthenticationException implements Serializable {
    private static final long serialVersionUID = 1L;

    public NoPermissionException() {
        super("No permission to perform this action");
    }

    public NoPermissionException(String message) {
        super(message);
    }
}