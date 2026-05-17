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
        Student student = new Student(firstname, surname, password, username, year, school);
        db.createStudent(student);
        return student;
    }

    /**
     * Creates a new teacher and saves it to the database.
     */
    Teacher createTeacher(String firstname, String surname, String password, String username, School school, TeacherType teacherType) {
        Teacher teacher = new Teacher(firstname, surname, password, username, school, teacherType);
        db.createUser(teacher);
        return teacher;
    }

    /**
     * Creates a new manager and saves it to the database.
     */
    public Manager createManager(String firstname, String surname, String password, String username, ManagerType type) {
        Manager manager = new Manager(firstname, surname, password, username, type);
        db.createManager(manager);
        return manager;
    }

    /**
     * Creates a new employee and saves it to the database.
     */
    public Employee createEmployee(String firstname, String surname, String password, String username, DepartmentType department) {
        Employee employee = new Employee(firstname, surname, password, username, department);
        db.createEmployee(employee);
        return employee;
    }

    /**
     * Updates the personal info of a user.
     */
    void changeInfo(User user, String firstname, String surname) {
        User u = db.getFilteredUsers(user.getId());
        u.setFirstname(firstname);
        u.setSurname(surname);
    }

    /**
     * Deletes a user from the database.
     */
    public void deleteUser(User user) {
        db.deleteUser(user);
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