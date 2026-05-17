package university_system;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Course {
	
	private static int counter = 0;
	
	{
		this.id = ++counter;
	}

    /**
     * Default constructors
     */
    Course() {
    	this.instructors = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
    }

    Course(String code, String name, int credits, String description, School school) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.school = school;
        this.instructors = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
    }
    
    Course(String code, String name, int credits, String description, School school,
            List<Teacher> instructors, List<Course> prerequisites) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.school = school;
        this.instructors = instructors;
        this.prerequisites = prerequisites;
   }

    private int id;
    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private int credits;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private School school;

    /**
     * 
     */
    private List<Teacher> instructors;

    /**
     * 
     */
    private List<Course> prerequisites;



    /**
     * 
     */
    
    int getId() {
        return this.id;
    }
    
    String getCourseCode() {
    	return this.code;
    }

    /**
     * 
     */
    String getCourseName() {
    	return this.name;
    }

    /**
     * 
     */
    int getCourseCredits() {
    	return this.credits;
    }

    /**
     * 
     */
    String getCourseDescription() {
    	return this.description;
    }

    /**
     * 
     */
    School getSchool() {
    	 return this.school;
    }

    /**
     * 
     */
    List<Teacher> getInstructors() {
    	return this.instructors;
    }

    /**
     * 
     */
    List<Course> getPrerequisites() {
    	return this.prerequisites;
    }

    /**
     * 
     */
    void setCode(String code) {
    	this.code = code;
    }

    /**
     * 
     */
    void setName(String name) {
    	this.name = name;
    }

    /**
     * 
     */
    void setCredits(int credits) {
    	this.credits = credits;
    }

    /**
     * 
     */
    void setDescription(String description) {
    	this.description = description;
    }

    /**
     * 
     */
    void setSchool(School school) {
    	this.school = school;
    }

    /**
     * 
     */
    void addInstructor(Teacher teacher) {
    	 this.instructors.add(teacher);
    }

    /**
     * 
     */
    void dropInstructor(Teacher teacher) {
    	 this.instructors.remove(teacher);
    }

    /**
     * 
     */
    void addPrerequisite(Course course) {
    	 this.prerequisites.add(course);
    }

    /**
     * 
     */
    void dropPrerequisite(Course course) {
    	 this.prerequisites.remove(course);
    }
    
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", description='" + description + '\'' +
                ", school=" + school +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return id == course.id &&
                credits == course.credits &&
                Objects.equals(code, course.code) &&
                Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
    	 return Objects.hash(id, code, name, credits);
    }
}