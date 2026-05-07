package oop_project_reference_code;

import java.time.LocalDate;

public abstract class Request {
	private static int counter = 0;
	
	protected int id;
	protected RequestStatus requestStatus;
	protected LocalDate createdAt;
	
	{
		this.id = ++counter;
	}
	
	Request () {
		this.requestStatus = RequestStatus.PENDING;
		this.createdAt = LocalDate.now();
	}
	
	int getId() {
		return this.id;
	}
	
	void setStatus(RequestStatus status) {
		this.requestStatus = status;
	}
	
	RequestStatus getStatus() {
		return this.requestStatus;
	}
}
