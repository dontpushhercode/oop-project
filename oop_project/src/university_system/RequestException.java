import java.io.Serializable;

/**
 * Exception for request-related errors.
 * Covers: request not found, invalid request status, request conflicts, etc.
 */
public class RequestException extends UniversityException implements Serializable {
    private static final long serialVersionUID = 1L;

    public RequestException(String message) {
        super(message);
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
