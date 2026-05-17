package university_system;
import java.io.*;
import java.util.*;

/**
 * 
 */
import java.io.Serializable;
public class Section extends Course implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public Section() {
        this.lessons = new ArrayList<>();
    }

    Section(Course course, Semester semester) {
        this.course = course;
        this.semester = semester;
        this.lessons = new ArrayList<>();
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
    void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * 
     */
    void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    /**
     * 
     */
    void dropLesson(Lesson lesson) {
        this.lessons.remove(lesson);
    }

    /**
     * 
     */
    void setCourse(Course course) {
        this.course = course;
    }

    /**
     * 
     */
    void setSemester(Semester semester) {
        this.semester = semester;
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
    Teacher getTeacher() {
        return this.teacher;
    }

    /**
     * 
     */
    Semester getSemester() {
        return this.semester;
    }

    /**
     * 
     */
    List<Lesson> getLessons() {
        return this.lessons;
    }

}
