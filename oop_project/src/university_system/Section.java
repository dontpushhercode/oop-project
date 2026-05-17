package university_system;
import java.io.Serializable;
import java.util.*;

/**
 * Represents a course section in the university system.
 *
 * A section connects a course with a semester, teacher and list of lessons.
 * Each section has a unique ID that is generated automatically.
 */
public class Section implements Serializable {
    private static final long serialVersionUID = 1L;
	
	 /**
     * Static counter used to generate unique section IDs.
     */
    private static int counter = 0;
    
    /**
     * Unique section identifier.
     */	
	private int id;
	
	/**
     * Assigns a unique ID to each new Section object.
     */
	{
		this.id = ++counter;
	}

    /**
     * Creates a section with course, lessons, teacher and semester.
     *
     * @param course the course of this section
     * @param lessons the list of lessons
     * @param teacher the teacher assigned to this section
     * @param semester the semester of this section
     */
    Section(Course course, List<Lesson> lessons, Teacher teacher, Semester semester) {
    	this.course = course;
    	this.lessons = lessons != null ? lessons : new ArrayList<>();
		this.teacher = teacher;
		this.semester = semester;
    }
    
    /**
     * Creates a section with course, lessons and semester.
     *
     * Teacher is initialized as null.
     *
     * @param course the course of this section
     * @param lessons the list of lessons
     * @param semester the semester of this section
     */
    Section(Course course, List<Lesson> lessons, Semester semester) {
		this.course = course;
		this.lessons = lessons != null ? lessons : new ArrayList<>();
		this.teacher = null;
		this.semester = semester;
	}
    
    /**
     * Creates a section with course and semester.
     *
     * Lessons are initialized as an empty list.
     * Teacher is initialized as null.
     *
     * @param course the course of this section
     * @param semester the semester of this section
     */
    Section(Course course, Semester semester){
		this.course = course;
		this.lessons = new ArrayList<>();
		this.teacher = null;
		this.semester = semester;
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
    void setTeacher(Teacher teacher) {
    	this.teacher = teacher;
    }

    /**
     * Adds a lesson to this section.
     *
     * @param lesson the lesson to add
     * @throws IllegalArgumentException if lesson already exists in this section
     */
    void addLesson(Lesson lesson) {
    	if (this.lessons.contains(lesson)) {
            throw new IllegalArgumentException("Lesson already exists in this section");
        }
    	this.lessons.add(lesson);
    }

    /**
     * Removes a lesson from this section.
     *
     * @param lesson the lesson to remove
     * @throws IllegalArgumentException if lesson is not found in this section
     */
    void dropLesson(Lesson lesson) {
    	if (!this.lessons.contains(lesson)) {
            throw new IllegalArgumentException("Lesson not found in this section");
        }
    	this.lessons.remove(lesson);
    }

    /**
     * Updates the course of this section.
     *
     * @param course the new course
     */
    void setCourse(Course course) {
    	this.course = course;
    }

    /**
     * Updates the semester of this section.
     *
     * @param semester the new semester
     */
    void setSemester(Semester semester) {
    	this.semester = semester;
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
     * Returns the teacher assigned to this section.
     *
     * @return the assigned teacher
     */
    Teacher getTeacher() {
    	return this.teacher;
    }

    /**
     * Returns the semester of this section.
     *
     * @return the semester
     */
    Semester getSemester() {
    	return this.semester;
    }

    /**
     * Returns the list of lessons in this section.
     *
     * @return the list of lessons
     */
    List<Lesson> getLessons() {
    	return this.lessons;
    }
    
    /**
     * Returns the section ID.
     *
     * @return the section ID
     */
	int getId() {
		return this.id;
	}
	
	/**
     * Returns a string representation of the section.
     *
     * @return section information as a string
     */
    @Override
	public String toString() {
		 return "Section of " + this.course.getCourseName() + ", id: " + this.id + "\n";
	}
	
    /**
     * Compares this section with another object.
     *
     * Two sections are considered equal if they have the same ID.
     *
     * @param obj the object to compare with
     * @return true if both sections have the same ID, otherwise false
     */
    @Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    Section section = (Section) obj;

	    return id == section.id;
	}
	
	 /**
     * Returns hash code based on the section ID.
     *
     * @return hash code value
     */
	@Override
	public int hashCode() {
	    return Objects.hash(this.id);
	}

}
