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
    	if (db == null) {
    	    throw new IllegalArgumentException("Database cannot be null");
    	}
        this.db = db;
        this.requestService = new RequestService(db);
    }

    /**
     * Assigns a student to a section if approved request exists
     * and student is not already enrolled.
     */
    public void assign(Student student, Section section) {
    	
    	Student st = db.getStudent(student.getId());
    	Section sec = db.getSection(section.getId());
    	
        if (!requestService.getRegistrationRequest(st, sec.getCourse()).isApproved()) {
            throw new IllegalStateException("No approved registration request for this course!");
        }
        if (isEnrolledInSection(st, sec)) {
            throw new AlreadyAssignedException("Student is already assigned to this section");
        }
        if (isEnrolledInCourse(st, sec.getCourse())) {
            throw new AlreadyAssignedException("Student is already assigned to this course");
        }
        if (getTotalCredits(st) + sec.getCourse().getCredits() > 21) {
            throw new CreditLimitExceededException();
        }
        if (getFailCount(st, sec.getCourse()) >= 3) {
            throw new CourseFailLimitException();
        }
        
        db.createEnrollment(new Enrollment(st, sec));
    }

    /**
     * Withdraws a student from a course.
     * Cannot withdraw from completed or already withdrawn enrollments.
     */
    public void withdraw(Student student, Course course) {
    	Student st = db.getStudent(student.getId());
    	Course c = db.getCourse(course.getId());
    	
        Enrollment target = findEnrollment(st, c);
        if (target == null) {
            throw new EnrollmentNotFoundException();
        }
        if (target.getStatus() == EnrollmentStatus.COMPLETED ||
            target.getStatus() == EnrollmentStatus.WITHDRAWN) {
            throw new InvalidEnrollmentStatusException("Cannot withdraw from completed or withdrawn course");
        }
        target.withdraw();
    }

    /**
     * Returns all enrollments for the given student.
     */
    public List<Enrollment> getStudentEnrollments(Student st) {
        return new ArrayList<>(db.getFilteredEnrollments(st));
    }

    /**
     * Returns enrollments filtered by teacher, course and enrollment status.
     */
    public List<Enrollment> getTeacherCourseEnrollments(Teacher teacher, Course course, EnrollmentStatus status) {
        return new ArrayList<>(db.getFilteredEnrollments(teacher, course, status));
    }

    /**
     * Returns total credits the student is currently enrolled in.
     */
    private int getTotalCredits(Student st) {
        int total = 0;
        for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getStatus() == EnrollmentStatus.ACTIVE) {
                total += e.getSection().getCourse().getCredits();
            }
        }
        return total;
    }

    /**
     * Returns how many times the student has failed the given course.
     */
    private int getFailCount(Student st, Course course) {
        int count = 0;
        for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getSection().getCourse().equals(course) &&
                e.getStatus() == EnrollmentStatus.COMPLETED &&
                e.getMark().getLiteralGrade().equals("F")) {
                count++;
            }
        }
        return count;
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
