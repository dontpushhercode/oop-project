package university_system;

/**
 *
 * Thrown when a student tries to exceed
 * the maximum allowed credits of 21.
 */
public class CreditLimitExceededException extends Exception {

    public CreditLimitExceededException() {
        super("Credit limit exceeded. Maximum allowed: 21 credits");
    }

    public CreditLimitExceededException(String message) {
        super(message);
    }
}