package university_system;
import java.io.*;
import java.util.*;
import java.time.LocalDate;
/**
 * 
 */
public class RequestService {
    /**
     * 
     */
    private final Database database;

    /**
     * Default constructor
     */
    public RequestService() {
    }

    public RequestService(Database database) {
        this.database = database;
    }

    private void checkPermission(Manager manager) {
        if (manager == null || manager.getManagerType() != ManagerType.ADMINISTRATIVE) {
            throw new IllegalStateException("No permission");
        }
    }
    /**
     * 
     */
    public RegistrationRequest createRegistrationRequest(Manager manager, Student student, Course course) {
        checkPermission(manager);

        for (RegistrationRequest r : database.getFilteredRegistrationRequests(student)) {
            if (r.getCourse().equals(course) && r.getStatus() != RequestStatus.REJECTED) {
                throw new IllegalStateException("Already requested!");
            }
        }

        RegistrationRequest regReq = new RegistrationRequest(student, course);
        database.createRegistration(regReq);
        return regReq;
    }
    /**
     * 
     */
    public EmployeeRequest createEmployeeRequest(Employee employee, String content) {
        EmployeeRequest r = new EmployeeRequest(employee, content);
        database.createEmployeeRequest(r);
        return r;
    }
    /**
     * 
     */
    public List<RegistrationRequest> getRegistrationRequests(Manager manager, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(status);
    }
    /**
     * 
     */
    public List<RegistrationRequest> getRegistrationRequests(Manager manager, Student student, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(student, status);
    }
    /**
     * 
     */
    public List<EmployeeRequest> getEmployeeRequests(Manager manager, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredEmployeeRequests(status);
    }
    /**
     * 
     */
    public List<EmployeeRequest> getEmployeeRequests(Manager manager, Employee employee, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredEmployeeRequests(employee, status);
    }
    /**
     * 
     */
    public List<Request> getRequests(Manager manager, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredRequests(status);
    }
    /**
     * 
     */
    public Request getRequestInfo(Manager manager, Request request) {
        checkPermission(manager);
        return database.getRequest(request.getId());
    }
    /**
     * 
     */
    public void setStatus(Manager manager, Request request, RequestStatus status) {
        checkPermission(manager);

        Request r = database.getRequest(request.getId());
        if (r == null) {
            throw new IllegalStateException("Request not found");
        }
        r.setStatus(status);
        r.setUpdatedAt(LocalDate.now());
    }
    /**
     * 
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
        r.setUpdatedAt(LocalDate.now());
    }
    /**
     * 
     */
    public List<RegistrationRequest> getPendingRegistrationRequests(Manager manager) {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(RequestStatus.PENDING);
    }
    /**
     * 
     */
    public List<EmployeeRequest> getPendingEmployeeRequests(Manager manager) {
        checkPermission(manager);
        return database.getFilteredEmployeeRequests(RequestStatus.PENDING);
    }
}