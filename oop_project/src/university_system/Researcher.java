package university_system;
import java.io.*;
import java.util.*;

/**
 * Represents a researcher profile with research papers and projects.
 */
public class Researcher implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public Researcher() {
    	this.researchPapers = new ArrayList<ResearchPaper>();
    }

    /**
     * 
     */
    private ResearchProject researchProject;

    /**
     * 
     */
    private ArrayList<ResearchPaper> researchPapers;

    /**
     * 
     */
    private User user;



    /**
     * 
     */
    void getHIndex() {
        // TODO implement here
    }

    /**
     * 
     */
    void printPapers() {
        // TODO implement here
    }

    /**
     * 
     */
    void getUserInfo() {
        // TODO implement here
    }

    /**
     * 
     */
    void setUser() {
        // TODO implement here
    }

    /**
     * 
     */
    void setResearchProject() {
        // TODO implement here
    }

    /**
     * 
     */
    void addResearchPaper() {
        // TODO implement here
    }

}