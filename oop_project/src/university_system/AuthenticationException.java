package university_system;

import java.io.Serializable;

/**
 * Exception for authentication and authorization errors.
 * Covers: invalid credentials, permission denied, etc.
 */
public class AuthenticationException extends UniversityException implements Serializable {
    private static final long serialVersionUID = 1L;

    public AuthenticationException() {
        super("Invalid username or password");
    }

    public AuthenticationException(String message) {
        super(message);
    }
}