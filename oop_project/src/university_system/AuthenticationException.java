package university_system;

/**
 *
 * Thrown when a user provides invalid credentials
 * during authentication.
 */
public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super("Invalid username or password");
    }

    public AuthenticationException(String message) {
        super(message);
    }
}