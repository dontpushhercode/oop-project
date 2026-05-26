package university_system;

/**
 * Central registry for accessing application services.
 * Provides shared instances of all core services used in the system.
 *
 * This class simplifies access to services such as course management,
 * enrollment processing, user management, research operations,
 * and reporting.
 *
 * All services are initialized once using a shared Database instance.
 */
public class OfficeRegister {

	/**
     * Shared database instance used by all services.
     */
	private static final Database db = Database.getDb();
	
    /**
     * Default constructor
     */
    public OfficeRegister() {}

    /**
     * Course service responsible for course-related operations.
     */
    private static final CourseService courseService = new CourseService(db);

    /**
     * Enrollment service responsible for managing student enrollments.
     */
    private static final EnrollmentService enrollmentService = new EnrollmentService(db);
    
    /**
     * Request service responsible for handling system and registration requests.
     */
    private static final RequestService requestService = new RequestService(db, enrollmentService);

    /**
     * Academic service responsible for academic operations and grading logic.
     */
    private static final AcademicService academicService = new AcademicService(db);

    /**
    * User service responsible for user management and role handling.
    */
    private static final UserService userService = new UserService(db);

    /**
     * Research service responsible for research projects and publications.
     */
    private static final ResearchService researchService = new ResearchService(db);
    
    /**
     * Report service responsible for generating system reports and analytics.
     */
    private static final ReportService reportService = new ReportService(db);

    /**
     * Returns the course service instance.
     * @return shared CourseService instance
     */
    public static CourseService getCourseService() {
        return courseService;
    }

    /**
     * Returns the request service instance.
     * @return shared RequestService instance
     */
    public static RequestService getRequestService() {
        return requestService;
    }

    /**
     * Returns the enrollment service instance.
     * @return shared EnrollmentService instance
     */
    public static EnrollmentService getEnrollmentService() {
        return enrollmentService;
    }

    /**
     * Returns the academic service instance.
     * @return shared AcademicService instance
     */
    public static AcademicService getAcademicService() {
        return academicService;
    }

    /**
     * Returns the user service instance.
     * @return shared UserService instance
     */
    public static UserService getUserService() {
        return userService;
    }

    /**
     * Returns the research service instance.
     * @return shared ResearchService instance
     */
    public static ResearchService getResearchService() {
        return researchService;
    }

    /**
    * Returns the report service instance.
    * @return shared ReportService instance
    */
    public static ReportService getReportService() {
    	return reportService;
    }
}