package university_system;

/**
 *
 * Represents an employee in the university system.
 */
public class Employee extends User {

    /**
     * Default constructor
     */
    Employee() {
        super();
    }

    /**
     * Constructor that initializes employee with all fields.
     */
    Employee(String firstname, String surname, String password, String username, DepartmentType department) {
        super(firstname, surname, password, username);
        this.department = department;
    }

    /**
     * Department where this employee works.
     */
    private DepartmentType department;

    /**
     * Returns the department of this employee.
     */
    DepartmentType getDepartment() {
        return this.department;
    }

    /**
     * Sets the department of this employee.
     */
    void setDepartment(DepartmentType department) {
        this.department = department;
    }

    /**
     * Returns string representation of this employee
     * including department information.
     */
    @Override
    public String toString() {
        return super.toString() + "Department: " + department + "\n";
    }

    /**
     * Compares this employee to another object by id.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return this.getId() == other.getId();
    }

    /**
     * Returns hash code based on employee id.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}