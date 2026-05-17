package university_system;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

/**
 * Represents a lesson or class session in a course section.
 */
public class Lesson implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public Lesson() {
    }

    /**
     * 
     */
    private LessonType type;

    /**
     * 
     */
    private DayOfWeek day;

    /**
     * 
     */
    private LocalTime startTime;

    /**
     * 
     */
    private LocalTime endTime;



    /**
     * 
     */
    void setTime() {
        // TODO implement here
    }

    /**
     * 
     */
    void setDay() {
        // TODO implement here
    }

    /**
     * 
     */
    void setType() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getTime() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getDay() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getType() {
        // TODO implement here
    }

}