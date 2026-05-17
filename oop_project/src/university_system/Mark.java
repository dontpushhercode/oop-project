package university_system;

/**
 * students marks in course
 */
public class Mark {

    /** auto-incremented counter for unique ids */
    private static int counter = 0;

    /** unique id of this mark */
    private int id;

    {
        this.id = ++counter;
    }

    /**
     * default constructor
     */
    public Mark() {
        this.firstAttestation = 0;
        this.secondAttestation = 0;
        this.finalExam = 0;
    }

    /**
     * score for the first attestation
     */
    private double firstAttestation;

    /**
     * score for the second attestation
     */
    private double secondAttestation;

    /**
     * score for the final exam
     */
    private double finalExam;

    /**
     * returns the sum of all scores
     */
    double getTotalPoints() {
        return firstAttestation + secondAttestation + finalExam;
    }

    /**
     * returns the literal grade based on total points
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
     * GPA is calculated in Transcript, not here
     */
    void getGpa() {
    }

    /**
     * sets the first attestation score
     */
    void setFirstAttestation(double score) {
        this.firstAttestation = score;
    }

    /**
     * sets the second attestation score
     */
    void setSecondAttestation(double score) {
        this.secondAttestation = score;
    }

    /**
     * sets the final exam score
     */
    void setFinalExam(double score) {
        this.finalExam = score;
    }

    /**
     * returns string representation of mark
     */
    @Override
    public String toString() {
        return "Mark id: " + this.id + ", total: " + getTotalPoints() + " (" + getLiteralGrade() + ")" + "\n";
    }

    /**
     * compares marks by id
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mark other = (Mark) obj;
        return this.id == other.id;
    }

    /**
     * returns hash code based on id
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }
}