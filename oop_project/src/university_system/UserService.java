package university_system;
import java.util.*;

/**
 *
 * Service responsible for managing users
 * in the university system.
 */
public class UserService {

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
        return student;
    }


    /**
     * Creates a new teacher and saves it to the database.
     */
    public Teacher createTeacher(String firstname, String surname, String password, String username, School school, TeacherType teacherType) {
        Teacher teacher = new Teacher(firstname, surname, username, password, teacherType, school);
        db.createUser(teacher);
        return teacher;
    }

    /**
     * Creates a new manager and saves it to the database.
     */
    public Manager createManager(String firstname, String surname, String password, String username, ManagerType type) {
        Manager manager = new Manager(firstname, surname, username, password, type);
        db.createUser(manager);
        return manager;
    }

    /**
     * Creates a new employee and saves it to the database.
     */
    public Employee createEmployee(String firstname, String surname, String password, String username, DepartmentType department) {
        Employee employee = new Employee(firstname, surname, username, password, department);
        db.createUser(employee);
        return employee;
    }
    
    /**
     * Creates a new admin and saves it to the database.
     */
    public Admin createAdmin(String firstname, String surname, String password, String username) {
        Admin admin = new Admin(firstname, surname, username, password);
        db.createUser(admin);
        return admin;
    }

    /**
     * Updates the personal info of a user.
     */
    public void changeInfo(User user, String firstname, String surname) {
        User u = db.getUser(user.getId());
        u.setFirstName(firstname);
        u.setSurName(surname);
    }

    /**
     * Deletes a user from the database.
     */
    public void deleteUser(User user) {
        db.getUsers().remove(user);
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
}
