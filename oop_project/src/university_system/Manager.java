package university_system;
import java.io.*;
import java.util.*;

/**
 *
 * Represents a manager in the university system.
 */
import java.io.Serializable;
public class Manager extends Employee implements Serializable {

    /**
     * Default constructor
     */
    Manager() {
        super();
    }

    /**
     * Constructor that initializes manager with all fields.
     */
    Manager(String firstname, String surname, String password, String username, ManagerType type) {
        super(firstname, surname, password, username, DepartmentType.MANAGEMENT);
        this.type = type;
    }

    /**
     * Type of this manager.
     */
    private ManagerType type;

    /**
     * Returns the type of this manager.
     */
    ManagerType getManagerType() {
        return this.type;
    }

    /**
     * Sets the type of this manager.
     */
    void setManagerType(ManagerType type) {
        this.type = type;
    }

    /**
     * Returns string representation of this manager
     * including manager type information.
     */
    @Override
    public String toString() {
        return super.toString() + "Manager type: " + type + "\n";
    }

    /**
     * Compares this manager to another object by id.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Manager other = (Manager) obj;
        return this.getId() == other.getId();
    }

    /**
     * Returns hash code based on manager id.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}