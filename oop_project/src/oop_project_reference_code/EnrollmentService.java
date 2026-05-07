package oop_project_reference_code;

import java.util.List;

public class EnrollmentService {

    private final Database db;

    public EnrollmentService(Database db) {
        this.db = db;
    }

    void assign(Student st, Section sec) {
        if (!hasApprovedRequest(st, sec.getCourse())) {
            throw new IllegalStateException("No approved registration for this course!");
        }
        if (isAlreadyEnrolledInSection(st, sec)) {
            throw new IllegalStateException("Already enrolled!");
        }
        if (isAlreadyEnrolledInCourse(st, sec.getCourse())) {
            throw new IllegalStateException("Already enrolled in this course!");
        }
        db.setOrCreateEnrollment(new Enrollment(st, sec));
    }

    void withdraw(Student st, Course course) {
        Enrollment target = findEnrollment(st, course);
        if (target == null) {
            throw new IllegalStateException("Enrollment not found");
        }
        if (target.getStatus() == EnrollmentStatus.COMPLETED ||
            target.getStatus() == EnrollmentStatus.WITHDRAWN) {
            throw new IllegalStateException("Cannot withdraw from completed or withdrawn course");
        }
        target.withdraw();
    }


    List<Enrollment> getStudentEnrollments(Student st) {
        return db.getFilteredEnrollments(st);
    }
    
    List<Enrollment> getTeacherCourseEnrollments(Teacher teacher, Course course, EnrollmentStatus status){
    	return this.db.getFilteredEnrollments(teacher, course, status);
    }
    
    private boolean hasApprovedRequest(Student st, Course c) {
        for (RegistrationRequest r : db.getFilteredRegistrationRequests(st)) {
            if (r.getCourse().equals(c) &&
                r.getStatus() == RequestStatus.APPROVED) {
                return true;
            }
        }
        return false;
    }

    private boolean isAlreadyEnrolledInSection(Student st, Section sec) {
        for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getSection().equals(sec)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAlreadyEnrolledInCourse(Student st, Course c) {
        for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getSection().getCourse().equals(c) && (e.getStatus()==EnrollmentStatus.ACTIVE || e.getStatus()==EnrollmentStatus.COMPLETED)) {
                return true;
            }
        }
        return false;
    }

    private Enrollment findEnrollment(Student st, Course c) {
        for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getSection().getCourse().equals(c)) {
                return e;
            }
        }
        return null;
    }
}

