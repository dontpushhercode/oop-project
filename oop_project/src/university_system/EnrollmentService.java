package university_system;

import java.util.*;

/**
 *
 * Service responsible for managing student enrollments
 * in the university system.
 */
public class EnrollmentService {

    /**
     * Database instance used for data access.
     */
    private final Database db;

    /**
     * RequestService used to check registration requests.
     */
    private final RequestService requestService;

    /**
     * Constructor that initializes the service with database.
     */
    public EnrollmentService(Database db) {
        this.db = db;
        this.requestService = new RequestService(db);
    }

    /**
     * Assigns a student to a section if approved request exists
     * and student is not already enrolled.
     */
    public void assign(Student st, Section sec) {
        if (!requestService.getRegistrationRequest(st, sec.getCourse()).isApproved()) {
            throw new IllegalStateException("No approved registration request for this course!");
        }
        if (isEnrolledInSection(st, sec)) {
            throw new IllegalStateException("Already enrolled in this section!");
        }
        if (isEnrolledInCourse(st, sec.getCourse())) {
            throw new IllegalStateException("Already enrolled in this course!");
        }
        db.createEnrollment(new Enrollment(st, sec));
    }

    /**
     * Withdraws a student from a course.
     * Cannot withdraw from completed or already withdrawn enrollments.
     */
    public void withdraw(Student st, Course course) {
        Enrollment target = findEnrollment(st, course);
        if (target == null) {
            throw new IllegalStateException("Enrollment not found!");
        }
        if (target.getStatus() == EnrollmentStatus.COMPLETED ||
            target.getStatus() == EnrollmentStatus.WITHDRAWN) {
            throw new IllegalStateException("Cannot withdraw from completed or withdrawn course!");
        }
        target.withdraw();
    }

    /**
     * Returns all enrollments for the given student.
     */
    public List<Enrollment> getStudentEnrollments(Student st) {
        return db.getFilteredEnrollments(st);
    }

    /**
     * Returns enrollments filtered by teacher, course and enrollment status.
     */
    public List<Enrollment> getTeacherCourseEnrollments(Teacher teacher, Course course, EnrollmentStatus status) {
        return db.getFilteredEnrollments(teacher, course, status);
    }

    /**
     * Checks if the student is already enrolled in the given section.
     */
    private boolean isEnrolledInSection(Student st, Section sec) {
        for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getSection().equals(sec)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the student is already enrolled in the given course.
     */
    private boolean isEnrolledInCourse(Student st, Course course) {
        for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getSection().getCourse().equals(course) &&
               (e.getStatus() == EnrollmentStatus.ACTIVE ||
                e.getStatus() == EnrollmentStatus.COMPLETED)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds and returns the enrollment for the given student and course.
     * Returns null if not found.
     */
    private Enrollment findEnrollment(Student st, Course course) {
        for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getSection().getCourse().equals(course)) {
                return e;
            }
        }
        return null;
    }
}