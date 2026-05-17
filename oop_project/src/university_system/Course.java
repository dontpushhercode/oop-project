package university_system;
import java.io.Serializable;
import java.util.*;

/**
 * Represents a course in the university system.
 *
 * A course contains information such as course code, name, credits,
 * description, school, instructors and prerequisite courses.
 *
 * Each course has a unique id that is generated automatically.
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
	
	private static int counter = 0;
	
	/**
     * Unique identifier of the course.
     */
    private int id;
	
	/**
	 *  Instance initializer block.
     *
     * It assigns a unique ID to every new Course object.
	 */
	
	
	{
		this.id = ++counter;
	}

    /**
     * Creates an empty Course object.
     *
     * Initializes the list of instructors and prerequisites.
     */
    Course() {
    	this.instructors = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
    }
    
    /**
     * Creates a Course object with basic course information.
     *
     * The instructors and prerequisites lists are initialized as empty lists.
     *
     * @param code the course code
     * @param name the course name
     * @param credits the number of credits for the course
     * @param description the course description
     * @param school the school that offers this course
     */

	    Course(String code, String name, int credits, String description, School school) {
	        this.code = code;
	        this.name = name;
	        this.credits = credits;
	        this.description = description;
	        this.school = school;
	        this.instructors = new ArrayList<>();
	        this.prerequisites = new ArrayList<>();
	    }

    Course(String code, String name, String description, School school, int credits) {
        this(code, name, credits, description, school);
    }
    
    /**
     * Creates a Course object with full course information.
     *
     * @param code the course code
     * @param name the course name
     * @param credits the number of credits for the course
     * @param description the course description
     * @param school the school that offers this course
     * @param instructors the list of teachers who can teach this course
     * @param prerequisites the list of prerequisite courses
     */
    
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
    
    
    /**
     * Course code.
     */
    private String code;

    /**
     * Course name.
     */
    private String name;

    /**
     * Number of credits assigned to the course.
     */
    private int credits;

    /**
     * Description of the course.
     */
    private String description;

    /**
     *  School that offers this course.
     */
    private School school;

    /**
     *  List of teachers who can teach this course.
     */
    private List<Teacher> instructors;

    /**
     *  List of courses that must be completed before taking this course.
     */
    private List<Course> prerequisites;



    /**
     * Returns the unique course ID.
     *
     * @return the course ID
     */
    
	int getId() {
	        return this.id;
	    }

    /**
     * Keeps generated course ids unique after deserialization.
     */
    static void syncCounter(int maxId) {
        counter = Math.max(counter, maxId);
    }
    
    /**
     * Returns the course code.
     *
     * @return the course code
     */

    
    String getCourseCode() {
    	return this.code;
    }

    /**
     * Returns the course name.
     *
     * @return the course name
     */
    String getCourseName() {
    	return this.name;
    }

    /**
     * Returns the number of credits for this course.
     *
     * @return the number of course credits
     */
    int getCourseCredits() {
    	return this.credits;
    }

    int getCredits() {
        return this.credits;
    }

    /**
     * Returns the course description.
     *
     * @return the course description
     */
    String getCourseDescription() {
    	return this.description;
    }

    /**
     * Returns the school that offers this course.
     *
     * @return the school of the course
     */
    School getSchool() {
    	 return this.school;
    }

    /**
     * Returns the list of instructors for this course.
     *
     * @return the list of course instructors
     */
    List<Teacher> getInstructors() {
    	return this.instructors;
    }

    List<Teacher> getCoordinators() {
        return this.instructors;
    }

    /**
     * Returns the list of prerequisite courses.
     *
     * @return the list of prerequisites
     */
    List<Course> getPrerequisites() {
    	return this.prerequisites;
    }

    /**
     * Sets the course code.
     *
     * @param code the new course code
     */
    void setCode(String code) {
    	this.code = code;
    }

    /**
     * Sets the course name.
     *
     * @param name the new course name
     */
    void setName(String name) {
    	this.name = name;
    }

    /**
     * Sets the number of credits for the course.
     *
     * @param credits the new number of credits
     */
    void setCredits(int credits) {
    	this.credits = credits;
    }

    /**
     * Sets the course description.
     *
     * @param description the new course description
     */
    void setDescription(String description) {
    	this.description = description;
    }

    /**
     * Sets the school that offers this course.
     *
     * @param school the school of the course
     */
    void setSchool(School school) {
    	this.school = school;
    }

    /**
     * Adds a teacher to the list of course instructors.
     *
     * @param teacher the teacher to add
     * @throws IllegalArgumentException if the teacher is already assigned to this course
     */
    void addInstructor(Teacher teacher) {
    	
    	 if (this.instructors.contains(teacher)) {
    	        throw new IllegalArgumentException("Teacher is already assigned to this course");
    	    }
    	 
    	 this.instructors.add(teacher);
    }

    /**
     * Removes a teacher from the list of course instructors.
     *
     * @param teacher the teacher to remove
     * @throws IllegalArgumentException if the teacher is not assigned to this course
     */
    void dropInstructor(Teacher teacher) {
    	
    	 if (!this.instructors.contains(teacher)) {
    	        throw new IllegalArgumentException("Teacher is not assigned to this course");
    	    }
    	 
    	 this.instructors.remove(teacher);
    }

    /**
     * Adds a prerequisite course.
     *
     * @param course the prerequisite course to add
     * @throws IllegalArgumentException if prerequisite already exists
     */
    void addPrerequisite(Course course) {
    	
    	if (this.prerequisites.contains(course)) {
            throw new IllegalArgumentException("Prerequisite already exists");
        }
    	
    	 this.prerequisites.add(course);
    }

    /**
     * Removes a prerequisite course.
     *
     * @param course the prerequisite course to remove
     * @throws IllegalArgumentException if the prerequisite course is not found
     */
    void dropPrerequisite(Course course) {
    	
    	if (!this.prerequisites.contains(course)) {
            throw new IllegalArgumentException("Prerequisite course not found");
        }
    	
    	 this.prerequisites.remove(course);
    }
    

    /**
     * Returns a string representation of the course.
     *
     * @return course information as a string
     */
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
    
    /**
     * Compares this course with another object.
     *
     * Two courses are considered equal if they have the same id.
     * 
     *
     * @param obj the object to compare with
     * @return true if the courses are equal, otherwise false
     */
    
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null || getClass() != obj.getClass()) return false;

    	    Course course = (Course) obj;

    	    return this.id == course.id;
    }

    /**
     * Returns the hash code of the course.
     *
     * The hash code is based on id.
     *
     * @return the hash code of the course
     */
    @Override
    public int hashCode() {
    	 return Objects.hash(this.id);
    }
}
