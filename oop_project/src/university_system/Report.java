package university_system;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a generated system report.
 *
 * A report contains statistical information
 * about students, courses, or enrollments.
 */
public class Report implements Comparable<Report>, Serializable {
    private static final long serialVersionUID = 1L;
	
	/**
     * Type of the report.
     */
    private ReportType reportType;
    
    /**
     * Date when the report was created.
     */
    private LocalDate createdAt;
    
    /**
     * User who created this report.
     */
    private Manager createdBy;
    
    /**
     * Text content of the report.
     */
    private String content;

    /**
     * Creates a report with specified type and author.
     *
     * The creation date is set automatically to the current date.
     *
     * @param reportType type of report
     * @param createdBy manager who generated the report
     */
    Report(ReportType reportType, Manager createdBy) {
        this.reportType = reportType;
        this.createdBy = createdBy;
        this.createdAt = LocalDate.now();
    }
    
    /**
     * Returns the type of this report.
     *
     * @return report type
     */
    ReportType getReportType() {
        return reportType;
    }

    /**
     * Returns the creation date of this report.
     *
     * @return creation date
     */
    LocalDate getCreatedAt() {
        return createdAt;
    }

    /**
     * Returns the manager who created this report.
     *
     * @return creator of report
     */
    Manager getCreatedBy() {
        return createdBy;
    }

    /**
     * Returns the content of the report.
     *
     * @return report content
     */
    String getContent() {
        return content;
    }

    /**
     * Sets the content of the report.
     *
     * Intended to be used only by service layer.
     *
     * @param content report text content
     */
    void setContent(String content) {
        this.content = content;
    }

    /**
     * Exports report as formatted text.
     *
     * @return string representation of report
     */
    public String export() {
        return "Report Type: " + reportType +
               "\nCreated At: " + createdAt +
               "\nCreated By: " + createdBy +
               "\n\n" + content;
    }
    
    /**
     * Compares reports by creation date (newest first).
     */
    @Override
    public int compareTo(Report other) {
        return other.createdAt.compareTo(this.createdAt);
    }
   
    /**
     * Returns string representation of this report.
     * including id, type and creator and date.
     *
     * @return string representation of request
     */
    @Override
    public String toString() {
        return "Report{" +
                ", type=" + reportType.toString() +
                ", createdAt=" + createdAt.toString() +
                ", createdBy=" + createdBy.toString() +
                '}';
    }
    
}
