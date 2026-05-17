package university_system;
import java.io.*;
import java.util.*;

/**
 * employee in university system
 */
public class Employee extends User {
    /**
     * default constructor
     */
    Employee() {
    	super();
    }
    /**
     * constructor with all fields
     */
    Employee(String firstname, String surname, String password, String username, DepartmentType department) {
        super(firstname, surname, password, username);
        this.department = department;
    }
    /**
     * department where this employee works
     */
    private DepartmentType department;
    /**
     * returns the department of this employee
     */
    DepartmentType getDepartment() {
        return this.department;
    }
    /**
     * sets the department of this employee
     */
    void setDepartment(DepartmentType department) {
        this.department = department;
    }
    /**
     * returns string representation of employee
     */
    @Override
    public String toString() {
        return super.toString() + "Department: " + department + "\n";
    }
     /**
     * compares employees by id
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return this.getId() == other.getId();
    }
    /**
     * returns hash code based on id
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}