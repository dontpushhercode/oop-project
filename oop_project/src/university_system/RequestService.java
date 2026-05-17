package university_system;
import java.util.List;

/**
 * Service for managing registration and employee requests
 * in the university system.
 * Only managers with ADMINISTRATIVE type have permission
 * to manage requests.
 */
public class RequestService {

    /**
     * Database instance used for data access.
     */
    private final Database database;

    /**
     * Constructor that initializes the service with a database.
     * @param database the database to use
     */
    public RequestService(Database database) {
        this.database = database;
    }

    /**
     * Checks if the given manager has permission to manage requests.
     * @param manager the manager to check
     * @throws IllegalStateException if manager is null or not ADMINISTRATIVE
     */
    private void checkPermission(Manager manager) {
        if (manager == null || manager.getManagerType() != ManagerType.ADMINISTRATIVE) {
            throw new IllegalStateException("No permission");
        }
    }

    /**
     * Calculates total credits from all approved registration requests for a student.
     * @param student the student to calculate credits for
     * @return total credits from approved requests
     */
    public int getTotalCredits(Student student) {
        int total = 0;
        for (RegistrationRequest r : database.getFilteredRegistrationRequests(student)) {
            if (r.getStatus() == RequestStatus.APPROVED) {
                total += r.getCourse().getCredits();
            }
        }
        return total;
    }

    /**
     * Creates a registration request for a student to enroll in a course.
     * Checks that student does not exceed 21 credits.
     * Throws exception if student already has a pending or approved request
     * for the same course.
     * @param manager the manager creating the request
     * @param student the student requesting enrollment
     * @param course the course to enroll in
     * @return the created RegistrationRequest
     * @throws IllegalStateException if student already requested this course
     * @throws IllegalStateException if student would exceed 21 credits
     */
    public RegistrationRequest createRegistrationRequest(
            Manager manager, Student student, Course course) {
        checkPermission(manager);
        if (getTotalCredits(student) + course.getCredits() > 21) {
            throw new IllegalStateException("Student cannot exceed 21 credits");
        }
        for (RegistrationRequest r : database.getFilteredRegistrationRequests(student)) {
            if (r.getCourse().equals(course) && r.getStatus() != RequestStatus.REJECTED) {
                throw new IllegalStateException("Already requested!");
            }
        }
        RegistrationRequest regReq = new RegistrationRequest(student, course);
        database.createRegistrationRequest(regReq);
        return regReq;
    }

    /**
     * Creates an employee request with the given content.
     * @param employee the employee submitting the request
     * @param content the content of the request
     * @return the created EmployeeRequest
     */
    public EmployeeRequest createEmployeeRequest(Employee employee, String content) {
        EmployeeRequest request = new EmployeeRequest(employee, content);
        database.createEmployeeRequest(request);
        return request;
    }

    /**
     * Returns all registration requests filtered by status.
     * @param manager the manager performing the action
     * @param status the status to filter by
     * @return list of registration requests with the given status
     */
    public List<RegistrationRequest> getRegistrationRequests(
            Manager manager, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(status);
    }

    /**
     * Returns all registration requests for a specific student filtered by status.
     * @param manager the manager performing the action
     * @param student the student whose requests to retrieve
     * @param status the status to filter by
     * @return list of registration requests for the student
     */
    public List<RegistrationRequest> getRegistrationRequests(
            Manager manager, Student student, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(student, status);
    }

    /**
     * Returns a specific registration request for a student and course.
     * @param student the student to search for
     * @param course the course to search for
     * @return the found RegistrationRequest or null
     */
    public RegistrationRequest getRegistrationRequest(Student student, Course course) {
        for (RegistrationRequest r : database.getFilteredRegistrationRequests(student)) {
            if (r.getCourse().equals(course)) {
                return r;
            }
        }
        return null;
    }
    

    /**
     * Checks if a student has an approved registration request for a course.
     * @param student the student to check
     * @param course the course to check
     * @return true if student has an approved request for the course
     */
    public boolean hasApprovedRequest(Student student, Course course) {
        for (RegistrationRequest r : database.getFilteredRegistrationRequests(student)) {
            if (r.getCourse().equals(course) && r.getStatus() == RequestStatus.APPROVED) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all employee requests filtered by status.
     * @param manager the manager performing the action
     * @param status the status to filter by
     * @return list of employee requests with the given status
     */
    public List<EmployeeRequest> getEmployeeRequests(
            Manager manager, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredEmployeeRequests(status);
    }

    /**
     * Returns all employee requests for a specific employee filtered by status.
     * @param manager the manager performing the action
     * @param employee the employee whose requests to retrieve
     * @param status the status to filter by
     * @return list of employee requests for the employee
     */
    public List<EmployeeRequest> getEmployeeRequests(
            Manager manager, Employee employee, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredEmployeeRequests(employee, status);
    }

    /**
     * Returns all requests filtered by status.
     * @param manager the manager performing the action
     * @param status the status to filter by
     * @return list of all requests with the given status
     */
    public List<Request> getRequests(Manager manager, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredRequests(status);
    }

    /**
     * Returns detailed info about a specific request.
     * @param manager the manager performing the action
     * @param request the request to look up
     * @return the found Request or null
     */
    public Request getRequestInfo(Manager manager, Request request) {
        checkPermission(manager);
        return database.getRequest(request.getId());
    }

    /**
     * Updates the status of a request.
     * @param manager the manager performing the action
     * @param request the request to update
     * @param status the new status to set
     * @throws IllegalStateException if request is not found
     */
    public void setStatus(Manager manager, Request request, RequestStatus status) {
        checkPermission(manager);
        Request r = database.getRequest(request.getId());
        if (r == null) {
            throw new IllegalStateException("Request not found");
        }
        r.setStatus(status);
    }

    /**
     * Cancels a pending request by setting its status to REJECTED.
     * @param manager the manager performing the action
     * @param request the request to cancel
     * @throws IllegalStateException if request not found or not PENDING
     */
    public void cancelRequest(Manager manager, Request request) {
        checkPermission(manager);
        Request r = database.getRequest(request.getId());
        if (r == null) {
            throw new IllegalStateException("Request not found");
        }
        if (r.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Only pending requests can be cancelled");
        }
        r.setStatus(RequestStatus.REJECTED);
    }

    /**
     * Returns all pending registration requests.
     * @param manager the manager performing the action
     * @return list of all pending registration requests
     */
    public List<RegistrationRequest> getPendingRegistrationRequests(Manager manager) {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(RequestStatus.PENDING);
    }

    /**
     * Returns all pending employee requests.
     * @param manager the manager performing the action
     * @return list of all pending employee requests
     */
    public List<EmployeeRequest> getPendingEmployeeRequests(Manager manager) {
        checkPermission(manager);
        return database.getFilteredEmployeeRequests(RequestStatus.PENDING);
    }
}