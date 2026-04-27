package oop_project;

import java.util.List;

public class Student extends User {
	private int year;
	private School school;
	private AcademicDegree degree;
	private User supervisor;
	
	public Student(String name, int id, String password, int year, School school) {
		super(name, id, password);
		this.year = year;
		this.school = school;
	}
	
	public List<Enrollment> getEnrollments(){
		return OfficeRegister.getOfficeRegister().getStudentEnrollments(this);
	}
	
	public void enrollSection(Section sec) {
		OfficeRegister.getOfficeRegister().setEnrollment(this, sec);
	}
	
	public void registerForCourse(Course course) {
		OfficeRegister.getOfficeRegister().registerStudentToCourse(this, course);
	}
}
