import java.io.Serializable;

/**
 * Base exception for the entire university system.
 * All custom exceptions in the system inherit from this class.
 */
public class UniversityException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public UniversityException(String message) {
        super(message);
    }

    public UniversityException(String message, Throwable cause) {
        super(message, cause);
    }
}
