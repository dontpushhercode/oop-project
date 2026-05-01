package oop_project;

public class EmployeeRequest extends Request {
	private Employee fromEmployee;
	private String content;
	private Employee signedBy;
	
	EmployeeRequest(Employee fromEmployee, String content){
		this.fromEmployee = fromEmployee;
		this.content = content;
		this.signedBy = signedBy;
	}
	
	Employee getEmployee() {
		return this.fromEmployee;
	}
	
	Employee getSigner() {
		return this.signedBy;
	}
	
	String getContent() {
		return this.content;
	}
	
	void setSign(Employee employee) {
		this.signedBy = employee;
	}
	
}
