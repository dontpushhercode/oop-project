package university_system;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

/**
 * 
 */
public class Lesson {

    /**
     * Default constructor
     */
    public Lesson() {
    }

    /**
     * 
     */
    private Lesson.LessonType type;

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

    /**
     * 
     */
    public enum LessonType {
        LECTURE,
        PRACTICE,
        LABORATORY
    }

}