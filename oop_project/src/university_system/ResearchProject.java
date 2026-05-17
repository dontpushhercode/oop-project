package university_system;
import java.util.*;


/**
 * Represents a research project in the university system.
 *
 * A project contains researchers as participants and
 * research papers produced during the project lifecycle.
 */
public class ResearchProject {


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
     * Returns project members.
     */
    List<Researcher> getMembers(){
    	return new ArrayList<>(members);
    }
    
    /**
     * Returns project papers.
     */
    List<ResearchPaper> getPapers(){
    	return new ArrayList<>(papers);
    }
    
    /**
     * Returns project name.
     */
    public String getProjectName() {
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