package university_system;
import java.util.List;

/**
 * 
 */
public class RequestService {

	private EnrollmentService enrollmentService;
	
	private void log(String actor, String action) {
	    database.createLog(new Log(actor, action));
	}
	
    /**
     * Database instance used for data access.
     */
    private final Database database;

    /**
     * Default constructor
     */
    public RequestService(Database database, EnrollmentService enrollmentService) {
    	if (database == null) {
    	    throw new IllegalArgumentException("Database cannot be null");
    	}
        this.database = database;
        this.enrollmentService = enrollmentService;
    }

    private void checkPermission(Manager manager) {
        if (manager == null || (manager.getManagerType() != ManagerType.OR && manager.getManagerType()!= ManagerType.ADMINISTRATIVE)) {
            throw new NoPermissionException();
        }
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
        
        if(enrollmentService.isEnrolledInCourse(student, course)) {
        	throw new AlreadyAssignedException("Student is already assigned to this course");
        }
        
        if(enrollmentService.getTotalCredits(student) + course.getCredits() >= 21) {
        	throw new CreditLimitExceededException();
        }
        
        if (enrollmentService.getFailCount(student, course) >= 3) {
            throw new CourseFailLimitException();
        }
        
        RegistrationRequest regReq = new RegistrationRequest(student, course);
        database.createRegistrationRequest(regReq);
        
        log(student.getFullName(), " created registration request for course: "+course.getCourseCode());
        
        database.saveToFile("data.ser");
        return regReq;
    }

    /**
     * 
     */
    public EmployeeRequest createEmployeeRequest(Employee employee, String content) {
        EmployeeRequest request = new EmployeeRequest(employee, content);
        database.createEmployeeRequest(request);
        
        log(employee.getFullName(), " created employee request");
        
        database.saveToFile("data.ser");
        return request;
    }
    
    /**
     * 
     */
    public List<RegistrationRequest> getRegistrationRequests(
            Manager manager, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(status);
    }

    /**
     * 
     */
    public List<RegistrationRequest> getRegistrationRequests(
            Manager manager, Student student, RequestStatus status) {
        checkPermission(manager);
        return database.getFilteredRegistrationRequests(student, status);
    }

    /**
     * 
     */
    public List<RegistrationRequest> getRegistrationRequests(Student student) {
        return database.getFilteredRegistrationRequests(student);
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
    public List<EmployeeRequest> getEmployeeRequests(Employee employee, RequestStatus status) {
        return database.getFilteredEmployeeRequests(employee, status);
    }

    /**
     * 
     */
    public List<Request> getRequests(Manager manager, RequestStatus status)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getFilteredRequests(status);
    }

    /**
     * 
     */
    public Request getRequestInfo(Manager manager, int id)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getRequest(id);
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
        
        log(manager.getFullName(), " rejected request " + request.getId());
        
        database.saveToFile("data.ser");
    }
    
    /**
     * 
     */
    public void approveRequest(Manager manager, Request request)
            throws NoPermissionException, RequestNotFoundException, InvalidRequestStatusException {
        checkPermission(manager);
        Request r = database.getRequest(request.getId());
        if (r == null) {
            throw new RequestNotFoundException();
        }
        if (r.getStatus() != RequestStatus.PENDING) {
            throw new InvalidRequestStatusException("Only pending requests can be approved");
        }
        r.setStatus(RequestStatus.APPROVED);
        
        log(manager.getFullName(), " approved request " + request.getId());
        
        database.saveToFile("data.ser");
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
    public List<EmployeeRequest> getPendingEmployeeRequests(Manager manager)
            throws NoPermissionException {
        checkPermission(manager);
        return database.getFilteredEmployeeRequests(RequestStatus.PENDING);
    }
}
