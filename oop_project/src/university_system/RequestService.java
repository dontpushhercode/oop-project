package university_system;
import java.io.*;
import java.util.*;
import java.time.LocalDate;

/**
 *
 * Service responsible for managing registration and employee
 * requests in the university system.
 */
public class RequestService {

    /**
     * Database instance used for data access.
     */
    private final Database database;

    /**
     * Constructor that initializes the service with a database instance.
     */
    public RequestService(Database database) {
        this.database = database;
    }

    /**
     * Checks if the manager has permission to perform request operations.
     */
    private void checkPermission(Manager manager) throws NoPermissionException {
        if (manager == null || manager.getManagerType() != ManagerType.ADMINISTRATIVE) {
            throw new NoPermissionException();
        }
    }

    /**
     * Creates a new registration request for a student and course.
     * Throws exception if request already exists or manager has no permission.
     */
    public RegistrationRequest createRegistrationRequest(Manager manager, Student student, Course course)
            throws NoPermissionException, AlreadyRequestedException {
        checkPermission(manager);
        for (RegistrationRequest r : database.getFilteredRegistrationRequests(student)) {
            if (r.getCourse().equals(course) && r.getStatus() != RequestStatus.REJECTED) {
                throw new AlreadyRequestedException();
            }
        }
        RegistrationRequest regReq = new RegistrationRequest(student, course);
        database.createRegistrationRequest(regReq);
        return regReq;
    }

    /**
     * Creates a new employee request with given content.
     */
    public EmployeeRequest createEmployeeRequest(Employee employee, String content) {
        EmployeeRequest r = new EmployeeRequest(employee, content);
        database.createEmployeeRequest(r);
        return r;
    }

    /**
     * Returns registration requests filtered by status.
     */
    public List<RegistrationRequest> getRegistrationRequests(Manager manager, RequestStatus status)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(status);
    }

    /**
     * Returns registration requests filtered by student and status.
     */
    public List<RegistrationRequest> getRegistrationRequests(Manager manager, Student student, RequestStatus status)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(student, status);
    }

    /**
     * Returns employee requests filtered by status.
     */
    public List<EmployeeRequest> getEmployeeRequests(Manager manager, RequestStatus status)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getFilteredEmployeeRequests(status);
    }

    /**
     * Returns employee requests filtered by employee and status.
     */
    public List<EmployeeRequest> getEmployeeRequests(Manager manager, Employee employee, RequestStatus status)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getFilteredEmployeeRequests(employee, status);
    }

    /**
     * Returns all requests filtered by status.
     */
    public List<Request> getRequests(Manager manager, RequestStatus status)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getFilteredRequests(status);
    }

    /**
     * Returns detailed info about a specific request.
     */
    public Request getRequestInfo(Manager manager, Request request)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getRequest(request.getId());
    }

    /**
     * Returns a registration request by student and course.
     * Returns null if not found.
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
     * Returns whether student has an approved request for the given course.
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
     * Sets the status of a request.
     * Throws exception if request is not found or manager has no permission.
     */
    public void setStatus(Manager manager, Request request, RequestStatus status)
            throws NoPermissionException, RequestNotFoundException {
        checkPermission(manager);
        Request r = database.getRequest(request.getId());
        if (r == null) {
            throw new RequestNotFoundException();
        }
        r.setStatus(status);
    }

    /**
     * Cancels a pending request by setting its status to rejected.
     * Throws exception if request is not found or not pending.
     */
    public void cancelRequest(Manager manager, Request request)
            throws NoPermissionException, RequestNotFoundException, InvalidRequestStatusException {
        checkPermission(manager);
        Request r = database.getRequest(request.getId());
        if (r == null) {
            throw new RequestNotFoundException();
        }
        if (r.getStatus() != RequestStatus.PENDING) {
            throw new InvalidRequestStatusException("Only pending requests can be cancelled");
        }
        r.setStatus(RequestStatus.REJECTED);
    }

    /**
     * Returns all pending registration requests.
     */
    public List<RegistrationRequest> getPendingRegistrationRequests(Manager manager)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(RequestStatus.PENDING);
    }

    /**
     * Returns all pending employee requests.
     */
    public List<EmployeeRequest> getPendingEmployeeRequests(Manager manager)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getFilteredEmployeeRequests(RequestStatus.PENDING);
    }
}
