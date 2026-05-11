package university_system;
/**
 * Represents the processing status of a request
 * in the university system.
 */
public enum RequestStatus {
	
	/**
     * The request is waiting for review.
     */
    PENDING,
    
    /**
     * The request has been approved.
     */
    APPROVED,
    
    /**
     * The request has been rejected.
     */
    REJECTED
}