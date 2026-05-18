package university_system;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

/**
 *
 * Service responsible for managing courses, sections
 * and lessons in the university system.
 */
public class CourseService {
	
	private void log(String actor, String action) {
	    db.createLog(new Log(actor, action));
	}

    /**
     * Database instance used for data access.
     */
    private final Database db;

    /**
     * Constructor that initializes the service with a database instance.
     */
    CourseService(Database db) {
    	if (db == null) {
    	    throw new IllegalArgumentException("Database cannot be null");
    	}
        this.db = db;
    }

    /**
     * Checks if the manager has permission to perform course operations.
     */
    private void checkPermission(Manager manager) throws NoPermissionException {
        if (manager == null || manager.getManagerType() != ManagerType.OR) {
            throw new NoPermissionException();
        }
    }

    /**
     * Creates a new course and saves it to the database.
     */
    public Course createCourse(Manager manager, String code, String name, String description, School school, int credits) throws NoPermissionException {
        checkPermission(manager);
        Course course = new Course(code, name, credits, description, school);
        db.createCourse(course);
        
        log(manager.getFullName(), "Created course: " + code);
        
        db.saveToFile("data.ser");
        return course;
    }

    /**
     * Returns a course from the database by id.
     */
    public Course getCourse(int id) {
        return db.getCourse(id);
    }

    /**
     * Creates a new section for a course and saves it to the database.
     */
    public Section createSection(Manager manager, Course course, Semester semester) throws NoPermissionException {
        checkPermission(manager);
        Section section = new Section(course, semester);
        db.createSection(section);
        
        log(manager.getFullName(), "Created section for course " + course.getCourseCode());
        
        db.saveToFile("data.ser");
        return section;
    }

    /**
     * Returns a section from the database by id.
     */
    public Section getSection(int id) {
        return db.getSection(id);
    }

    /**
     * Creates a new lesson with given type, day and time.
     */
    public Lesson createLesson(Manager manager, LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime) throws NoPermissionException {
        checkPermission(manager);
        db.saveToFile("data.ser");
        
        log(manager.getFullName(), "Created lesson: " + type + " on " + day);
        
        return new Lesson(type, day, startTime, endTime);
    }

    /**
     * Returns all courses associated with the given teacher.
     */
    public List<Course> getCourses(Teacher teacher) {
        return new ArrayList<>(db.getFilteredCourses(teacher));
    }

    /**
     * Returns all sections associated with the given teacher.
     */
    public List<Section> getSections(Teacher teacher) {
        return new ArrayList<>(db.getFilteredSections(teacher));
    }
    
    /**
     * Returns all courses in the system.
     */
    public List<Course> getCourses(){
    	return new ArrayList<>(db.getCourses());
    }
    
    /**
     * Returns all sections in the system.
     * @return
     */
    public List<Section> getSections(){
    	return new ArrayList<>(db.getSections());
    }
    
    /**
     * Updates the name and description of a course.
     */
    public void updateCourse(Manager manager, Course course, String name, String description) throws NoPermissionException {
        checkPermission(manager);
        Course c = db.getCourse(course.getId());
        c.setName(name);
        c.setDescription(description);
        
        log(manager.getFullName(), "Updated course: " + course.getCourseCode());
        
        db.saveToFile("data.ser");
    }

    /**
     * Adds an instructor to a course.
     */
    public void addInstructor(Manager manager, Course course, Teacher teacher) throws NoPermissionException, AlreadyAssignedException {
        checkPermission(manager);
        Course c = db.getCourse(course.getId());
        for (Teacher t : c.getInstructors()) {
            if (t.equals(teacher)) {
                throw new AlreadyAssignedException("Teacher already assigned to this course");
            }
        }
        c.addInstructor(teacher);
        
        log(manager.getFullName(), "Added instructor: " + teacher.toString()+" to course: " + course.getCourseCode());
        
        db.saveToFile("data.ser");
    }

    /**
     * Assigns a teacher to a section.
     */
    public void addTeacher(Manager manager, Section sec, Teacher teacher) throws NoPermissionException, AlreadyAssignedException {
        checkPermission(manager);
        Section s = db.getSection(sec.getId());
        if (s.getTeacher() != null) {
            throw new AlreadyAssignedException("Teacher already assigned to this section");
        }
        s.setTeacher(teacher);
        
        log(manager.getFullName(), "Added teacher: " + teacher.toString() + " to section of the course: " + sec.getCourse().getCourseCode());
        
        db.saveToFile("data.ser");
    }

    /**
     * Removes an instructor from a course.
     */
    public void dropInstructor(Manager manager, Course course, Teacher teacher) throws NoPermissionException, CourseStateException {
        checkPermission(manager);
        Course c = db.getCourse(course.getId());
        for (Teacher t : c.getInstructors()) {
            if (t.equals(teacher)) {
                c.dropInstructor(teacher);
                
                log(manager.getFullName(), "Dropped instructor: " + teacher.toString()+" from course: "+course.getCourseCode());
                
                db.saveToFile("data.ser");
                return;
            }
        }
        throw new CourseStateException("Teacher not assigned to this course");
    }

    /**
     * Removes the teacher from a section.
     */
    public void dropTeacher(Manager manager, Section sec) throws NoPermissionException, CourseStateException {
        checkPermission(manager);
        Section s = db.getSection(sec.getId());
        if (s.getTeacher() == null) {
            throw new CourseStateException("No teacher assigned to this section");
        }
        s.setTeacher(null);
        
        log(manager.getFullName(), "Dropped teacher from section of course: " + sec.getCourse().getCourseCode());
        
        db.saveToFile("data.ser");
    }

    /**
     * Adds a lesson to a section.
     */
    public void addLesson(Manager manager, Section sec, Lesson lesson) throws NoPermissionException, AlreadyAssignedException {
        checkPermission(manager);
        Section s = db.getSection(sec.getId());
        for (Lesson l : s.getLessons()) {
            if (l.equals(lesson)) {
                throw new AlreadyAssignedException("Lesson already assigned to this section");
            }
        }
        s.addLesson(lesson);
        
        log(manager.getFullName(), "Added lesson: " + lesson.getLessonType()+" to the course: "+sec.getCourse().getCourseCode());
        
        db.saveToFile("data.ser");
    }

    /**
     * Removes a lesson from a section.
     */
    public void dropLesson(Manager manager, Section sec, Lesson lesson) throws NoPermissionException, CourseStateException {
        checkPermission(manager);
        Section s = db.getSection(sec.getId());
        for (Lesson l : s.getLessons()) {
            if (l.equals(lesson)) {
                s.dropLesson(lesson);
                
                log(manager.getFullName(), "Dropped lesson: " + lesson.getLessonType() + " from course: "+sec.getCourse().getCourseCode());
                
                db.saveToFile("data.ser");
                return;
            }
        }
        throw new CourseStateException("Lesson not found in this section");
    }
}
