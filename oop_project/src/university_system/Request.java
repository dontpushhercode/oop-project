package university_system;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * Abstract base class representing a request
 * in the university system.
 */
public abstract class Request {

    /**
     * Auto-incremented counter for generating unique ids.
     */
    private static int counter = 0;

    /**
     * Unique id of this request.
     */
    private int id;

    {
        this.id = ++counter;
    }

    /**
     * Default constructor that sets initial status and creation date.
     */
    Request() {
        this.isApproved = false;
        this.status = RequestStatus.PENDING;
        this.createdAt = LocalDate.now();
    }

    /**
     * Indicates whether this request has been approved.
     */
    private boolean isApproved;

    /**
     * Current status of this request.
     */
    private RequestStatus status;

    /**
     * Date when this request was created.
     */
    private LocalDate createdAt;

    /**
     * Returns the unique id of this request.
     */
    int getId() {
        return this.id;
    }

    /**
     * Returns the current status of this request.
     */
    RequestStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of this request.
     */
    void setStatus(RequestStatus status) {
        this.status = status;
    }

    /**
     * Returns whether this request is approved.
     */
    boolean isApproved() {
        return this.isApproved;
    }

    /**
     * Sets the approval status of this request.
     */
    void setApproved(boolean approved) {
        this.isApproved = approved;
    }

    /**
     * Returns string representation of this request
     * including id, status and creation date.
     */
    @Override
    public String toString() {
        return "Request id: " + this.id + ", status: " + status + ", created: " + createdAt + "\n";
    }

    /**
     * Compares this request to another object by id.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Request other = (Request) obj;
        return this.id == other.id;
    }

    /**
     * Returns hash code based on request id.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }
}