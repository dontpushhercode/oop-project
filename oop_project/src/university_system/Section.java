package university_system;
import java.io.*;
import java.util.*;

/**
 * 
 */
import java.io.Serializable;
public class Section extends Course implements Serializable {

    /**
     * Default constructor
     */
    public Section() {
    }

    /**
     * 
     */
    private Course course;

    /**
     * 
     */
    private List<Lesson> lessons;

    /**
     * 
     */
    private Teacher teacher;

    /**
     * 
     */
    private Semester semester;





    /**
     * 
     */
    void setTeacher() {
        // TODO implement here
    }

    /**
     * 
     */
    void addLesson() {
        // TODO implement here
    }

    /**
     * 
     */
    void dropLesson() {
        // TODO implement here
    }

    /**
     * 
     */
    void setCourse() {
        // TODO implement here
    }

    /**
     * 
     */
    void setSemester() {
        // TODO implement here
    }

    /**
     * 
     */
    Course getCourse() {
        return this.course;
    }

    /**
     * 
     */
    public void getTeacher() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getSemester() {
        // TODO implement here
    }

    /**
     * 
     */
    public void getLessons() {
        // TODO implement here
    }

}