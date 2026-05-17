package university_system;

/**
 *
 * Thrown when a student has failed
 * the same course more than 3 times.
 */
public class CourseFailLimitException extends Exception {

    public CourseFailLimitException() {
        super("Course fail limit exceeded. Cannot fail the same course more than 3 times");
    }

    public CourseFailLimitException(String message) {
        super(message);
    }
}