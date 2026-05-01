package oop_project;

import java.util.List;

public class Student extends User {
	private int year;
	private School school;
	private AcademicDegree degree;
	private User supervisor;
	
	Student(String firstname, String surname, String password, String username, int year, School school) {
		super(firstname, surname, password, username);
		this.year = year;
		this.school = school;
	}
	
	void setSuperVisor(User supervisor){
		this.supervisor = supervisor;
	}
	
	public List<RegistrationRequest> getRegisteredCourses(){
		return OfficeRegister.getOfficeRegister().getStudentRegisteredCourses(this);
	}
	
	public List<Enrollment> getEnrollments(){
		return OfficeRegister.getOfficeRegister().getStudentEnrollments(this);
	}
	
	public Transcript getTranscript(){
		return OfficeRegister.getOfficeRegister().getStudentTranscript(this);
	}
	
	public double getGpa() {
		return getTranscript().getGpa();
	}

	public String viewTeachersInfo(Course course) {
		return OfficeRegister.getOfficeRegister().getTeachers(course).toString();
	}
	
	public String viewCourseInfo(Course course) {
		return OfficeRegister.getOfficeRegister().getCourseInfo(course).toString();
	}
	
	public String viewSectionInfo(Section sec) {
		return OfficeRegister.getOfficeRegister().getSectionInfo(sec).toString();
	}
	
	public void registerCourse(Course course) {
		OfficeRegister.getOfficeRegister().createRegistrationRequest(this, course);
	}
	
	public void enrollSection(Section sec) {
		OfficeRegister.getOfficeRegister().assignStudentToSection(this, sec);
	}
	
	public void withdrawCourse(Course course) {
		OfficeRegister.getOfficeRegister().withdrawFromCourse(this, course);
	}
	
	public void rateTeacher(double rate, Teacher teacher, Course course) {
		OfficeRegister.getOfficeRegister().rateTeacher(this, teacher, course, rate);
	}
	
}
