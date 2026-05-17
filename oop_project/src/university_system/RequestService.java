package university_system;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class RequestService {

    /**
     * Database instance used for data access.
     */
    private final Database database;

    /**
     * Default constructor
     */
    public RequestService(Database database) {
    	if (database == null) {
    	    throw new IllegalArgumentException("Database cannot be null");
    	}
        this.database = database;
    }

    private void checkPermission(Manager manager) {
        if (manager == null || manager.getManagerType() != ManagerType.ADMINISTRATIVE) {
            throw new NoPermissionException();
        }
    }
    
    private void checkRegistrationPermission(Manager manager) {
        if (manager == null || manager.getManagerType() != ManagerType.OR) {
            throw new NoPermissionException();
        }
    }

    /**
     * 
     */
    public RegistrationRequest createRegistrationRequest(Manager manager, Student student, Course course) {
        checkRegistrationPermission(manager);
        return createRegistrationRequest(student, course);
    }

    /**
     * Creates a registration request from a student.
     */
    public RegistrationRequest createRegistrationRequest(Student student, Course course) {

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
     * 
     */
    public EmployeeRequest createEmployeeRequest(Employee employee, String content) {
        EmployeeRequest request = new EmployeeRequest(employee, content);
        database.createEmployeeRequest(request);
        return request;
    }
    
    /**
     * 
     */
    public List<RegistrationRequest> getRegistrationRequests(
            Manager manager, RequestStatus status) {
        checkPermission(manager);
        return new ArrayList<>(database.getFilteredRegistrationRequests(status));
    }

    /**
     * 
     */
    public List<RegistrationRequest> getRegistrationRequests(
            Manager manager, Student student, RequestStatus status) {
        checkPermission(manager);
        return new ArrayList<>(database.getFilteredRegistrationRequests(student, status));
    }

    /**
     * 
     */
    public List<EmployeeRequest> getEmployeeRequests(Manager manager, RequestStatus status) {
        checkPermission(manager);
        return new ArrayList<>(database.getFilteredEmployeeRequests(status));
    }

    /**
     * 
     */
    public List<EmployeeRequest> getEmployeeRequests(Manager manager, Employee employee, RequestStatus status) {
        checkPermission(manager);
        return new ArrayList<>(database.getFilteredEmployeeRequests(employee, status));
    }

    /**
     * 
     */
    public List<Request> getRequests(Manager manager, RequestStatus status)
            throws NoPermissionException {
        checkPermission(manager);
        return new ArrayList<>(database.getFilteredRequests(status));
    }

    /**
     * 
     */
    public Request getRequestInfo(Manager manager, Request request)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getRequest(request.getId());
    }
    
    public RegistrationRequest getRegistrationRequest(Student student, Course course) {
    	for (RegistrationRequest r : database.getFilteredRegistrationRequests(student)) {
            if (r.getCourse().equals(course)) {
            	return r;
            }
        }
    	return null;
    }
    
    /**
     * 
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
        if (request instanceof RegistrationRequest) {
            checkRegistrationPermission(manager);
        } else {
            checkPermission(manager);
        }
        Request r = database.getRequest(request.getId());
        if (r == null) {
            throw new RequestNotFoundException();
        }
        r.setStatus(status);
        if (status == RequestStatus.APPROVED) {
            r.setApproved();
        }
    }

    /**
     * 
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
     * 
     */
    public List<RegistrationRequest> getPendingRegistrationRequests(Manager manager) {
        checkPermission(manager);
        return new ArrayList<>(database.getFilteredRegistrationRequests(RequestStatus.PENDING));
    }

    /**
     * 
     */
    public List<EmployeeRequest> getPendingEmployeeRequests(Manager manager)
            throws NoPermissionException {
        checkPermission(manager);
        return new ArrayList<>(database.getFilteredEmployeeRequests(RequestStatus.PENDING));
    }
}
