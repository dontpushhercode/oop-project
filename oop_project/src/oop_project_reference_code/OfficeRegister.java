package oop_project_reference_code;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class OfficeRegister {
	
	private static OfficeRegister officeRegister;

    private final RequestService requestService;
    private final EnrollmentService enrollmentService;
    private final AcademicService academicService;
    private final CourseService courseService;
    private final UserService userService;

    private OfficeRegister() {
        Database db = Database.getDb();
        this.requestService = new RequestService(db);
        this.enrollmentService = new EnrollmentService(db);
        this.academicService = new AcademicService(db);
        this.courseService = new CourseService(db);
        this.userService = new UserService(db);
    }

    public static OfficeRegister getOfficeRegister() {
        if (officeRegister == null) {
        	officeRegister = new OfficeRegister();
        }
        return officeRegister;
    }
    
    public RequestService getRequestService() {
        return requestService;
    }

    public EnrollmentService getEnrollmentService() {
        return enrollmentService;
    }

    public AcademicService getAcademicService() {
        return academicService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public UserService getUserService() {
        return userService;
    }
    
}	