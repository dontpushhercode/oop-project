package university_system;

/**
 * Represents a literal (letter) grade used for student assessment.
 *
 * Each grade corresponds to a standard academic grading scale
 * from highest (A) to failing (F).
 */
public enum LiteralGrade {

    /** Excellent performance. */
    A,

    /** Excellent performance with minor deduction. */
    A_MINUS,

    /** Very good performance. */
    B_PLUS,

    /** Good performance. */
    B,

    /** Good performance with minor issues. */
    B_MINUS,

    /** Above average performance. */
    C_PLUS,

    /** Average performance. */
    C,

    /** Average performance with minor deficiencies. */
    C_MINUS,

    /** Below average performance. */
    D_PLUS,

    /** Poor performance, minimal passing level. */
    D,

    /** Very poor performance, barely passing. */
    D_MINUS,

    /** Failing grade. */
    F
}