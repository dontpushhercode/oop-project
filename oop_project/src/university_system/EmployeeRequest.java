package university_system;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class EmployeeRequest extends Request {

    /**
     * Default constructor
     */
    EmployeeRequest() {
    }

    EmployeeRequest(Employee fromEmployee, String content) {
        super();
        this.fromEmployee = fromEmployee;
        this.content = content;
    }

    /**
     * 
     */
    private Employee fromEmployee;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Employee signedBy;

    /**
     * 
     */
    boolean isSigned() {
        return this.signedBy != null;
    }

    Employee getEmployee() {
        return this.fromEmployee;
    }

    String getContent() {
        return this.content;
    }

    Employee getSignedBy() {
        return this.signedBy;
    }

    void setSign(Employee employee) {
        this.signedBy = employee;
    }

    @Override
    public String toString() {
        return super.toString() + "From: " + fromEmployee + ", signed: " + isSigned() + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmployeeRequest other = (EmployeeRequest) obj;
        return this.getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}