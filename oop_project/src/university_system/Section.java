package university_system;
import java.util.*;

/**
 * Represents a course section in the university system.
 *
 * A section connects a course with a semester, teacher and list of lessons.
 * Each section has a unique ID that is generated automatically.
 */
public class Section extends Course {

    /**
     * Default constructor
     */
    public Section() {
    }

    /**
     * Course associated with this section.
     */
    private Course course;

    /**
     * List of lessons in this section.
     */
    private List<Lesson> lessons;

    /**
     * Teacher assigned to this section.
     */
    private Teacher teacher;

    /**
     * Semester when this section is offered.
     */
    private Semester semester;

 
    /**
     * Assigns a teacher to this section.
     *
     * @param teacher the teacher to assign
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
     * Removes a lesson from this section.
     *
     * @param lesson the lesson to remove
     * @throws IllegalArgumentException if lesson is not found in this section
     */
    void dropLesson() {
        // TODO implement here
    }

    /**
     * Updates the course of this section.
     *
     * @param course the new course
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
     * Returns the course of this section.
     *
     * @return the course of this section
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
