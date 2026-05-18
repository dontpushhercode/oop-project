package university_system;
import java.util.*;
import java.io.Serializable;


/**
 * 
 */
public class ResearchProject implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static void setCounter(int value) {
        counter = value;
    }

	private static int counter = 0;
	
	{
		this.id=++counter;
	}

	/**
	 * Represents a research project in the university system.
	 *
	 */
    public ResearchProject(String projectName, ArrayList<Researcher> members) {
    	this.projectName = projectName;
    	this.members = new ArrayList<Researcher>(members);
    	this.papers = new ArrayList<ResearchPaper>();
    }
    
    /**
     * Creates a research project without initial members.
     *
     * @param projectName name of project
     */
    public ResearchProject(String projectName) {
        this.projectName = projectName;
        this.members = new ArrayList<Researcher>();
        this.papers = new ArrayList<ResearchPaper>();
    }
    
    /**
     * Creates a research project with a single member.
     *
     * @param projectName name of project
     * @param member initial researcher
     */
    public ResearchProject(String projectName, Researcher member) {
    	this.projectName = projectName;
    	this.members = new ArrayList<Researcher>();
    	this.papers = new ArrayList<ResearchPaper>();
    	addMember(member);
    }

    /**
     * 
     */
    private int id;
    
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
    
    /**
     * 
     */
    int getId() {
    	return this.id;
    }


    /**
     * Returns project papers.
     */
    List<ResearchPaper> getPapers(){
    	return new ArrayList<>(papers);
    }
    
    /**
     * Returns project members.
     */
    List<Researcher> getMembers() {
        return new ArrayList<>(members);
    }
    
    /**
     * Returns project name.
     */
    String getProjectName() {
        return projectName;
    }
    
    /**
     * Adds a research paper to the project.
     */
    void addPaper(ResearchPaper paper) {
    	if(papers.contains(paper)) {
    		throw new IllegalArgumentException("Paper already exists in project");
    	}
        this.papers.add(paper);
    }

    /**
     * Removes a research paper from the project.
     */
    void deletePaper(ResearchPaper paper) {
    	if(!papers.contains(paper)) {
    		throw new IllegalArgumentException("Paper not found in project");
    	}
        this.papers.remove(paper);
    }

    /**
     * Adds a researcher to the project.
     */
    void addMember(Researcher member) {
        if(member==null) {
        	throw new IllegalArgumentException("Member must be a researcher");
        }
        if(members.contains(member)) {
        	throw new IllegalArgumentException("Member already exists");
        }
        this.members.add(member);
    }

    /**
     * Removes a researcher from the project.
     */
    void deleteMember(Researcher member) {
        if(!members.contains(member)) {
        	throw new IllegalArgumentException("Member not found");
        }
        if(members.size()==1) {
        	throw new IllegalStateException("Project must have at least one member");
        }
        this.members.remove(member);
    }

    /**
     * Renames the project.
     */
    void renameProject(String name) {
        this.projectName = name;
    }

}
