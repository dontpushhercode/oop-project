package university_system;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public abstract class Request {

    private static int counter = 0;
    private int id;

    {
        this.id = ++counter;
    }

    /**
     * Default constructor
     */
    Request() {
        this.isApproved = false;
        this.status = RequestStatus.PENDING;
        this.createdAt = LocalDate.now();
    }

    /**
     * 
     */
    private boolean isApproved;

    /**
     * 
     */
    private LocalDate createdAt;

    /**
     * 
     */
    private RequestStatus status;

    int getId() {
        return this.id;
    }

    RequestStatus getStatus() {
        return this.status;
    }

    void setStatus(RequestStatus status) {
        this.status = status;
    }

    boolean isApproved() {
        return this.isApproved;
    }

    void setApproved(boolean approved) {
        this.isApproved = approved;
    }

    @Override
    public String toString() {
        return "Request id: " + this.id + ", status: " + status + ", created: " + createdAt + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Request other = (Request) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }
}