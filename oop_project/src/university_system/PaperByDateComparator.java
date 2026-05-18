package university_system;

import java.util.Comparator;

/**
 * Comparator for sorting research papers by publication date.
 */
public class PaperByDateComparator implements Comparator<ResearchPaper> {

    @Override
    public int compare(ResearchPaper p1, ResearchPaper p2) {
        return p1.getPublicationDate().compareTo(p2.getPublicationDate());
    }
}