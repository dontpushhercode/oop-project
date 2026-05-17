package university_system;

/**
 *
 * Represents a request submitted by an employee
 * in the university system.
 */
import java.io.Serializable;
public class EmployeeRequest extends Request implements Serializable {

    /**
     * Default constructor
     */
    EmployeeRequest() {
    }

    /**
     * Constructor that initializes the request with employee and content.
     */
    EmployeeRequest(Employee fromEmployee, String content) {
        super();
        this.fromEmployee = fromEmployee;
        this.content = content;
    }

    /**
     * Employee who submitted this request.
     */
    private Employee fromEmployee;

    /**
     * Text content of this request.
     */
    private String content;

    /**
     * Employee who signed this request.
     */
    private Employee signedBy;

    /**
     * Returns whether this request has been signed.
     */
    boolean isSigned() {
        return this.signedBy != null;
    }

    /**
     * Returns the employee who submitted this request.
     */
    Employee getEmployee() {
        return this.fromEmployee;
    }

    /**
     * Returns the content of this request.
     */
    String getContent() {
        return this.content;
    }

    /**
     * Returns the employee who signed this request.
     */
    Employee getSignedBy() {
        return this.signedBy;
    }

    /**
     * Sets the employee who signs this request.
     */
    void setSign(Employee employee) {
        this.signedBy = employee;
    }

    /**
     * Returns string representation of this employee request
     * including sender and sign status.
     */
    @Override
    public String toString() {
        return super.toString() + "From: " + fromEmployee + ", signed: " + isSigned() + "\n";
    }

    /**
     * Compares this employee request to another object by id.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EmployeeRequest other = (EmployeeRequest) obj;
        return this.getId() == other.getId();
    }

    /**
     * Returns hash code based on request id.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}