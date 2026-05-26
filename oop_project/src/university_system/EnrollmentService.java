package university_system;

import java.util.*;

/**
 *
 * Service responsible for managing student enrollments
 * in the university system.
 */
public class EnrollmentService {

	private void log(String actor, String action) {
	    db.createLog(new Log(actor, action));
	}
	
    /**
     * Database instance used for data access.
     */
    private final Database db;

    /**
     * Constructor that initializes the service with database.
     */
    public EnrollmentService(Database db) {
    	if (db == null) {
    	    throw new IllegalArgumentException("Database cannot be null");
    	}
        this.db = db;
    }
    
    /**
     * Assigns a student to a section if approved request exists
     * and student is not already enrolled.
     */
    public void assign(Student student, Section section) {
    	
    	Student st = db.getStudent(student.getId());
    	Section sec = db.getSection(section.getId());
    	
        if (!hasApprovedRequest(student, section.getCourse())) {
            throw new IllegalStateException("No approved registration request for this course!");
        }
        
        if (getTotalCredits(st) + sec.getCourse().getCredits() > 21) {
            throw new CreditLimitExceededException();
        }
        
        if (isEnrolledInSection(st, sec)) {
            throw new AlreadyAssignedException("Student is already assigned to this section");
        }
        
        if (isEnrolledInCourse(st, sec.getCourse())) {

        	Enrollment e = findEnrollment(st, sec.getCourse(), EnrollmentStatus.ACTIVE);
        	this.db.getEnrollments().remove(e);
        	Enrollment newEnr = new Enrollment(st, section);
        	this.db.createEnrollment(newEnr);
        	
        	log(student.getFullName(), " assigned to section of the course: "+section.getCourse().getCourseCode());
            
            db.saveToFile("data.ser");
        	
        	return;
        }
        
        Enrollment e = new Enrollment(st, section);
        this.db.createEnrollment(e);
  
        log(student.getFullName(), " assigned to section of the course: "+section.getCourse().getCourseCode());
        
        db.saveToFile("data.ser");
    }

    /**
     * Withdraws a student from a course.
     * Cannot withdraw from completed or already withdrawn enrollments.
     */
    public void withdraw(Student student, Course course) {
    	Student st = db.getStudent(student.getId());
    	Course c = db.getCourse(course.getId());
    	
        Enrollment e = findEnrollment(st, c, EnrollmentStatus.ACTIVE);
        if (e == null) {
            throw new EnrollmentNotFoundException();
        }

        e.withdraw();
        
        log(student.getFullName(), " withdrawed from course: "+ course.getCourseCode());
        
        db.saveToFile("data.ser");
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
     * Returns enrollments filtered by section
     */
    public List<Enrollment> getSectionEnrollments(Section section) {
    	return db.getFilteredEnrollments(section);
    }
    
    /**
     * Returns total credits the student is currently enrolled in.
     */
    public int getTotalCredits(Student st) {
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
    public int getFailCount(Student st, Course course) {
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
    
    
    public List<Enrollment> getFailedAttempts(Student student, Course course) {
    	List<Enrollment> enrollments = new ArrayList<>();
    	Student st = db.getStudent(student.getId());
    	Course c = db.getCourse(course.getId());
    	for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getSection().getCourse().equals(c) &&
                e.getStatus() == EnrollmentStatus.COMPLETED &&
                e.getMark().getLiteralGrade().equals("F")) {
            		enrollments.add(e);
            }
    	}
    	return enrollments;
    }
    

    /**
     * Checks if the student is already enrolled in the given section.
     */
    public boolean isEnrolledInSection(Student st, Section sec) {
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
    public boolean isEnrolledInCourse(Student st, Course course) {
        for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getSection().getCourse().equals(course) &&
               (e.getStatus() == EnrollmentStatus.ACTIVE)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds and returns the enrollment for the given student and course.
     * Returns null if not found.
     */
    private Enrollment findEnrollment(Student st, Course course, EnrollmentStatus status) {
        for (Enrollment e : db.getFilteredEnrollments(st)) {
            if (e.getSection().getCourse().equals(course) && e.getStatus() == status) {
                return e;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param student
     * @param course
     * @return
     */
    private boolean hasApprovedRequest(Student student, Course course) {
        for (RegistrationRequest r : db.getFilteredRegistrationRequests(student)) {
            if (r.getCourse().equals(course) && r.getStatus() == RequestStatus.APPROVED) {
                return true;
            }
        }
        return false;
    }
}
