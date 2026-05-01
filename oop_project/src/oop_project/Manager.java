package oop_project;

import java.util.List;

public class Manager extends Employee {
	
	Manager(String firstname, String secondname, String password, String username){
		super(firstname, secondname, password, username, DepartmentType.MANAGEMENT);
	}
	
	List<Request> getPendingRequests(){
		return OfficeRegister.getOfficeRegister().getRequests(RequestStatus.PENDING);
	}
	
	List<Request> getSignedRequests(){
		return OfficeRegister.getOfficeRegister().getRequests(RequestStatus.APPROVED);
	}
	
	List<RegistrationRequest> getPendingRegistration(){
		return OfficeRegister.getOfficeRegister().getRegistrationRequests(RequestStatus.PENDING);
	}
	
	List<EmployeeRequest> getPendingEmployeeRequests(){
		return OfficeRegister.getOfficeRegister().getEmployeesRequests(RequestStatus.PENDING);
	}
	
	List<Student> getStudents(){
		return OfficeRegister.getOfficeRegister().getStudents();
	}
	
	List<Teacher> getTeachers(){
		return OfficeRegister.getOfficeRegister().getTeachers();
	}
	
	Request getRequestInfo(Request request) {
		return OfficeRegister.getOfficeRegister().getRequestInfo(request);
	}
	
	void approveStudentRegistration(RegistrationRequest request) {
		OfficeRegister.getOfficeRegister().setRegistrationRequest(request, RequestStatus.APPROVED);
	}
	
	void rejectStudentRegistration(RegistrationRequest request) {
		OfficeRegister.getOfficeRegister().setRegistrationRequest(request, RequestStatus.REJECTED);
	}
	
	void addCourse(String code, String name, String description, School school, int credits) {
		OfficeRegister.getOfficeRegister().createCourse(code, name, description, school, credits);
	}
	
	void updateCourse(Course course, String name, String description) {
		OfficeRegister.getOfficeRegister().updateCourseInfo(course, name, description);
	}
	
	void udpdateCourse(Course course, String description) {
		OfficeRegister.getOfficeRegister().updateCourseInfo(course, description);
	}
	
	void assignInstructorToCourse(Course course, Teacher teacher) {
		OfficeRegister.getOfficeRegister().assignInstructorToCourse(course, teacher);
	}
	
	void assignTeacherToSection(Section sec, Teacher teacher) {
		OfficeRegister.getOfficeRegister().assignTeacherToSection(sec, teacher);
	}
	
	void dropInstructorFromCourse(Course course, Teacher teacher) {
		OfficeRegister.getOfficeRegister().dropInstructorFromCourse(course, teacher);
	}
	
	void dropTeacherFromSection(Section sec) {
		OfficeRegister.getOfficeRegister().dropTeacherFromSection(sec);
	}
}
