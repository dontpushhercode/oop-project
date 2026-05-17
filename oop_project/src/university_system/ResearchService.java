package university_system;
import java.util.*;

/**
 * Service class responsible for managing research-related operations.
 *
 * This class works with research profiles, research projects,
 * project members and research papers.
 */
public class ResearchService {
	
	/**
     * Database used to store and access research data.
     */
    private final Database database;


    /**
     * Creates a ResearchService with the given database.
     *
     * @param database the database used by this service
     */
    ResearchService(Database database) {
    	if (database == null) {
    	    throw new IllegalArgumentException("Database cannot be null");
    	}
    	this.database = database;	
    }

    /**
     * Assigns a research profile to a user.
     *
     * A research profile is represented by a Researcher object.
     *
     * @param user the user who becomes a researcher
     * @return the created researcher profile
     * @throws IllegalArgumentException if user is null
     * @throws IllegalStateException if the user already has a research profile
     */
    public void createResearchProfile(User user) {
    	if (user == null) {
             throw new IllegalArgumentException("User cannot be null");
         }
    	Researcher existing = getResearchProfile(user);

        if (existing != null) {
            throw new IllegalStateException("User already has a research profile");
        }

        User u = database.getUser(user.getId());
        
        Researcher researcher = new Researcher(u);
        u.setResearchProfile(researcher);
    }


    /**
     * Removes a research profile from a user.
     *
     * @param user the user whose research profile should be removed
     * @throws IllegalArgumentException if user is null
     * @throws IllegalStateException if the user does not have a research profile
     */
    public void removeResearchProfile(User user) {
    	if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Researcher researcher = getResearchProfile(user);

        if (researcher == null) {
            throw new IllegalStateException("User does not have a research profile");
        }
        
        User u = database.getUser(user.getId());
        u.deleteResearchProfile();
    }

    /**
     * Returns the research profile of a user.
     *
     * @param user the user whose research profile should be returned
     * @return the researcher profile, or null if the user has no profile
     */
    public Researcher getResearchProfile(User user) {
    	 if (user == null) {
             throw new IllegalArgumentException("User cannot be null");
         }
    	 
    	 User u = database.getUser(user.getId());
         return u.getResearchProfile();
    }

    /**
     * Creates a new research project.
     *
     * @param projectName the name of the project
     * @return the created research project
     * @throws IllegalArgumentException if projectName is empty 
     */
    public ResearchProject createProject(String projectName, Researcher researcher) {
    	if (projectName == null || projectName.isBlank()) {
    	    throw new IllegalArgumentException("Project name cannot be empty");
    	}
        ResearchProject project = new ResearchProject(projectName, researcher);
        database.createProject(project);
        return project;
    }

    /**
     * Adds a researcher as a member of a project.
     *
     * @param project the research project
     * @param researcher the researcher to add
     * @throws IllegalArgumentException if project or researcher is null
     * @throws IllegalStateException if researcher is already a member
     */
    public void addMember(ResearchProject project, Researcher researcher) {
    	if (project == null) {
            throw new IllegalArgumentException("Research project cannot be null");
        }

        if (researcher == null) {
            throw new IllegalArgumentException("Researcher cannot be null");
        }

        ResearchProject p = database.getProject(project.getId());
        if (p.getMembers().contains(researcher)) {
            throw new IllegalStateException("Researcher is already a member of this project");
        }
        
        p.addMember(researcher);
    }


    /**
     * Removes a researcher from a project.
     *
     * @param project the research project
     * @param researcher the researcher to remove
     * @throws IllegalArgumentException if project or researcher is null
     * @throws IllegalStateException if researcher is not a member
     */
    public void removeMember(ResearchProject project, Researcher researcher) {
    	if (project == null) {
    		throw new IllegalArgumentException("Research project cannot be null");
        }

    	if (researcher == null) {
    		throw new IllegalArgumentException("Researcher cannot be null");
    	}
    	
    	ResearchProject p = database.getProject(project.getId());
    	if (!p.getMembers().contains(researcher)) {
    		throw new IllegalStateException("Researcher is not a member of this project");
    	}
    	p.deleteMember(researcher);
    }

    /**
     *  Adds a research paper to a project and to the database.
     *
     * @param project the research project
     * @param paper the research paper to add
     * @throws IllegalArgumentException if project or paper is null
     * @throws IllegalStateException if paper already exists in the project
     */
    public void addPaper(ResearchProject project, ResearchPaper paper) {
    	if (project == null) {
            throw new IllegalArgumentException("Research project cannot be null");
        }

        if (paper == null) {
            throw new IllegalArgumentException("Research paper cannot be null");
        }

        ResearchProject p = database.getProject(project.getId());
        if (p.getPapers().contains(paper)) {
            throw new IllegalStateException("Paper already exists in this project");
        }
        p.addPaper(paper);
        database.createPaper(paper);
    }

    /**
     * Returns research papers related to the project.
     * 
     * @param project the research project
     * @return list of papers
     */
    public List<ResearchPaper> getPapers(ResearchProject project) {
    	if (project == null) {
            throw new IllegalArgumentException("Research project cannot be null");
        }

    	ResearchProject p = database.getProject(project.getId());
        return new ArrayList<>(p.getPapers());
    }

    /**
     * Assigns a supervisor to a researcher.
     *
     * @param researcher the researcher who receives a supervisor
     * @param supervisor the supervisor
     * @throws IllegalArgumentException if researcher or supervisor is null
     * @throws IllegalArgumentException if researcher and supervisor are the same person
     */
    public void assignSupervisor(Student student, Researcher supervisor) {
    	if (student == null) {
    	    throw new IllegalArgumentException("Student cannot be null");
    	}
    	
    	if (student.getResearchProfile() == null) {
            throw new IllegalArgumentException("Student does not have a research profile");
        }

        if (supervisor == null) {
            throw new IllegalArgumentException("Supervisor cannot be null");
        }

        if (student.getResearchProfile().equals(supervisor)) {
            throw new IllegalArgumentException("Researcher cannot be supervisor of himself/herself");
        }
        
        if(supervisor.getHIndex()<3) {
        	throw new IllegalArgumentException("Supervisor's h-index must be greater than 3!");
        }

        Student st = database.getStudent(student.getId());
        st.setResearchSupervisor(supervisor);
    }

    /**
     * Returns all researchers from the database.
     *
     * @return list of all researchers
     */
    public List<User> getResearchers() {
    	return new ArrayList<>(database.getFilteredUsers());
    }

}

