package university_system;
import java.io.*;
import java.util.*;

/**
 * Represents a student's academic transcript.
 */
public class Transcript implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    Transcript() {
    }

    /**
     * 
     */
    private ArrayList<Enrollment> enrollments;

    /**
     * 
     */
    private Student student;


    /**
     * 
     */
    double getGpa() {
        if (enrollments == null || enrollments.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (Enrollment enrollment : enrollments) {
            total += enrollment.getMark().getGpa();
        }
        return total / enrollments.size();
    }

}
