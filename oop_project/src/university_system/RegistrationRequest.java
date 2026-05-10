package university_system;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class RegistrationRequest extends Request {

    /**
     * Default constructor
     */
    RegistrationRequest() {
    	super();
    }

    RegistrationRequest(Student student, Course course) {
        super();
        this.student = student;
        this.course = course;
    }

    /**
     * 
     */
    private Student student;

    /**
     * 
     */
    private Course course;

    Student getStudent() {
        return this.student;
    }

    Course getCourse() {
        return this.course;
    }

    @Override
    public String toString() {
        return super.toString() + "Student: " + student + ", Course: " + course + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RegistrationRequest other = (RegistrationRequest) obj;
        return this.getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}