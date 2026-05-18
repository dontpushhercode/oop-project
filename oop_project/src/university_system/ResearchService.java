package university_system;
import java.time.LocalDate;
import java.util.*;

/**
 * Service class responsible for managing research-related operations.
 *
 * This class works with research profiles, research projects,
 * project members and research papers.
 */
public class ResearchService {
	
	private void log(String actor, String action) {
	    database.createLog(new Log(actor, action));
	}
	
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

        User u = database.getUser(user.getId());

        if (u.getResearchProfile() != null) {
            throw new IllegalStateException("User already has a research profile");
        }

        Researcher researcher = new Researcher(u);

        u.setResearchProfile(researcher);

        database.createResearcher(researcher);
        
        log(user.getFullName(), " created research profile");
        
        database.saveToFile("data.ser");
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
        
        log(user.getFullName(), " removed research profile");
        
        database.saveToFile("data.ser");
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
        ResearchProject project = new ResearchProject(projectName);
        addMember(project, researcher);
        database.createProject(project);
        database.saveToFile("data.ser");
        
        log(researcher.getUser().getFullName(), " created research project: "+projectName);
        
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
        	throw new NotAResearcherException();
        }

        ResearchProject p = database.getProject(project.getId());
        if (p.getMembers().contains(researcher)) {
            throw new IllegalStateException("Researcher is already a member of this project");
        }
        
        researcher.addResearchProject(p);
        
        log(researcher.getUser().getFullName(), " joined research project: "+project.getProjectName());
        database.saveToFile("data.ser");
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
    	
    	log(researcher.getUser().getFullName(), " disjoined research project: "+project.getProjectName());
    	
    	database.saveToFile("data.ser");
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
        
        log(project.getProjectName(), " , paper added: "+paper.getTitle());
        
        database.saveToFile("data.ser");
    }
    
    /**
     * Creates research paper.
     * @param title title of paper
     * @param author author of paper
     * @param publicationDate publication date of paper
     * @param pages number of pages
     * @param journal publication journal
     * @return
     */
    public ResearchPaper createPaper(String title, Researcher author, LocalDate publicationDate, int pages, String journal) {
    	ResearchPaper paper = new ResearchPaper(title, author, publicationDate, pages, journal);
    	author.addResearchPaper(paper);
    	database.createPaper(paper);
    	
    	log(author.getUser().getFullName(), " created research paper: "+paper.getTitle());
    	
    	database.saveToFile("data.ser");
    	return paper;
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
        	throw new LowHIndexException("Supervisor must have h-index >= 3");
        }

        Student st = database.getStudent(student.getId());
        st.setResearchSupervisor(supervisor);
        
        log(supervisor.getUser().getFullName(), " supervising: "+student.getFullName());
        
        database.saveToFile("data.ser");
    }

    /**
     * Returns all researchers from the database.
     *
     * @return list of all researchers
     */
    public List<Researcher> getResearchers() {
    	return database.getResearchers();
    }

    /**
     * Returns research papers authored by researcher.
     * @param researcher
     * @return list of papers
     */
    public List<ResearchPaper> getPapers(Researcher researcher){
    	return database.getFilteredPapers(researcher);
    }
    
    public List<ResearchPaper> getPapers(){
    	return database.getPapers();
    }
    
    /**
     * Returns projects related to this researcher
     * @param researcher
     * @return list of projects
     */
    public List<ResearchProject> getProjects(Researcher researcher){
    	return database.getFilteredProjects(researcher);
    }
    
    /**
     * Returns research project by identificator.
     * @param id research project id
     * @return research project
     */
    public ResearchProject getProject(int id) {
    	return database.getProject(id);
    }
    
    /**
     * Return all research projects in the system.
     * @return list of research projects
     */
    public List<ResearchProject> getProjects(){
    	return database.getProjects();
    }
    
    /**
     * Prints research papers of all researchers in the university.
     *
     * Papers are sorted according to the given comparator.
     *
     * @param comparator comparator used for sorting papers
     * @throws IllegalArgumentException if comparator is null
     */
    void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        List<ResearchPaper> allPapers = new ArrayList<>();

        for (Researcher researcher : database.getResearchers()) {
            allPapers.addAll(researcher.getResearchPapers());
        }

        allPapers.sort(comparator);

        for (ResearchPaper paper : allPapers) {
            System.out.println(paper);
        }
    }
    
    /**
     * Returns the top cited researcher of a given year among all schools.
     *
     * @param year publication year
     * @return researcher with the highest citation count in that year
     */
    Researcher getTopCitedResearcherOfYear(int year) {
        Researcher topResearcher = null;
        int maxCitations = -1;

        for (Researcher researcher : database.getResearchers()) {
            int totalCitations = 0;

            for (ResearchPaper paper : researcher.getResearchPapers()) {
                if (paper.getPublicationDate().getYear() == year) {
                    totalCitations += paper.getCitationNumber();
                }
            }

            if (totalCitations > maxCitations) {
                maxCitations = totalCitations;
                topResearcher = researcher;
            }
        }

        return topResearcher;
    }
}
