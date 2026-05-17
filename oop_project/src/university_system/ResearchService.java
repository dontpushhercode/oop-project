package university_system;
import java.io.*;
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
    private Database database;


    /**
     * Creates a ResearchService with the given database.
     *
     * @param database the database used by this service
     * @throws IllegalArgumentException if database is null
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
    Researcher assignResearchProfile(User user) {
    	if (user == null) {
             throw new IllegalArgumentException("User cannot be null");
         }
    	Researcher existing = getResearchProfile(user);

        if (existing != null) {
            throw new IllegalStateException("User already has a research profile");
        }

        Researcher researcher = new Researcher(user);

        database.getResearchers().add(researcher);

        return researcher;
    }


    /**
     * Removes a research profile from a user.
     *
     * @param user the user whose research profile should be removed
     * @throws IllegalArgumentException if user is null
     * @throws IllegalStateException if the user does not have a research profile
     */
    void removeResearchProfile(User user) {
    	if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Researcher researcher = getResearchProfile(user);

        if (researcher == null) {
            throw new IllegalStateException("User does not have a research profile");
        }

        database.getResearchers().remove(researcher);
    }

    /**
     * Returns the research profile of a user.
     *
     * @param user the user whose research profile should be returned
     * @return the researcher profile, or null if the user has no profile
     * @throws IllegalArgumentException if user is null
     */
    Researcher getResearchProfile(User user) {
    	 if (user == null) {
             throw new IllegalArgumentException("User cannot be null");
         }

         for (Researcher researcher : database.getResearchers()) {
             if (researcher.getUser().equals(user)) {
                 return researcher;
             }
         }

         return null;
    }

    /**
     *Creates a new research project.
     *
     * @param projectName the name of the project
     * @return the created research project
     * @throws IllegalArgumentException if projectName is empty 
     */
    ResearchProject createProject(String projectName) {
    	if (projectName == null || projectName.isBlank()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }

        ResearchProject project = new ResearchProject(projectName);

        database.getProjects().add(project);

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
    void addMember(ResearchProject project, Researcher researcher) {
    	if (project == null) {
            throw new IllegalArgumentException("Research project cannot be null");
        }

        if (researcher == null) {
            throw new IllegalArgumentException("Researcher cannot be null");
        }

        if (project.getMembers().contains(researcher)) {
            throw new IllegalStateException("Researcher is already a member of this project");
        }

        project.addMember(researcher);
    }


    /**
     * Removes a researcher from a project.
     *
     * @param project the research project
     * @param researcher the researcher to remove
     * @throws IllegalArgumentException if project or researcher is null
     * @throws IllegalStateException if researcher is not a member
     */
    void removeMember(ResearchProject project, Researcher researcher) {
        if (project == null) {
            throw new IllegalArgumentException("Research project cannot be null");
        }

        if (researcher == null) {
            throw new IllegalArgumentException("Researcher cannot be null");
        }

        if (!project.getMembers().contains(researcher)) {
            throw new IllegalStateException("Researcher is not a member of this project");
        }

        project.deleteMember(researcher);
    }

    /**
     *  Adds a research paper to a project and to the database.
     *
     * @param project the research project
     * @param paper the research paper to add
     * @throws IllegalArgumentException if project or paper is null
     * @throws IllegalStateException if paper already exists in the project
     */
    void addPaper(ResearchProject project, ResearchPaper paper) {
    	if (project == null) {
            throw new IllegalArgumentException("Research project cannot be null");
        }

        if (paper == null) {
            throw new IllegalArgumentException("Research paper cannot be null");
        }

        if (project.getPapers().contains(paper)) {
            throw new IllegalStateException("Paper already exists in this project");
        }

        project.addPaper(paper);

        if (!database.getPapers().contains(paper)) {
            database.getPapers().add(paper);
        }
    }

    /**
     * 
     */
    List<ResearchPaper> getPapers(ResearchProject project) {
    	if (project == null) {
            throw new IllegalArgumentException("Research project cannot be null");
        }

        return project.getPapers();
    }

    /**
     * ssigns a supervisor to a researcher.
     *
     * @param researcher the researcher who receives a supervisor
     * @param supervisor the supervisor
     * @throws IllegalArgumentException if researcher or supervisor is null
     * @throws IllegalArgumentException if researcher and supervisor are the same person
     */
    void assignSupervisor(Researcher researcher, Researcher supervisor) {
    	if (researcher == null) {
            throw new IllegalArgumentException("Researcher cannot be null");
        }

        if (supervisor == null) {
            throw new IllegalArgumentException("Supervisor cannot be null");
        }

        if (researcher.equals(supervisor)) {
            throw new IllegalArgumentException("Researcher cannot be supervisor of himself/herself");
        }

        researcher.setSupervisor(supervisor);
    }

    /**
     * Returns all researchers from the database.
     *
     * @return list of all researchers
     */
    List<Researcher> getResearchers() {
    	return database.getResearchers();
    }

    /**
     * Returns researchers filtered by keyword.
     *
     * The method searches by user information.
     *
     * @param keyword the search keyword
     * @return list of filtered researchers
     * @throws IllegalArgumentException if keyword is null
     */
     List<Researcher> getFilteredResearchers(String keyword) {
    	 if (keyword == null) {
             throw new IllegalArgumentException("Keyword cannot be null");
         }

         List<Researcher> result = new ArrayList<>();

         for (Researcher researcher : database.getResearchers()) {
             String info = researcher.getUserInfo();

             if (info != null && info.toLowerCase().contains(keyword.toLowerCase())) {
                 result.add(researcher);
             }
       }
         return result;
     }
}
