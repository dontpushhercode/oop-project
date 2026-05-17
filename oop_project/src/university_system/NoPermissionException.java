package university_system;

/**
 *
 * Thrown when a user tries to perform an action
 * without the required permissions.
 */
public class NoPermissionException extends Exception {

    public NoPermissionException() {
        super("No permission to perform this action");
    }

    public NoPermissionException(String message) {
        super(message);
    }
}