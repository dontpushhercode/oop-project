package university_system;
import java.io.*;
import java.util.*;

/**
 * Represents a research project with members and a supervisor.
 */
public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public ResearchProject() {
    }

    /**
     * 
     */
    private ArrayList<ResearchPaper> papers;

    /**
     * 
     */
    private ArrayList<Researcher> members;

    /**
     * 
     */
    private String projectName;

    List<Researcher> getMembers() {
        return this.members == null ? new ArrayList<>() : new ArrayList<>(this.members);
    }


    /**
     * 
     */
    void addPaper() {
        // TODO implement here
    }

    /**
     * 
     */
    void deletePaper() {
        // TODO implement here
    }

    /**
     * 
     */
    void addMember() {
        // TODO implement here
    }

    /**
     * 
     */
    void deleteMember() {
        // TODO implement here
    }

    /**
     * 
     */
    void renameProject() {
        // TODO implement here
    }

}
