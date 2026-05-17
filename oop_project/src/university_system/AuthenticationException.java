package university_system;

/**
 *
 * Thrown when a user provides invalid credentials
 * during authentication.
 */
import java.io.Serializable;
public class AuthenticationException extends Exception implements Serializable {

    public AuthenticationException() {
        super("Invalid username or password");
    }

    public AuthenticationException(String message) {
        super(message);
    }
}