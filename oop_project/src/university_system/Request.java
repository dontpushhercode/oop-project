package university_system;
import java.time.LocalDate;

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
     * Date when this request was last updated.
     */
    private LocalDate updatedAt;

    /**
     * Returns the unique id of this request.
     *
     * @return request id
     */
    int getId() {
        return this.id;
    }

    /**
     * Returns the current status of this request.
     *
     * @return request status
     */
    RequestStatus getStatus() {
        return this.status;
    }
    
    /**
     * Returns the request creation date.
     *
     * @return creation date
     */
    LocalDate getCreationDate() {
    	return this.createdAt;
    }
    
    /**
     * Returns the last update date of this request.
     *
     * @return update date (may be null if never updated)
     */
    LocalDate getUpdationDate() {
    	return this.updatedAt;
    }
    
    /**
     * Sets the status of this request.
     *
     * @param status new request status
     */
    void setStatus(RequestStatus status) {
        this.status = status;
        this.updatedAt = LocalDate.now();
    }

    /**
     * Returns whether this request is approved.
     *
     * @return true if approved, false otherwise
     */
    boolean isApproved() {
        return this.isApproved;
    }

    /**
     * Sets the approval status of this request.
     */
    void setApproved() {
        this.isApproved = true;
        this.updatedAt = LocalDate.now();
    }

    /**
     * Returns string representation of this request.
     * including id, status and creation date.
     *
     * @return string representation of request
     */
    @Override
    public String toString() {
        return "Request id: " + this.id + ", status: " + status + ", created: " + createdAt + "\n";
    }

    /**
     * Compares this request to another object by id.
     *
     * @param obj object to compare
     * @return true if objects have the same id
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
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }
}