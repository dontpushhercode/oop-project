package university_system;
import java.time.LocalDate;
import java.util.*;

/**
 * Central storage class of the university system.
 *
 * Stores and manages users, courses, sections, enrollments,
 * requests, research entities, and system logs.
 *
 * Implemented as a singleton.
 */
public class Database {
	
	/**
	 * Singleton database instance.
	 */
	private static Database db;
	
	/**
	 * Creates an empty database instance.
	 */
	private Database() {
		users = new ArrayList<>();
		courses = new ArrayList<>();
		sections = new ArrayList<>();
		enrollments = new ArrayList<>();
		transcripts = new ArrayList<>();
		employeeRequests = new ArrayList<>();
		registrationRequests = new ArrayList<>();
		papers = new ArrayList<>();
		projects = new ArrayList<>();
		logs = new ArrayList<>();
	}

	/**
	 * Returns the singleton database instance.
	 *
	 * @return database instance
	 */
    public static Database getDb() {
    	if(db==null) {
    		db = new Database();
    	}
    	return db;
    }

    /**
     * Registered system users.
     */
    private ArrayList<User> users;

    /**
     * Available university courses.
     */
    private ArrayList<Course> courses;

    /**
     * Course sections.
     */
    private ArrayList<Section> sections;

    /**
     * Student enrollments.
     */
    private ArrayList<Enrollment> enrollments;

    /**
     * Student transcripts.
     */
    private ArrayList<Transcript> transcripts;

    /**
     * Employee requests.
     */
    private ArrayList<EmployeeRequest> employeeRequests;

    /**
     * Student registration requests.
     */
    private ArrayList<RegistrationRequest> registrationRequests;

    /**
     * Research papers stored in the system.
     */
    private ArrayList<ResearchPaper> papers;

    /**
     * Research projects stored in the system.
     */
    private ArrayList<ResearchProject> projects;

    /**
     * System activity logs.
     */
    private ArrayList<Log> logs;

    /**
     * Returns enrollments associated with a student.
     *
     * @param st target student
     * @return list of student enrollments
     */
    List<Enrollment> getFilteredEnrollments(Student st){
		List<Enrollment> filtered = new ArrayList<Enrollment>();
		for(Enrollment e:enrollments) {
			if(e.getStudent().equals(st)) {
				filtered.add(e);
			}
		}
		return filtered;
	}
    
    /**
     * Returns enrollments associated with a section.
     *
     * @param sec target section
     * @return list of section enrollments
     */
    List<Enrollment> getFilteredEnrollments(Section sec){
		List<Enrollment> filtered = new ArrayList<Enrollment>();
		for(Enrollment e:enrollments) {
			if(e.getSection().equals(sec)) {
				filtered.add(e);
			}
		}
		return filtered;
	}
    
    /**
     * Returns enrollments associated with a course.
     *
     * @param course target course
     * @return list of course enrollments
     */
    List<Enrollment> getFilteredEnrollments(Course course){
		List<Enrollment> filtered = new ArrayList<Enrollment>();
		for(Enrollment e:enrollments) {
			if(e.getSection().getCourse().equals(course)) {
				filtered.add(e);
			}
		}
		return filtered;
	}
    
    /**
     * Returns enrollments filtered by teacher,
     * course, and enrollment status.
     *
     * @param teacher target teacher
     * @param course target course
     * @param status enrollment status
     * @return filtered enrollments
     */
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
    
    /**
     * Returns sections taught by a teacher.
     *
     * @param teacher target teacher
     * @return list of sections
     */
    List<Teacher> getFilteredTeachers(Course course){
		List<Section> sections = getFilteredSections(course);
		List<Teacher> filtered = new ArrayList<Teacher>();
		for(Section s:sections) {
			filtered.add(s.getTeacher());
		}
		return filtered;
	}

    /**
     * Returns sections associated with a course.
     *
     * @param course target course
     * @return list of sections
     */
    List<Section> getFilteredSections(Teacher teacher){
		List<Section> filtered = new ArrayList<Section>();
		for(Section s:sections) {
			if(s.getTeacher().equals(teacher)) {
				filtered.add(s);
			}
		}
		return filtered;
	}
    
    /**
     * Returns teachers associated with a course.
     *
     * @param course target course
     * @return list of teachers
     */
    List<Section> getFilteredSections(Course course){
		List<Section> filtered = new ArrayList<Section>();
		for(Section s:sections) {
			if(s.getCourse().equals(course)) {
				filtered.add(s);
			}
		}
		return filtered;
	}

    /**
     * Returns courses coordinated by a teacher.
     *
     * @param teacher target teacher
     * @return list of courses
     */
    List<Course> getFilteredCourses(Teacher teacher){
		List<Course> filtered = new ArrayList<Course>();
		for(Course c:filtered) {
			List<Teacher> coordinators = c.getInstructors();
			for(Teacher t:coordinators) {
				if(t.equals(teacher)) {
					filtered.add(c);
				}
			}
		}
		return filtered;
	}

    /**
     * Returns research papers authored by a researcher.
     *
     * @param researcher target researcher
     * @return list of research papers
     */
    List<ResearchPaper> getFilteredPapers(Researcher researcher) {
        List<ResearchPaper> filtered = new ArrayList<ResearchPaper>();
        for(ResearchPaper p:papers) {
        	for(Researcher r:p.getAuthors()) {
        		if(r.equals(researcher)) {
        			filtered.add(p);
        		}
        	}
        }
        return filtered;
    }
    
    /**
     * Returns research projects associated with a researcher.
     *
     * @param researcher target researcher
     * @return list of projects
     */
    List<ResearchProject> getFilteredProjects(Researcher researcher) {
        List<ResearchProject> filtered = new ArrayList<ResearchProject>();
        for(ResearchProject p:projects) {
        	List<Researcher> members = p.getMembers();
        	for(Researcher r:members) {
        		if(r.equals(researcher)) {
        			filtered.add(p);
        		}
        	}
        }
        return filtered;
    }

    /**
     * Returns transcripts with GPA greater than
     * or equal to the specified value.
     *
     * @param gpa minimum GPA
     * @return filtered transcripts
     */
    List<Transcript> getFilteredTranscripts(Double gpa) {
        List<Transcript> filtered = new ArrayList<Transcript>();
        for(Transcript t:transcripts) {
        	if(t.getGpa()>=gpa) {
        		filtered.add(t);
        	}
        }
        return filtered;
    }
    
    /**
     * Returns requests with the specified status.
     *
     * @param status request status
     * @return filtered requests
     */
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
    
    /**
     * Returns registration requests associated with a student.
     *
     * @param student target student
     * @return filtered registration requests
     */
    List<RegistrationRequest> getFilteredRegistrationRequests(Student student){
		List<RegistrationRequest> filtered = new ArrayList<RegistrationRequest>();
		for(RegistrationRequest r:registrationRequests) {
			if(r.getStudent().equals(student)) {
				filtered.add(r);
			}
		}
		return filtered;
	}
    
    /**
     * Returns registration requests filtered by student and request status.
     * 
     * @param student target student
     * @param status request status
     * @return filtered registration requests
     */
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
	
	/**
     * Returns registration requests with the specified status.
     *
     * @param status request status
     * @return filtered registration requests
     */
	List<RegistrationRequest> getFilteredRegistrationRequests(RequestStatus status){
		List<RegistrationRequest> filtered = new ArrayList<RegistrationRequest>();
		for(RegistrationRequest r:registrationRequests) {
			if(r.getStatus()==status) {
				filtered.add(r);
			}
		}
		return filtered;
	}

	/**
     * Returns employers requests with the specified status.
     *
     * @param status request status
     * @return filtered employer's requests
     */
    List<EmployeeRequest> getFilteredEmployeeRequests(RequestStatus status){
		List<EmployeeRequest> filtered = new ArrayList<EmployeeRequest>();
		for(EmployeeRequest r:employeeRequests) {
			if(r.getStatus()==status) {
				filtered.add(r);
			}
		}
		return filtered;
	}
	
    /**
     * Returns employers requests associated with an employee.
     *
     * @param employee target employee
     * @return filtered employer's requests
     */
	List<EmployeeRequest> getFilteredEmployeeRequests(Employee employee){
		List<EmployeeRequest> filtered = new ArrayList<EmployeeRequest>();
		for(EmployeeRequest r:employeeRequests) {
			if(r.getEmployee().equals(employee)) {
				filtered.add(r);
			}
		}
		return filtered;
	}
	
	/**
	 * Returns employers requests filtered by an employee and request status.
	 * 
	 * @param employee target employee
	 * @param status request status
	 * @return filtered employer's requests
	 */
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

	/**
	 * Returns logs created after the specified date.
	 *
	 * @param time lower date bound
	 * @return filtered logs
	 */
    List<Log> getFilteredLogs(LocalDate time) {
        List<Log> filtered = new ArrayList<Log>();
        for(Log l:logs) {
        	if(l.getTime().isAfter(time)) {
        		filtered.add(l);
        	}
        }
        return filtered;
    }
    
    /**
     * Returns all courses stored in the system.
     * 
     * @return list of courses
     */
    List<Course> getCourses(){
    	return this.courses;
    }
    
    /**
     * Returns all enrollments stored in the system.
     * 
     * @param list of enrollments
     */
    List<Enrollment> getEnrollments(){
    	return this.enrollments;
    }
    
    /**
     * Returns all students stored in the system.
     *
     * @return list of students
     */
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


	/**
	 * Returns all teachers stored in the system.
	 *
	 * @return list of teachers
	 */
    List<Teacher> getTeachers(){
    	List<Teacher> filtered = new ArrayList<Teacher>();
    	for(User u:users) {
    		if(u instanceof Teacher) {
    			Teacher t = (Teacher) u;
    			filtered.add(t);
    		}
    	}
    	return filtered;
    }

    /**
     * Returns all employees stored in the system.
     *
     * @return list of employees
     */
    List<Employee> getEmployees() {
        List<Employee> filtered = new ArrayList<Employee>();
        for(User u:users) {
        	if(u instanceof Employee) {
        		Employee e = (Employee) u;
        		filtered.add(e);
        	}
        }
        return filtered;
    }

    /**
     * Returns all managers stored in the system.
     *
     * @return list of managers
     */
    List<Manager> getManagers() {
        List<Manager> filtered = new ArrayList<Manager>();
        for(User u:users) {
        	if(u instanceof Manager) {
        		Manager m = (Manager) u;
        		filtered.add(m);
        	}
        }
        return filtered;
    }

    /**
     * Returns all requests in the system (both employee and registration requests).
     *
     * @return list of all requests
     */
    List<Request> getRequests() {
        List<Request> filtered = new ArrayList<Request>();
        for(Request r:employeeRequests) {
        	filtered.add(r);
        }
        for(Request r:registrationRequests) {
        	filtered.add(r);
        }
        return filtered;
    }

    /**
     * Returns all research projects in the system.
     *
     * @return list of projects
     */
    List<ResearchProject> getProjects() {
    	return this.projects;
    }

    /**
     * Returns all system logs.
     *
     * @return list of logs
     */
    List<Log> getLogs() {
        return this.logs;
    }

    /**
     * Returns a user by identifier.
     *
     * @param id user identifier
     * @return matching user or null if not found
     */
    User getUser(int id) {
		for(User u: users) {
			if(u.getId()==id) {
				return u;
			}
		}
		return null;
	}
    
    /**
     * Returns a course by identifier.
     * 
     * @param id course identifier
     * @return matching course or null if not found
     */
    Course getCourse(int id) {
		for(Course c:courses) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
    
    /**
     * Returns a section by identifier.
     * 
     * @param id section identifier
     * @return matching section or null if not found
     */
    Section getSection(int id) {
		for(Section s:sections) {
			if(s.getId() == id) {
				return s;
			}
		}
		return null;
	}
    
    /**
     * Returns an enrollment by identifier.
     * 
     * @param id enrollment identifier
     * @return matching enrollment or null if not found
     */
    Enrollment getEnrollment(int id) {
		for(Enrollment e:enrollments) {
			if(e.getId() == id) {
				return e;
			}
		}
		return null;
	}
    
    /**
     * Returns registration request by identifier.
     * 
     * @param id registration request identifier
     * @return matching registration request or null if not found
     */
    RegistrationRequest getRegRequest(int id) {
		for(RegistrationRequest r:registrationRequests) {
			if(r.getId() == id) {
				return r;
			}
		}
		return null;
	}
    
    /**
     * Returns an employee request by identifier.
     *
     * @param id employee request identifier
     * @return matching employee request or null if not found
     */
    EmployeeRequest getEmployeeRequest(int id) {
		for(EmployeeRequest r:employeeRequests) {
			if(r.getId() == id) {
				return r;
			}
		}
		return null;
	}
    
    /**
     * Returns a teacher by identifier.
     *
     * @param id teacher identifier
     * @return matching teacher or null if not found
     */
    Teacher getTeacher(int id) {
		for(User u:users) {
			if(u.getId() == id) {
				Teacher t = (Teacher) u;
				return t;
			}
		}
		return null;
	}
    

	/**
	 * Returns a request by identifier.
	 *
	 * Searches both registration and employee requests.
	 *
	 * @param id request identifier
	 * @return matching request or null if not found
	 */
    Request getRequest(int id) {
		Request r = getRegRequest(id);
		if(r==null) r = getEmployeeRequest(id);
		return r;
	}
    
    /**
     * Adds a teacher to the database or updates
     * an existing teacher with the same id.
     *
     * @param teacher teacher to store
     */
    void createTeacher(Teacher teacher) {
    	for (int i = 0; i < users.size(); i++) {
	        if (users.get(i).equals(teacher)) {
	            users.set(i, teacher);
	            return;
	        }
	    }
	    users.add(teacher);
    }
    
    /**
     * Adds a student to the database or updates
     * an existing student with the same id.
     *
     * @param student student to store
     */
    void createStudent(Student student) {
    	for (int i = 0; i < users.size(); i++) {
	        if (users.get(i).equals(student)) {
	            users.set(i, student);
	            return;
	        }
	    }
	    users.add(student);
    }
    
    /**
     * Adds a employee to the database or updates
     * an existing employee with the same id.
     *
     * @param employee employee to store
     */
    void createEmployee(Employee employee) {
    	for (int i = 0; i < users.size(); i++) {
	        if (users.get(i).equals(employee)) {
	            users.set(i, employee);
	            return;
	        }
	    }
	    users.add(employee);
    }
    
    /**
     * Adds a manager to the database or updates
     * an existing manager with the same id.
     *
     * @param manager manager to store
     */
    void createManager(Manager manager) {
    	for (int i = 0; i < users.size(); i++) {
	        if (users.get(i).equals(manager)) {
	            users.set(i, manager);
	            return;
	        }
	    }
	    users.add(manager);
    }

    /**
     * Adds an enrollment to the database or updates an existing
     * enrollment with the same identifier.
     *
     * @param enrollment enrollment to be stored
     */
    void createEnrollment(Enrollment enrollment) {
		for (int i = 0; i < enrollments.size(); i++) {
	        if (enrollments.get(i).equals(enrollment)) {
	            enrollments.set(i, enrollment);
	            return;
	        }
	    }
	    enrollments.add(enrollment);
	}

    /**
     * Adds a section to the database or updates an existing
     * section with the same identifier.
     *
     * @param section section to be stored
     */
    void createSection(Section section) {
		for (int i = 0; i < sections.size(); i++) {
	        if (sections.get(i).equals(section)) {
	            sections.set(i, section);
	            return;
	        }
	    }
	    sections.add(section);
	}
    
    /**
     * Adds an employee request to the database or updates an existing
     * request with the same identifier.
     *
     * @param request employee request to be stored
     */
    void createEmployeeRequest(EmployeeRequest request) {
		for(int i=0; i<employeeRequests.size(); i++) {
			if(employeeRequests.get(i).equals(request)) {
				employeeRequests.set(i, request);
				return;
			}
		}
		employeeRequests.add(request);
	}
    
    /**
     * Adds a registration request to the database or updates an existing
     * request with the same identifier.
     *
     * @param request registration request to be stored
     */
    void createRegistrationRequest(RegistrationRequest request) {
		for(int i=0; i<registrationRequests.size(); i++) {
			if(registrationRequests.get(i).equals(request)) {
				registrationRequests.set(i, request);
				return;
			}
		}
		registrationRequests.add(request);
	}

    /**
     * Adds a course to the database or updates an existing
     * course with the same identifier.
     *
     * @param course course to be stored
     */
    void createCourse(Course course) {
		for(int i=0; i<courses.size(); i++) {
			if(courses.get(i).equals(course)) {
				courses.set(i, course);
				return;
			}
		}
		courses.add(course);
	}

    /**
     * Adds a research paper to the database or updates an existing
     * paper with the same identifier.
     *
     * @param paper research paper to be stored
     */
    void createPaper(ResearchPaper paper) {
    	for(int i=0; i<papers.size(); i++) {
			if(papers.get(i).equals(paper)) {
				papers.set(i, paper);
				return;
			}
		}
		papers.add(paper);
    }

    /**
     * Adds a research project to the database or updates an existing
     * project with the same identifier.
     *
     * @param project research project to be stored
     */
    void createProject(ResearchProject project) {
    	for(int i=0; i<projects.size(); i++) {
			if(projects.get(i).equals(project)) {
				projects.set(i, project);
				return;
			}
		}
    	projects.add(project);
    }

    /**
     * Adds a log entry to the database or updates an existing
     * log with the same identifier.
     *
     * @param log log entry to be stored
     */
    void createLog(Log log) {
    	for(int i=0; i<logs.size(); i++) {
			if(logs.get(i).equals(log)) {
				logs.set(i, log);
				return;
			}
		}
    	logs.add(log);
    }
    
    /**
     * Deletes user if presented in database.
     * 
     * @param user user
     */
    void deleteUser(User user) {
    	if(this.users.contains(user)) {
    		this.users.remove(user);
    	}
    	else {
    		throw new IllegalArgumentException("User does not exist!");
    	}
    }
}