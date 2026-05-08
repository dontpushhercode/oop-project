package university_system;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class Course {
    
	private static int counter=0;
	
	private int id;
	private String code;
	private String name;
	private int credits;
	private String description;
	private School school;
	private List<Teacher> instructors;
	private List<Course> prerequisites;
	
	{
		this.id = ++counter;
	}
	
	
    public Course() {
    	this.instructors = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
    }
    

    public Course(String code, String name, int credits, String description, School school) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.school = school;
        this.instructors = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
    }
    
    public Course(String code, String name, int credits, String description, School school,
            List<Teacher> instructors, List<Course> prerequisites) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.school = school;
        this.instructors = instructors;
        this.prerequisites = prerequisites;
   }
    

    
    public int getId() {
        return this.id;
    }

    public String getCourseCode() {
        return this.code;
    }

    public String getCourseName() {
        return this.name;
    }

    public int getCourseCredits() {
    	return this.credits
    }

    public String getCourseDescription() {
        return this.description;
    }
    
    public School getSchool() {
        return this.school;
    }

    public List<Teacher> getInstructors() {
        return this.instructors;
    }

    public List<Course> getPrerequisites() {
        return this.prerequisites;
    }
    

    public void setCode(String code) {
        this.code = code;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setSchool(School school) {
        this.school = school;
    }

    public void addInstructor(Teacher teacher) {
        this.instructors.add(teacher);
    }

    public void dropInstructor(Teacher teacher) {
        this.instructors.remove(teacher);
    }
    
    public void addPrerequisite(Course course) {
        this.prerequisites.add(course);
    }

    public void dropPrerequisite(Course course) {
        this.prerequisites.remove(course);
    }
    
   
    
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
    
    
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return id == course.id;
    }

    
    public int hashCode() {
        return Objects.hash(id);
    }
}
    
 

