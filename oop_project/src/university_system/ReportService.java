package university_system;

/**
 * Service class responsible for generating system reports.
 *
 * Provides functionality to create different types of reports
 * such as academic performance reports and course statistics reports.
 * Access to report generation is restricted based on manager permissions.
 */
public class ReportService {

	/**
     * Reference to the system database used for retrieving data.
     */
    private Database db;

    /**
     * Constructs a ReportService with the specified database.
     *
     * @param db database instance used for report generation
     */
    public ReportService(Database db) {
        this.db = db;
    }

    /**
     * Generates an academic performance report.
     *
     * Includes statistics about enrollments and students in the system.
     *
     * @param manager manager requesting the report
     * @return generated academic report
     * @throws NoPermissionException if manager does not have academic permissions
     */
    public Report generateAcademicReport(Manager manager) throws NoPermissionException {

        checkPermission(manager);
        Report report = new Report(ReportType.ACADEMIC_PERFORMANCE, manager);
        String content = buildAcademicStats();
        report.setContent(content);
        return report;
    }

    /**
     * Generates a course performance report.
     *
     * Contains information about the total number of courses in the system.
     *
     * @param manager manager requesting the report
     * @return generated course performance report
     * @throws NoPermissionException if manager does not have academic permissions
     */
    public Report generateCourseReport(Manager manager) throws NoPermissionException {

        checkPermission(manager);
        Report report = new Report(ReportType.COURSE_PERFORMANCE, manager);
        String content = "Total courses: " + db.getCourses().size();
        report.setContent(content);
        return report;
    }

    /**
     * Builds a string containing academic statistics.
     *
     * Includes total number of enrollments and students.
     *
     * @return formatted academic statistics
     */
    private String buildAcademicStats() {

        int enrollments = db.getEnrollments().size();
        int students = db.getStudents().size();
        return "Enrollments: " + enrollments +
               "\nStudents: " + students;
    }

    /**
     * Checks whether the manager has permission to generate reports.
     *
     * Only managers with ACADEMIC type are allowed.
     *
     * @param manager manager to check permissions for
     * @throws NoPermissionException if manager does not have access rights
     */
    private void checkPermission(Manager manager) throws NoPermissionException {
        if (manager == null || manager.getManagerType() != ManagerType.ACADEMIC) {
            throw new NoPermissionException();
        }
    }
}
