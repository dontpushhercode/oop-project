package university_system;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * Represents a research paper published by a researcher.
 *
 * Stores bibliographic information such as title,
 * author, publication year, citation count, and DOI.
 */
public class ResearchPaper implements Serializable {
    private static final long serialVersionUID = 1L;
	
	/**
	 * Creates a research paper with bibliographic information.
	 *
	 * @param title paper title
	 * @param author paper author
	 * @param publicationYear publication year
	 */
    public ResearchPaper(String title, List<Researcher> authors, LocalDate publicationDate, int pages, String journal) {
    	this.id = ++counter;
    	
    	this.title = title;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.pages = pages;
        this.journal = journal;

        generateDoi();
    }

    /**
     * Auto-incremented counter for generating unique ids.
     */
    private static int counter = 0;
    
    /**
     * Unique identifier of this research paper.
     */
    private int id;

    /**
     * Title of the research paper.
     */
    private String title;

    /**
     * Author of the research paper.
     */
    private List<Researcher> authors;

    /**
     * Publication date of the research paper.
     */
    private LocalDate publicationDate;

    /**
     * Number of citations received by the paper.
     */
    private int citationNumber;

    /**
     * Digital Object Identifier (DOI) of the paper.
     */
    private String doi;
    
    /**
     * Number of pages of the research paper.
     */
    private int pages;
    
    /**
     * Name of the research journal.
     */
    private String journal;

    /**
     * Generates a unique DOI for the research paper.
     */
    private void generateDoi() {
        this.doi = "10.UNI." + publicationDate + "." + id;
    }
    
    /**
     * Increases the citation count of the paper by one.
     */
    void addCitation() {
        this.citationNumber++;
    }
    
    /**
     * Checks whether the specified researcher is an author of this paper.
     *
     * @param researcher the researcher to check
     * @return true if the researcher is listed among the authors, false otherwise
     */
    boolean hasAuthor(Researcher researcher) {
        return authors.contains(researcher);
    }

    /**
     * Returns the number of citations of the paper.
     *
     * @return citation count
     */
	int getCitationNumber() {
		return citationNumber;
	}

	/**
     * Returns the title of the paper.
     *
     * @return paper title
     */
	String getTitle() {
		return title;
	}

	/**
     * Returns the unique identifier of the paper.
     *
     * @return paper id
     */
	int getId() {
		return id;
	}

    /**
     * Keeps generated paper ids unique after deserialization.
     */
    static void syncCounter(int maxId) {
        counter = Math.max(counter, maxId);
    }
	
	/**
	 * Returns research journal name.
	 * 
	 * @return journal name
	 */
	String getJournal() {
		return this.journal;
	}

	/**
     * Returns the author of the paper.
     *
     * @return paper author
     */
	List<Researcher> getAuthors() {
		return new ArrayList<>(authors);
	}

	/**
     * Returns the publication date of the paper.
     *
     * @return publication date
     */
	LocalDate getPublicationDate() {
		return publicationDate;
	}
	
	/**
	 * Returns the number of pages of the paper.
	 * 
	 * @return pages
	 */
	int getPages() {
		return this.pages;
	}
	
	 /**
     * Returns the DOI of the paper.
     *
     * @return DOI string
     */
	String getDoi() {
		return doi;
	}
	
	/**
     * Returns string representation of this paper.
     * including title, authors, date, citation number, pages and DOI.
     *
     * @return string representation of paper
     */
	@Override
	public String toString() {
	    return "ResearchPaper{" +
	            "id=" + id +
	            ", title='" + title + '\'' +
	            ", authors=" + authors +
	            ", date=" + publicationDate.toString() +
	            ", citations=" + citationNumber +
	            ", pages=" + pages +
	            ", doi='" + doi + '\'' +
	            '}';
	}

    /**
     * Compares this paper to another object by id.
     *
     * @param obj object to compare
     * @return true if objects have the same id
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ResearchPaper other = (ResearchPaper) obj;
        return this.id == other.id;
    }

    /**
     * Returns hash code based on request id.
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }
    
}
