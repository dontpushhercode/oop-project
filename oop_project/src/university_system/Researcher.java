package university_system;
import java.util.*;
import java.io.Serializable;

/**
 * Represents a researcher profile with research papers and projects.
 */
public class Researcher implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public Researcher(User user) {
    	this.researchPapers = new ArrayList<ResearchPaper>();
    	this.researchProjects = new ArrayList<ResearchProject>();
    	setUser(user);
    }

    /**
     * 
     */
    private ArrayList<ResearchProject> researchProjects;

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
    private Researcher supervisor;

    /**
     * 
     */
    int getHIndex() {
    	List<Integer> citations = new ArrayList<>();

        for (ResearchPaper paper : researchPapers) {
            citations.add(paper.getCitationNumber());
        }

        citations.sort(Collections.reverseOrder());

        int h = 0;
        for (int i = 0; i < citations.size(); i++) {
            if (citations.get(i) >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }

    /**
     * 
     */
    void printPapers(Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> sorted = new ArrayList<>(researchPapers);
        sorted.sort(comparator);

        for (ResearchPaper p : sorted) {
            System.out.println(p);
        }
    }

    /**
     * 
     */
    User getUser() {
        return this.user;
    }

    /**
     * 
     */
    void setUser(User user) {
        this.user = user;

        if (user != null && user.getResearchProfile() != this) {
            user.setResearchProfile(this);
        }
    }
    
    /**
     * 
     */
    Researcher getSupervisor() {
        return this.supervisor;
    }
    
    /**
     * 
     */
    void setSupervisor(Researcher supervisor) {
        this.supervisor = supervisor;
    }
    
    /**
     * 
     */
    String getUserInfo() {
        return user == null ? "" : user.toString();
    }

    /**
     * 
     */
    void addResearchProject(ResearchProject project) {
    	if(!researchProjects.contains(project)) {
    		this.researchProjects.add(project);
            project.addMember(this);
    	}
    }

    /**
     * 
     */
    void addResearchPaper(ResearchPaper paper) {
    	if (!researchPapers.contains(paper)) {
    	    researchPapers.add(paper);
    	    paper.addAuthor(this);
    	}
    }
    
    List<ResearchPaper> getResearchPapers(){
    	return new ArrayList<ResearchPaper>(this.researchPapers);
    }
    
    List<ResearchProject> getResearchProjects(){
    	return new ArrayList<ResearchProject>(this.researchProjects);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Researcher)) return false;
        Researcher that = (Researcher) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user);
    }
    
    @Override
    public String toString() {
        return "Researcher{" +
                "papers=" + researchPapers.size() +
                ", projects=" + researchProjects.size() +
                ", hIndex=" + getHIndex() +
                '}';
    }

}
