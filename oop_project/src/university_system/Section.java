package university_system;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Section{
    private static int counter = 0;
	
	private int id;

	{
		this.id = ++counter;
	}

    /**
     * Default constructors
     */
    Section(Course course, List<Lesson> lessons, Teacher teacher, Semester semester) {
    	this.course = course;
    	this.lessons = lessons != null ? lessons : new ArrayList<>();
		this.teacher = teacher;
		this.semester = semester;
    }
    
    Section(Course course, List<Lesson> lessons, Semester semester) {
		this.course = course;
		this.lessons = lessons != null ? lessons : new ArrayList<>();
		this.teacher = null;
		this.semester = semester;
	}
    
    Section(Course course, Semester semester){
		this.course = course;
		this.lessons = new ArrayList<>();
		this.teacher = null;
		this.semester = semester;
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
    
	int getId() {
		return this.id;
	}
	
	public String toString() {
		 return "Section of " + this.course.getCourseName() + ", id: " + this.id + "\n";
	}
	
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    Section section = (Section) obj;

	    return id == section.id &&
	            Objects.equals(course, section.course) &&
	            Objects.equals(teacher, section.teacher) &&
	            semester == section.semester;
	}

	public int hashCode() {
	    return Objects.hash(id, course, teacher, semester);
	}

}