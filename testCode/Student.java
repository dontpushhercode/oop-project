package test;

import java.util.List;

public class Student extends User {
	private int year;
	private School school;
	
	public Student(String name, int id, String password, int year, School school) {
		super(name, id, password);
		this.year = year;
		this.school = school;
	}
	
	public List<Enrollment> getEnrollments(){
		return OfficeRegister.getOfficeRegister().getStudentEnrollments(this);
	}
}
