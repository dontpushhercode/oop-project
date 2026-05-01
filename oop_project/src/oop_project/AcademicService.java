package oop_project;

public class AcademicService {
	
	private final Database db;

	public AcademicService(Database db) {
		this.db = db;
	}
	
	Transcript getTranscript(Student st) {
		return new Transcript(st);
	}
	
	void putMark(Teacher teacher, Student student, Section sec, Mark mark) {
		Section dbSection = db.getSection(sec.getId());
	    if (!dbSection.getTeacher().equals(teacher)) {
	        throw new IllegalStateException("Cannot put mark on this section");
	    }

	    Enrollment target = null;
	    for (Enrollment e : db.getFilteredEnrollments(student)) {
	        if (e.getSection().equals(dbSection)) {
	            target = e;
	            break;
	        }
	    }

	    if (target == null) {
	        throw new IllegalStateException("Student not enrolled in this section");
	    }
	    if (target.getStatus() == EnrollmentStatus.WITHDRAWN) {
	        throw new IllegalStateException("Cannot grade withdrawn student");
	    }
	    if (target.getStatus() == EnrollmentStatus.COMPLETED) {
	        throw new IllegalStateException("Course already completed");
	    }
	    target.setMark(mark);
	}
	
	void rateTeacher(Student student, Teacher teacher, Course course, double score) {
		Teacher t = db.getTeacher(teacher.getId());
		boolean completed = false;
			for (Enrollment e : db.getFilteredEnrollments(student)) {
				if (e.getSection().getCourse().equals(course) && e.getSection().getTeacher().equals(teacher) && e.getStatus() == EnrollmentStatus.COMPLETED) {
					completed = true;
		            break;
		        }
		    }
		if (!completed) {
		   throw new IllegalStateException("You can only rate after completing the course");
		}
		t.addRating(score);
	}
}
