package university_system;

import java.io.Serializable;

/**
 * Thrown when a student attempts to enroll in courses that would exceed their credit limit.
 * This is part of AcademicException hierarchy.
 */
public class CreditLimitExceededException extends AcademicException implements Serializable {
    private static final long serialVersionUID = 1L;

    public CreditLimitExceededException() {
        super("Credit limit exceeded. Maximum allowed: 21 credits");
    }

    public CreditLimitExceededException(String message) {
        super(message);
    }
}