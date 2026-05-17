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

    /**
     * Database instance used for data access.
     */
    private final Database db;

    /**
     * Constructor that initializes the service with a database instance.
     */
    CourseService(Database db) {
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
        Course course = new Course(code, name, description, school, credits);
        db.createCourse(course);
        return course;
    }

    /**
     * Returns a course from the database by id.
     */
    public Course getCourse(Course course) {
        return db.getCourse(course.getId());
    }

    /**
     * Creates a new section for a course and saves it to the database.
     */
    public Section createSection(Manager manager, Course course, Semester semester) throws NoPermissionException {
        checkPermission(manager);
        Section section = new Section(course, semester);
        db.createSection(section);
        return section;
    }

    /**
     * Returns a section from the database by id.
     */
    public Section getSection(Section section) {
        return db.getSection(section.getId());
    }

    /**
     * Creates a new lesson with given type, day and time.
     */
    public Lesson createLesson(Manager manager, LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime) throws NoPermissionException {
        checkPermission(manager);
        return new Lesson(type, day, startTime, endTime);
    }

    /**
     * Returns all courses associated with the given teacher.
     */
    public List<Course> getCourses(Teacher teacher) {
        return db.getFilteredCourses(teacher);
    }

    /**
     * Returns all sections associated with the given teacher.
     */
    public List<Section> getSections(Teacher teacher) {
        return db.getFilteredSections(teacher);
    }

    /**
     * Updates the name and description of a course.
     */
    public void updateCourse(Manager manager, Course course, String name, String description) throws NoPermissionException {
        checkPermission(manager);
        Course c = db.getCourse(course.getId());
        c.setName(name);
        c.setDescription(description);
    }

    /**
     * Adds an instructor to a course.
     */
    public void addInstructor(Manager manager, Course course, Teacher teacher) throws NoPermissionException, AlreadyAssignedException {
        checkPermission(manager);
        Course c = db.getCourse(course.getId());
        for (Teacher t : c.getCoordinators()) {
            if (t.equals(teacher)) {
                throw new AlreadyAssignedException("Teacher already assigned to this course");
            }
        }
        c.addInstructor(teacher);
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
    }

    /**
     * Removes an instructor from a course.
     */
    public void dropInstructor(Manager manager, Course course, Teacher teacher) throws NoPermissionException, CourseStateException {
        checkPermission(manager);
        Course c = db.getCourse(course.getId());
        for (Teacher t : c.getCoordinators()) {
            if (t.equals(teacher)) {
                c.dropInstructor(teacher);
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
                return;
            }
        }
        throw new CourseStateException("Lesson not found in this section");
    }
}
