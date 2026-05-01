package oop_project;

import java.util.ArrayList;
import java.util.List;

public class Database {
	private static Database db;
	private List<User> users; 
	private List<Course> courses;
	private List<Section> sections;
	private List<Enrollment> enrollments;
	private List<RegistrationRequest> regRequests;
	private List<EmployeeRequest> emplRequests;
	
	private Database() {
		users = new ArrayList<>();
	    courses = new ArrayList<>();
	    sections = new ArrayList<>();
	    enrollments = new ArrayList<>();
	    regRequests = new ArrayList<>();
	    emplRequests = new ArrayList<>();
	}
	
	public static Database getDb() {
		if(db==null) {
			db = new Database();
		}
		return db;
	}
	
	List<Enrollment> getFilteredEnrollments(Student st){
		List<Enrollment> filtered = new ArrayList<Enrollment>();
		for(Enrollment e:enrollments) {
			if(e.getStudent().equals(st)) {
				filtered.add(e);
			}
		}
		return filtered;
	}
	
	List<Enrollment> getFilteredEnrollments(Section sec){
		List<Enrollment> filtered = new ArrayList<Enrollment>();
		for(Enrollment e:enrollments) {
			if(e.getSection().equals(sec)) {
				filtered.add(e);
			}
		}
		return filtered;
	}
	
	List<Enrollment> getFilteredEnrollments(Course course){
		List<Enrollment> filtered = new ArrayList<Enrollment>();
		for(Enrollment e:enrollments) {
			if(e.getSection().getCourse().equals(course)) {
				filtered.add(e);
			}
		}
		return filtered;
	}
	
	List<Enrollment> getFilteredEnrollments(Teacher teacher, Course course, EnrollmentStatus status){
		List<Enrollment> courseEnrollments = getFilteredEnrollments(course);
		List<Enrollment> filtered = new ArrayList<Enrollment>();
		for(Enrollment e:courseEnrollments) {
			if(e.getSection().getTeacher().equals(teacher) && e.getStatus()==status) {
				filtered.add(e);
			}
		}
		return filtered;
	}
	
	List<Section> getFilteredSections(Teacher teacher){
		List<Section> filtered = new ArrayList<Section>();
		for(Section s:sections) {
			if(s.getTeacher().equals(teacher)) {
				filtered.add(s);
			}
		}
		return filtered;
	}
	
	List<Section> getFilteredSections(Course course){
		List<Section> filtered = new ArrayList<Section>();
		for(Section s:sections) {
			if(s.getCourse().equals(course)) {
				filtered.add(s);
			}
		}
		return filtered;
	}
	
	List<EmployeeRequest> getFilteredEmployeeRequests(RequestStatus status){
		List<EmployeeRequest> filtered = new ArrayList<EmployeeRequest>();
		for(EmployeeRequest r:emplRequests) {
			if(r.getStatus()==status) {
				filtered.add(r);
			}
		}
		return filtered;
	}
	
	List<EmployeeRequest> getFilteredEmployeeRequests(Employee employee){
		List<EmployeeRequest> filtered = new ArrayList<EmployeeRequest>();
		for(EmployeeRequest r:emplRequests) {
			if(r.getEmployee().equals(employee)) {
				filtered.add(r);
			}
		}
		return filtered;
	}
	
	List<EmployeeRequest> getFilteredEmployeeRequests(Employee employee, RequestStatus status){
		List<EmployeeRequest> employerRequests = getFilteredEmployeeRequests(employee);
		List<EmployeeRequest> filtered = new ArrayList<EmployeeRequest>();
		for(EmployeeRequest r:employerRequests) {
			if(r.getStatus()==status) {
				filtered.add(r);
			}
		}
		return filtered;
	}
	
	List<Request> getFilteredRequests(RequestStatus status){
		List<Request> filtered = new ArrayList<Request>();
		for(RegistrationRequest r: getFilteredRegistrationRequests(status)) {
			filtered.add(r);
		}
		for(EmployeeRequest r:getFilteredEmployeeRequests(status)) {
			filtered.add(r);
		}
		return filtered;
	}
	
	List<RegistrationRequest> getFilteredRegistrationRequests(Student student){
		List<RegistrationRequest> filtered = new ArrayList<RegistrationRequest>();
		for(RegistrationRequest r:regRequests) {
			if(r.getStudent().equals(student)) {
				filtered.add(r);
			}
		}
		return filtered;
	}
	
	List<RegistrationRequest> getFilteredRegistrationRequests(Student student, RequestStatus status){
		List<RegistrationRequest> registrations = getFilteredRegistrationRequests(student);
		List<RegistrationRequest> filtered = new ArrayList<RegistrationRequest>();
		for(RegistrationRequest r:registrations) {
			if(r.getStatus()==status) {
				filtered.add(r);
			}
		}
		return filtered;
	}
	
	List<RegistrationRequest> getFilteredRegistrationRequests(RequestStatus status){
		List<RegistrationRequest> filtered = new ArrayList<RegistrationRequest>();
		for(RegistrationRequest r:regRequests) {
			if(r.getStatus()==status) {
				filtered.add(r);
			}
		}
		return filtered;
	}
	
	List<Teacher> getFilteredTeachers(Course course){
		List<Section> sections = getFilteredSections(course);
		List<Teacher> filtered = new ArrayList<Teacher>();
		for(Section s:sections) {
			filtered.add(s.getTeacher());
		}
		return filtered;
	}
	
	List<Course> getFilteredCourses(Teacher teacher){
		List<Course> filtered = new ArrayList<Course>();
		for(Course c:filtered) {
			List<Teacher> coordinators = c.getCoordinators();
			for(Teacher t:coordinators) {
				if(t.equals(teacher)) {
					filtered.add(c);
				}
			}
		}
		return filtered;
	}
	
	List<Student> getStudents(){
		List<Student> filtered = new ArrayList<Student>();
		for(User u:users) {
			if(u instanceof Student) {
				Student s = (Student) u;
				filtered.add(s);
			}
		}
		return filtered;
	}
	
	
	User getUser(int id) {
		for(User u: users) {
			if(u.getId()==id) {
				return u;
			}
		}
		return null;
	}
	
	Course getCourse(int id) {
		for(Course c:courses) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	Section getSection(int id) {
		for(Section s:sections) {
			if(s.getId() == id) {
				return s;
			}
		}
		return null;
	}
	
	Enrollment getEnrollment(int id) {
		for(Enrollment e:enrollments) {
			if(e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	RegistrationRequest getRegRequest(int id) {
		for(RegistrationRequest r:regRequests) {
			if(r.getId() == id) {
				return r;
			}
		}
		return null;
	}
	
	EmployeeRequest getEmployeeRequest(int id) {
		for(EmployeeRequest r:emplRequests) {
			if(r.getId() == id) {
				return r;
			}
		}
		return null;
	}
	
	Teacher getTeacher(int id) {
		for(User u:users) {
			if(u.getId() == id) {
				Teacher t = (Teacher) u;
				return t;
			}
		}
		return null;
	}
	
	Request getRequest(int id) {
		Request r = getRegRequest(id);
		if(r==null) r = getEmployeeRequest(id);
		return r;
	}
	
	void setOrCreateEnrollment(Enrollment e) {
		for (int i = 0; i < enrollments.size(); i++) {
	        if (enrollments.get(i).getId() == e.getId()) {
	            enrollments.set(i, e);
	            return;
	        }
	    }
	    enrollments.add(e);
	}
	
	void setOrCreateSection(Section sec) {
		for (int i = 0; i < sections.size(); i++) {
	        if (sections.get(i).getId() == sec.getId()) {
	            sections.set(i, sec);
	            return;
	        }
	    }
	    sections.add(sec);
	}
	
	void setOrCreateRegistration(RegistrationRequest r) {
		for(int i=0; i<regRequests.size(); i++) {
			if(regRequests.get(i).getId() == r.getId()) {
				regRequests.set(i, r);
				return;
			}
		}
		regRequests.add(r);
	}
	
	void setOrCreateEmployeeRequest(EmployeeRequest r) {
		for(int i=0; i<emplRequests.size(); i++) {
			if(emplRequests.get(i).getId() == r.getId()) {
				emplRequests.set(i, r);
				return;
			}
		}
		emplRequests.add(r);
	}
	
	void setOrCreateCourse(Course c) {
		for(int i=0; i<courses.size(); i++) {
			if(courses.get(i).getId() == c.getId()) {
				courses.set(i, c);
				return;
			}
		}
		courses.add(c);
	}
}
