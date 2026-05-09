package university_system;
import java.io.*;
import java.util.*;

/**
 * Represents a manager in the university system.
 */
public class Manager extends Employee {

    /**
     * Default constructor
     */
    public Manager() {
    }

    /**
     * Constructor with all fields
     */
    public Manager(String firstname, String surname, String password, String username, ManagerType type) {
        super(firstname, surname, password, username, DepartmentType.MANAGEMENT);
        this.type = type;
    }

    /**
     * type of this manager
     */
    private ManagerType type;

    /**
     * returns the type of this manager
     */
    public ManagerType getManagerType() {
        return this.type;
    }

    /**
     * sets the type of this manager
     */
    void setManagerType(ManagerType type) {
        this.type = type;
    }

    /**
     * returns string representation of manager
     */
    @Override
    public String toString() {
        return super.toString() + "Manager type: " + type + "\n";
    }

    /**
     * compares managers by id
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Manager other = (Manager) obj;
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