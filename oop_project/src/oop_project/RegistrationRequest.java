package oop_project;

public class RegistrationRequest extends Request {
	
	private Student student;
	private Course course;
	
	RegistrationRequest(Student student, Course course) {	
		super();
		this.student = student;
		this.course = course;
	}
	
	Student getStudent() {
		return this.student;
	}
	
	Course getCourse() {
		return this.course;
	}
	
}
