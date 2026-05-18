package university_system;
import java.util.*;

/**
 *
 * Service responsible for managing users
 * in the university system.
 */
public class UserService {
	
	private void log(String actor, String action) {
	    db.createLog(new Log(actor, action));
	}

    /**
     * Database instance used for data access.
     */
    private final Database db;

    /**
     * Constructor that initializes the service with a database instance.
     */
    UserService(Database db) {
        this.db = db;
    }

    /**
     * Authenticates a user by username and password.
     * Throws exception if credentials are invalid.
     */
    public User login(String username, String password) throws AuthenticationException {
        for (User u : db.getUsers()) {
            if (u.getUserName().equals(username) && u.checkPassword(password)) {
                u.login();
                return u;
            }
        }
        throw new AuthenticationException();
    }

    /**
     * Logs out a user.
     */
    public void logout(User user) {
        user.logout();
    }

    /**
     * Creates a new student and saves it to the database.
     */
    public Student createStudent(String firstname, String surname, String password, String username, int year, School school) {
        Student student = new Student(firstname, surname, username, password, year, school);
        db.createUser(student);
        
        log(student.getFullName() + " id: "+student.getId(), " created");
        
        db.saveToFile("data.ser");
        return student;
    }


    /**
     * Creates a new teacher and saves it to the database.
     */
    public Teacher createTeacher(String firstname, String surname, String password, String username, School school, TeacherType teacherType) {
        Teacher teacher = new Teacher(firstname, surname, username, password, teacherType, school);
        db.createUser(teacher);
        
        log(teacher.getFullName() + " id: "+teacher.getId(), " created");
        
        db.saveToFile("data.ser");
        return teacher;
    }

    /**
     * Creates a new manager and saves it to the database.
     */
    public Manager createManager(String firstname, String surname, String password, String username, ManagerType type) {
        Manager manager = new Manager(firstname, surname, username, password, type);
        db.createUser(manager);
        
        log(manager.getFullName() + " id: "+manager.getId(), " created");
        
        db.saveToFile("data.ser");
        return manager;
    }

    /**
     * Creates a new employee and saves it to the database.
     */
    public Employee createEmployee(String firstname, String surname, String password, String username, DepartmentType department) {
        Employee employee = new Employee(firstname, surname, username, password, department);
        db.createUser(employee);
        
        log(employee.getFullName() + " id: "+employee.getId(), " created");
        
        db.saveToFile("data.ser");
        return employee;
    }
    
    /**
     * Creates a new admin and saves it to the database.
     */
    public Admin createAdmin(String firstname, String surname, String password, String username) {
        Admin admin = new Admin(firstname, surname, username, password);
        db.createUser(admin);
        
        log(admin.getFullName() + " id: "+admin.getId(), " created");
        
        db.saveToFile("data.ser");
        return admin;
    }

    /**
     * Updates the personal info of a user.
     */
    public void changeInfo(User user, String firstname, String surname) {
        User u = db.getUser(user.getId());
        u.setFirstName(firstname);
        u.setSurName(surname);
        
        log(user.getFullName() + " id: "+user.getId(), " info changed");
        
        db.saveToFile("data.ser");
    }

    /**
     * Deletes a user from the database.
     */
    public void deleteUser(User user) {
        db.getUsers().remove(user);
        
        log(user.getFullName() + " id: "+user.getId(), " deleted");
        
        db.saveToFile("data.ser");
    }

    /**
     * Returns all students in the database.
     */
    public List<Student> getStudents() {
        return db.getStudents();
    }

    /**
     * Returns all teachers in the database.
     */
    public List<Teacher> getTeachers() {
        return db.getTeachers();
    }

    /**
     * Returns all managers in the database.
     */
    public List<Manager> getManagers() {
        return db.getManagers();
    }

    /**
     * Returns all employees in the database.
     */
    public List<Employee> getEmployees() {
        return db.getEmployees();
    }
    
    /**
     * Returns all users in the database.
     */
    public List<User> getUsers(){
    	return this.db.getUsers();
    }
    
    /**
     * Returns user with provided id.
     * 
     * @param id user id
     */
    public User getUser(int id) {
    	return this.db.getUser(id);
    }
}
