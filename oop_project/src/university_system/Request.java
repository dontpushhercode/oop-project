package university_system;
import java.io.*;
import java.util.*;

/**
 * 
 */
public class Request {

    /**
     * Default constructor
     */
    public Request() {
    }

    /**
     * 
     */
    private int requestId;

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
    private boolean isApproved;

    /**
     * 
     */
    private Date createdAt;

}