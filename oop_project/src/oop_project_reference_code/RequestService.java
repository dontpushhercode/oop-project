package oop_project_reference_code;

import java.util.List;

public class RequestService {
	
	private final Database db;
	
	public RequestService(Database db) {
		this.db = db;
	}
	
	private void checkPermission(Manager manager) {
		if (manager == null || manager.getDepartment() != DepartmentType.MANAGEMENT) {
	        throw new IllegalStateException("No permission");
	    }
	}
	
	RegistrationRequest createRegistrationRequest(Manager manager, Student st, Course course) {
		
		checkPermission(manager);
		
	    for (RegistrationRequest r : db.getFilteredRegistrationRequests(st)) {
	        if (r.getCourse().equals(course) && r.getStatus() != RequestStatus.REJECTED) {
	            throw new IllegalStateException("Already requested!");
	        }
	    }

	    RegistrationRequest regReq = new RegistrationRequest(st, course);
	    db.createRegistration(regReq);
	    return regReq;
	}
	
	EmployeeRequest createEmployeeRequest(Employee empl, String content) {
		EmployeeRequest r = new EmployeeRequest(empl, content);
		db.createEmployeeRequest(r);
		return r;
	}
	
	List<RegistrationRequest> getRegistrationRequests(Manager manager, RequestStatus status){
		
		checkPermission(manager);
		
		return this.db.getFilteredRegistrationRequests(status);
	}
	
	List<RegistrationRequest> getRegistrationRequests(Manager manager, Student student, RequestStatus status){
		
		checkPermission(manager);
		
		return this.db.getFilteredRegistrationRequests(student, status);
	}
	
	List<EmployeeRequest> getEmployeeRequests(Manager manager, RequestStatus status){
		
		checkPermission(manager);
		
		return this.db.getFilteredEmployeeRequests(status);
	}
	
	List<EmployeeRequest> getEmployeeRequests(Manager manager, Employee employee, RequestStatus status){
		
		checkPermission(manager);
		
		return this.db.getFilteredEmployeeRequests(employee, status);
	}
	
	List<Request> getRequests(Manager manager, RequestStatus status){
		
		checkPermission(manager);
		
		return this.db.getFilteredRequests(status);
	}
	
	Request getRequestInfo(Manager manager, Request request) {
		
		checkPermission(manager);
		
		return this.db.getRequest(request.getId());
	}
	
	void setStatus(Manager manager, Request req, RequestStatus status) {
		
		checkPermission(manager);
		
		Request r = this.db.getRequest(req.getId());
		if(r == null) {
			throw new IllegalStateException("Request not found");
		}
		r.setStatus(status);
	}
}
