package university_system;
import java.io.*;
import java.util.*;

/**
 *
 * Represents a course registration request submitted
 * by a student in the university system.
 */
import java.io.Serializable;
public class RegistrationRequest extends Request implements Serializable {

    /**
     * Default constructor
     */
    RegistrationRequest() {
    }

    /**
     * Constructor that initializes the request with student and course.
     */
    RegistrationRequest(Student student, Course course) {
        super();
        this.student = student;
        this.course = course;
    }

    /**
     * Student who submitted this registration request.
     */
    private Student student;

    /**
     * Course for which registration is requested.
     */
    private Course course;

    /**
     * Returns the student who submitted this request.
     */
    Student getStudent() {
        return this.student;
    }

    /**
     * Returns the course for which registration is requested.
     */
    Course getCourse() {
        return this.course;
    }

    /**
     * Returns string representation of this registration request
     * including student and course information.
     */
    @Override
    public String toString() {
        return super.toString() + "Student: " + student + ", Course: " + course + "\n";
    }

    /**
     * Compares this registration request to another object by id.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RegistrationRequest other = (RegistrationRequest) obj;
        return this.getId() == other.getId();
    }

    /**
     * Returns hash code based on request id.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}