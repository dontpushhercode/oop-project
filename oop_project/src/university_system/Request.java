package university_system;
import java.io.*;
import java.util.*;

/**
 * request in uni system
 */
public class Request {

    /** auto-incremented counter for unique ids */
    private static int counter = 0;

    /** unique id of this request */
    private int id;

    {
        this.id = ++counter;
    }

    /**
     * default constructor
     */
    public Request() {
        this.isApproved = false;
        this.createdAt = new Date();
    }

    /**
     * constructor with employee and content
     */
    public Request(Employee fromEmployee, String content) {
        this.fromEmployee = fromEmployee;
        this.content = content;
        this.isApproved = false;
        this.createdAt = new Date();
    }

    /**
     * employee who submitted the request
     */
    private Employee fromEmployee;

    /**
     * text content of the request
     */
    private String content;

    /**
     * employee who signed the request
     */
    private Employee signedBy;

    /**
     * whether the request is approved
     */
    private boolean isApproved;

    /**
     * date when the request was created
     */
    private Date createdAt;

    /**
     * returns the id of this request
     */
    int getId() {
        return this.id;
    }

    /**
     * returns the employee who submitted the request
     */
    Employee getEmployee() {
        return this.fromEmployee;
    }

    /**
     * returns the content of the request
     */
    String getContent() {
        return this.content;
    }

    /**
     * returns whether the request is approved
     */
    boolean isApproved() {
        return this.isApproved;
    }

    /**
     * sets the approval status of the request
     */
    void setApproved(boolean approved) {
        this.isApproved = approved;
    }

    /**
     * sets the employee who signed the request
     */
    void setSign(Employee employee) {
        this.signedBy = employee;
    }

    /**
     * returns string representation of request
     */
    @Override
    public String toString() {
        return "Request id: " + this.id + ", approved: " + isApproved + "\n";
    }

    /**
     * compares requests by id
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Request other = (Request) obj;
        return this.id == other.id;
    }

    /**
     * returns hash code based on id
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }
}