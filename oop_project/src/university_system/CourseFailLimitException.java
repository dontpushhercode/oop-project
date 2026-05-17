package university_system;

/**
 *
 * Thrown when a student has failed
 * the same course more than 3 times.
 */
import java.io.Serializable;
public class CourseFailLimitException extends Exception implements Serializable {

    public CourseFailLimitException() {
        super("Course fail limit exceeded. Cannot fail the same course more than 3 times");
    }

    public CourseFailLimitException(String message) {
        super(message);
    }
}