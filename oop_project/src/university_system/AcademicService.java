package university_system;

/**
 * AcademicService is a service class responsible 
 * for academic operations in the university system.
 *
 * This class works with the Database and provides methods for:
 * -getting a student's transcript;
 * -putting marks for students;
 * -allowing students to rate teachers after completing a course.
 */
public class AcademicService {
	/**
     * Database object is used to access and manage university data.
     */
	private final Database db;

    /**
     * Creates an AcademicService object with the given database.
     * @param db the database that stores students, teachers, 
     * sections, enrollments and academic information.
     * 
     */
    AcademicService(Database db) {
    	this.db = db;
    }

    /**
     * Creates and returns a transcript for the given student.
     * 
     * @param st the student whose transcript should be created
     * @return a new Transcript object for the given student
     */
 
    Transcript getTranscript(Student st) {
    	return new Transcript(st);
    }
    
    /**
     * Puts a mark for a student in a specific section.
     * 
     * Before assigning the mark, the method checks:
     * -whether the section exists in the database;
     * -whether the given teacher is responsible for this section;
     * -whether the student is enrolled in this section;
     * -whether the student has not withdrawn from the section;
     * -whether the course has not already been completed.
     * 
     *  If all checks pass, the mark is assigned to the student's enrollment.
     *
     * @param teacher the teacher who wants to put the mark
     * @param student the student who receives the mark
     * @param sec the section where the student is enrolled
     * @param mark the mark to be assigned
     *
     * @throws IllegalStateException if the section does not exist
     * @throws IllegalStateException if the teacher is not assigned to this section
     * @throws IllegalStateException if the student is not enrolled in this section
     * @throws IllegalStateException if the student has withdrawn from the section
     * @throws IllegalStateException if the course is already completed
     */
    

    
    void putMark(Teacher teacher, Student student, Section sec, Mark mark) {
    	Section dbSection = db.getSection(sec.getId());
    	
        if (dbSection == null) {
            throw new IllegalStateException("Section not found!");
        }
    	
	    if (dbSection.getTeacher() == null || !dbSection.getTeacher().equals(teacher)) {
	        throw new IllegalStateException("Cannot put mark on this section!");
	    }

	    Enrollment target = null;
	    for (Enrollment e : db.getFilteredEnrollments(student)) {
	        if (e.getSection().equals(dbSection)) {
	            target = e;
	            break;
	        }
	    }

	    if (target == null) {
	        throw new IllegalStateException("Student not enrolled in this section!");
	    }
	    if (target.getStatus() == EnrollmentStatus.WITHDRAWN) {
	        throw new IllegalStateException("Cannot grade withdrawn student!");
	    }
	    if (target.getStatus() == EnrollmentStatus.COMPLETED) {
	        throw new IllegalStateException("Course already completed!");
	    }
	    target.setMark(mark);
	}

    /**
     * Allows a student to rate a teacher for a specific course.
     *
     * The student can rate the teacher only if:
     * - the teacher exists in the database;
     * - the student was enrolled in the teacher's section;
     * - the student has completed the course.
     *
     * If these conditions are satisfied, the rating score is added
     * to the teacher's rating list.
     *
     * @param student the student who gives the rating
     * @param teacher the teacher who receives the rating
     * @param course the course for which the teacher is rated
     * @param score the rating score given by the student
     *
     * @throws IllegalStateException if the teacher is not found
     * @throws IllegalStateException if the student has not completed the course
     */
    void rateTeacher(Student student, Teacher teacher, Course course, double score) {
    	Teacher t = db.getTeacher(teacher.getId());
    	
    	if (t == null) {
            throw new IllegalStateException("Teacher not found!");
        }
    	
		boolean completed = false;
			for (Enrollment e : db.getFilteredEnrollments(student)) {
				if (e.getSection().getCourse().equals(course) && e.getSection().getTeacher().equals(teacher) && e.getStatus() == EnrollmentStatus.COMPLETED) {
					completed = true;
		            break;
		        }
		    }
		if (!completed) {
		   throw new IllegalStateException("You can only rate after completing the course!");
		}
		t.addRating(score);
    }

}