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
     * Creates a new student and saves it to the database.
     */
    Student createStudent(String firstname, String surname, String password, String username, int year, School school) {
        Student student = new Student(firstname, surname, password, username, year, school);
        db.createStudent(student);
        return student;
    }

    /**
     * Creates a new teacher and saves it to the database.
     */
    Teacher createTeacher(String firstname, String surname, String password, String username, School school, TeacherType teacherType) {
        Teacher teacher = new Teacher(firstname, surname, username, password, teacherType, school);
        db.createTeacher(teacher);
        return teacher;
    }

    /**
     * Creates a new manager and saves it to the database.
     */
    Manager createManager(String firstname, String surname, String password, String username, ManagerType type) {
        Manager manager = new Manager(firstname, surname, password, username, type);
        db.createManager(manager);
        return manager;
    }

    /**
     * Creates a new employee and saves it to the database.
     */
    Employee createEmployee(String firstname, String surname, String password, String username, DepartmentType department) {
        Employee employee = new Employee(firstname, surname, password, username, department);
        db.createEmployee(employee);
        return employee;
    }

    /**
     * Updates the personal info of a user.
     */
    void changeInfo(User user, String firstname, String surname) {
        User u = db.getUser(user.getId());
        u.setFirstName(firstname);
        u.setSurName(surname);
    }

    /**
     * Deletes a user from the database.
     */
    void deleteUser(User user) {
        db.deleteUser(user);
    }

    /**
     * Returns all students in the database.
     */
    List<Student> getStudents() {
        return db.getStudents();
    }

    /**
     * Returns all teachers in the database.
     */
    List<Teacher> getTeachers() {
        return db.getTeachers();
    }

    /**
     * Returns all managers in the database.
     */
    List<Manager> getManagers() {
        return db.getManagers();
    }

    /**
     * Returns all employees in the database.
     */
    List<Employee> getEmployees() {
        return db.getEmployees();
    }
}