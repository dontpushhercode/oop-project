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
     * Creates a new course and saves it to the database.
     * Only a manager can create a course.
     */
    Course createCourse(Manager manager, String code, String name, String description, School school, int credits) {
        Course course = new Course(code, name, description, school, credits);
        db.createCourse(course);
        return course;
    }

    /**
     * Returns a course from the database by id.
     */
    Course getCourse(Course course) {
        return db.getCourse(course.getId());
    }

    /**
     * Creates a new section for a course and saves it to the database.
     * Only a manager can create a section.
     */
    Section createSection(Manager manager, Course course, Semester semester) {
        Section section = new Section(course, semester);
        db.createSection(section);
        return section;
    }

    /**
     * Returns a section from the database by id.
     */
    Section getSection(Section section) {
        return db.getSection(section.getId());
    }

    /**
     * Creates a new lesson with given type, day and time.
     * Only a manager can create a lesson.
     */
    Lesson createLesson(Manager manager, LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        return new Lesson(type, day, startTime, endTime);
    }

    /**
     * Returns all courses associated with the given teacher.
     */
    List<Course> getCourses(Teacher teacher) {
        return db.getFilteredCourses(teacher);
    }

    /**
     * Returns all sections associated with the given teacher.
     */
    List<Section> getSections(Teacher teacher) {
        return db.getFilteredSections(teacher);
    }

    /**
     * Updates the name and description of a course.
     * Only a manager can update a course.
     */
    void updateCourse(Manager manager, Course course, String name, String description) {
        Course c = db.getCourse(course.getId());
        c.setName(name);
        c.setDescription(description);
    }

    /**
     * Adds an instructor to a course.
     * Throws exception if teacher is already assigned.
     */
    void addInstructor(Manager manager, Course course, Teacher teacher) {
        Course c = db.getCourse(course.getId());
        for (Teacher t : c.getCoordinators()) {
            if (t.equals(teacher)) {
                throw new IllegalStateException("Teacher already assigned to this course!");
            }
        }
        c.addInstructor(teacher);
    }

    /**
     * Assigns a teacher to a section.
     * Throws exception if section already has a teacher.
     */
    void addTeacher(Manager manager, Section sec, Teacher teacher) {
        Section s = db.getSection(sec.getId());
        if (s.getTeacher() != null) {
            throw new IllegalStateException("Teacher already assigned to this section!");
        }
        s.setTeacher(teacher);
    }

    /**
     * Removes an instructor from a course.
     * Throws exception if teacher is not assigned to course.
     */
    void dropInstructor(Manager manager, Course course, Teacher teacher) {
        Course c = db.getCourse(course.getId());
        for (Teacher t : c.getCoordinators()) {
            if (t.equals(teacher)) {
                c.dropInstructor(teacher);
                return;
            }
        }
        throw new IllegalStateException("Teacher not assigned to this course!");
    }

    /**
     * Removes the teacher from a section.
     * Throws exception if no teacher is assigned.
     */
    void dropTeacher(Manager manager, Section sec) {
        Section s = db.getSection(sec.getId());
        if (s.getTeacher() == null) {
            throw new IllegalStateException("No teacher assigned to this section!");
        }
        s.setTeacher(null);
    }

    /**
     * Adds a lesson to a section.
     * Throws exception if lesson already exists in section.
     */
    void addLesson(Manager manager, Section sec, Lesson lesson) {
        Section s = db.getSection(sec.getId());
        for (Lesson l : s.getLessons()) {
            if (l.equals(lesson)) {
                throw new IllegalStateException("Lesson already assigned to this section!");
            }
        }
        s.addLesson(lesson);
    }

    /**
     * Removes a lesson from a section.
     * Throws exception if lesson is not found in section.
     */
    void dropLesson(Manager manager, Section sec, Lesson lesson) {
        Section s = db.getSection(sec.getId());
        for (Lesson l : s.getLessons()) {
            if (l.equals(lesson)) {
                s.dropLesson(lesson);
                return;
            }
        }
        throw new IllegalStateException("Lesson not found in this section!");
    }
}