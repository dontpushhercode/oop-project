package university_system;

import java.io.Serializable;

/**
 *
 * Represents a student's mark in a course
 * in the university system.
 */
public class Mark implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Auto-incremented counter for generating unique ids.
     */
    private static int counter = 0;

    /**
     * Unique id of this mark.
     */
    private int id;

    {
        this.id = ++counter;
    }

    /**
     * Default constructor that initializes all scores to zero.
     */
    public Mark() {
        this.firstAttestation = 0;
        this.secondAttestation = 0;
        this.finalExam = 0;
    }

    /**
     * Returns the mark identifier.
     */
    int getId() {
        return this.id;
    }

    /**
     * Keeps generated mark ids unique after deserialization.
     */
    static void syncCounter(int maxId) {
        counter = Math.max(counter, maxId);
    }

    /**
     * Score for the first attestation.
     */
    private double firstAttestation;

    /**
     * Score for the second attestation.
     */
    private double secondAttestation;

    /**
     * Score for the final exam.
     */
    private double finalExam;

    /**
     * Returns the sum of all scores.
     */
    double getTotalPoints() {
        return firstAttestation + secondAttestation + finalExam;
    }

    /**
     * Returns the literal grade based on total points.
     */
    String getLiteralGrade() {
        double total = getTotalPoints();
        if (total >= 95) return "A";
        if (total >= 90) return "A-";
        if (total >= 85) return "B+";
        if (total >= 80) return "B";
        if (total >= 75) return "B-";
        if (total >= 70) return "C+";
        if (total >= 65) return "C";
        if (total >= 60) return "C-";
        if (total >= 55) return "D+";
        if (total >= 50) return "D";
        return "F";
    }

    /**
    * Returns the GPA for this mark based on total points.
    */
    double getGpa() {
        double total = getTotalPoints();
        if (total >= 95) return 4.0;
        if (total >= 90) return 3.67;
        if (total >= 85) return 3.33;
        if (total >= 80) return 3.0;
        if (total >= 75) return 2.67;
        if (total >= 70) return 2.33;
        if (total >= 65) return 2.0;
        if (total >= 60) return 1.67;
        if (total >= 55) return 1.33;
        if (total >= 50) return 1.0;
        return 0.0;
    }

    /**
     * Sets the first attestation score.
     */
    void setFirstAttestation(double score) {
        this.firstAttestation = score;
    }

    /**
     * Sets the second attestation score.
     */
    void setSecondAttestation(double score) {
        this.secondAttestation = score;
    }

    /**
     * Sets the final exam score.
     */
    void setFinalExam(double score) {
        this.finalExam = score;
    }

    /**
     * Returns string representation of this mark
     * including total points and literal grade.
     */
    @Override
    public String toString() {
        return "Mark id: " + this.id + ", total: " + getTotalPoints() + " (" + getLiteralGrade() + ")" + "\n";
    }

    /**
     * Compares this mark to another object by id.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mark other = (Mark) obj;
        return this.id == other.id;
    }

    /**
     * Returns hash code based on mark id.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }
}
