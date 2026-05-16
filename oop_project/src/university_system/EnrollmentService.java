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
    private final Database database;

    /**
     * Constructor that initializes the service with a database instance.
     */
    EnrollmentService(Database database) {
        this.database = database;
    }

    /**
     * Assigns a student to a section if all conditions are met.
     * Checks for approved registration request, and verifies
     * the student is not already enrolled.
     */
    void assign(Student student, Section section) {
        if (!hasApprovedRequest(student, section.getCourse())) {
            throw new IllegalStateException("No approved registration request for this course!");
        }
        if (isEnrolledInSection(student, section)) {
            throw new IllegalStateException("Already enrolled in this section!");
        }
        if (isEnrolledInCourse(student, section.getCourse())) {
            throw new IllegalStateException("Already enrolled in this course!");
        }
        database.createEnrollment(new Enrollment(student, section));
    }

    /**
     * Withdraws a student from a course.
     * Cannot withdraw from completed or already withdrawn enrollments.
     */
    void withdraw(Student student, Course course) {
        Enrollment target = findEnrollment(student, course);
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
    List<Enrollment> getStudentEnrollments(Student student) {
        return database.getFilteredEnrollments(student);
    }

    /**
     * Returns enrollments filtered by teacher, course and enrollment status.
     */
    List<Enrollment> getTeacherCourseEnrollments(Teacher teacher, Course course, EnrollmentStatus status) {
        return database.getFilteredEnrollments(teacher, course, status);
    }

    /**
     * Checks if the student has an approved registration request for the course.
     */
    private boolean hasApprovedRequest(Student student, Course course) {
        for (RegistrationRequest r : database.getFilteredRegistrationRequests(student)) {
            if (r.getCourse().equals(course) && r.getStatus() == RequestStatus.APPROVED) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the student is already enrolled in the given section.
     */
    private boolean isEnrolledInSection(Student student, Section section) {
        for (Enrollment e : database.getFilteredEnrollments(student)) {
            if (e.getSection().equals(section)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the student is already enrolled in the given course.
     */
    private boolean isEnrolledInCourse(Student student, Course course) {
        for (Enrollment e : database.getFilteredEnrollments(student)) {
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
    private Enrollment findEnrollment(Student student, Course course) {
        for (Enrollment e : database.getFilteredEnrollments(student)) {
            if (e.getSection().getCourse().equals(course)) {
                return e;
            }
        }
        return null;
    }
}