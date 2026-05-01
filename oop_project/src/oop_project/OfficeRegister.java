package oop_project;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class OfficeRegister {
	
	private static OfficeRegister officeRegister;

    private final RequestService requestService;
    private final EnrollmentService enrollmentService;
    private final AcademicService academicService;
    private final CourseService courseService;
    private final UserService userService;

    private OfficeRegister() {
        Database db = Database.getDb();
        this.requestService = new RequestService(db);
        this.enrollmentService = new EnrollmentService(db);
        this.academicService = new AcademicService(db);
        this.courseService = new CourseService(db);
        this.userService = new UserService(db);
    }

    public static OfficeRegister getOfficeRegister() {
        if (officeRegister == null) {
        	officeRegister = new OfficeRegister();
        }
        return officeRegister;
    }

//Request
   
    RegistrationRequest createRegistrationRequest(Student st, Course c) {
        return requestService.createRegistrationRequest(st, c);
    }
    
    EmployeeRequest createEmployeeRequest(Employee empl, String content) {
    	return requestService.createEmployeeRequest(empl, content);
    }
    
    Request getRequestInfo(Request request) {
    	return requestService.getRequestInfo(request);
    }
    
    List<RegistrationRequest> getRegistrationRequests(RequestStatus status){
    	return requestService.getRegistrationRequests(status);
    }

    List<RegistrationRequest> getStudentRegisteredCourses(Student student){
    	return requestService.getRegistrationRequests(student, RequestStatus.APPROVED);
    }
    
    List<EmployeeRequest> getEmployeeRequests(RequestStatus status){
    	return requestService.getEmployeeRequests(status);
    }
    
    List<Request> getRequests(RequestStatus status){
    	return requestService.getRequests(status);
    }
    
    void setRequestStatus(Request r, RequestStatus s) {
        requestService.setStatus(r, s);
    }
    
//Enrollment
    
    List<Enrollment> getTeacherCourseEnrollments(Teacher teacher, Course course){
    	return enrollmentService.getTeacherCourseEnrollments(teacher, course, EnrollmentStatus.ACTIVE);
    }
    
    List<Enrollment> getStudentEnrollments(Student student){
    	return enrollmentService.getStudentEnrollments(student);
    }
    
    void assignStudentToSection(Student st, Section sec) {
        enrollmentService.assign(st, sec);
    }

    void withdrawFromCourse(Student st, Course c) {
        enrollmentService.withdraw(st, c);
    }

//Academic
    
    Transcript getStudentTranscript(Student student) {
        return academicService.getTranscript(student);
    }
    
    Report createReport() {
    	
    }
    
    void putStudentMarkForSection(Teacher teacher, Student student, Section section, Mark mark) {
        academicService.putMark(teacher, student, section, mark);
    }

    void rateTeacher(Student student, Teacher teacher, Course course, double score) {
        academicService.rateTeacher(student, teacher, course, score);
    }
    
    void exportReport() {
    	
    }

//Course Management

    Course createCourse(String code, String name, String desc, School school, int credits) {
        return courseService.createCourse(code, name, desc, school, credits);
    }
    
    Course getCourseInfo(Course course) {
    	return courseService.getCourse(course);
    }
    
    Section createSection(Course course, Semester semester) {
    	return courseService.createSection(course, semester);
    }
    
    Section getSectionInfo(Section section) {
    	return courseService.getSection(section);
    }
    
    Lesson createLesson(LessonType type, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
    	return courseService.createLesson(type, day, startTime, endTime);
    }

    List<Course> getTeacherCourses(Teacher teacher){
    	return courseService.getCourses(teacher);
    }
    
    List<Section> getTeacherSections(Teacher teacher){
    	return courseService.getSections(teacher);
    }
    
    void assignInstructorToCourse(Course course, Teacher teacher) {
        courseService.addInstructor(course, teacher);
    }

    void assignTeacherToSection(Section section, Teacher teacher) {
        courseService.addTeacher(section, teacher);
    }
    
    void updateCourseInfo(Course course, String description) {
    	courseService.updateCourse(course, description);
    }
    
    void updateCourseInfo(Course course, String name, String description) {
    	courseService.updateCourse(course, name, description);
    }
    
    void dropTeacherFromSection(Section section) {
    	courseService.dropTeacher(section);
    }
    
    void dropInstructorFromCourse(Course course,Teacher teacher) {
    	courseService.dropInstructor(course, teacher);
    }
    
    void addLesson(Section sec, Lesson lesson) {
    	courseService.addLesson(sec, lesson);
    }
    
    void dropLesson(Section sec, Lesson lesson) {
    	courseService.dropLesson(sec, lesson);
    }
    
//User
    
    Student createStudent(String name) {
    	return userService.createStudent();
    }
    
    Teacher createTeacher(String name) {
    	return userService.createTeacher();
    }
    
    Employee createEmployee(String name) {
    	return userService.createEmployee();
    }
    
    Manager createManager(String name) {
    	return userService.createManager();
    }
    
    List<Student> getStudents(){
    	return userService.getStudents();
    }
    
    List<Teacher> getTeachers(){
    	return userService.getTeachers();
    }
    
    List<Employee> getEmployees(){
    	return userService.getEmployees();
    }
    
    List<Manager> getManagers(){
    	return userService.getManagers();
    }
    
    List<Student> getFilteredStudents(){
    	
    }
    
    List<Teacher> getFilteredTeachers(){
    	
    }
    
    List<Employee> getFilteredEmployees(){
    	
    }
    
    List<Manager> getFilteredManagers(){
    	
    }
}	