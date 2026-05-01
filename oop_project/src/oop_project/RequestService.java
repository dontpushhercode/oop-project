package oop_project;

import java.util.List;

public class RequestService {
	
	private final Database db;
	
	public RequestService(Database db) {
		this.db = db;
	}
	
	RegistrationRequest createRegistrationRequest(Student st, Course course) {
	    for (RegistrationRequest r : db.getFilteredRegistrationRequests(st)) {
	        if (r.getCourse().equals(course) && r.getStatus() != RequestStatus.REJECTED) {
	            throw new IllegalStateException("Already requested!");
	        }
	    }

	    RegistrationRequest regReq = new RegistrationRequest(st, course);
	    db.setOrCreateRegistration(regReq);
	    return regReq;
	}
	
	EmployeeRequest createEmployeeRequest(Employee empl, String content) {
		EmployeeRequest r = new EmployeeRequest(empl, content);
		db.setOrCreateEmployeeRequest(r);
		return r;
	}
	
	List<RegistrationRequest> getRegistrationRequests(RequestStatus status){
		return this.db.getFilteredRegistrationRequests(status);
	}
	
	List<RegistrationRequest> getRegistrationRequests(Student student, RequestStatus status){
		return this.db.getFilteredRegistrationRequests(student, status);
	}
	
	List<EmployeeRequest> getEmployeeRequests(RequestStatus status){
		return this.db.getFilteredEmployeeRequests(status);
	}
	
	List<EmployeeRequest> getEmployeeRequests(Employee employee, RequestStatus status){
		return this.db.getFilteredEmployeeRequests(employee, status);
	}
	
	List<Request> getRequests(RequestStatus status){
		return this.db.getFilteredRequests(status);
	}
	
	Request getRequestInfo(Request request) {
		return this.db.getRequest(request.getId());
	}
	
	void setStatus(Request req, RequestStatus status) {
		Request r = this.db.getRequest(req.getId());
		if(r == null) {
			throw new IllegalStateException("Request not found");
		}
		r.setStatus(status);
	}
}
