package oop_project_reference_code;

public class Enrollment {
	private static int counter = 0;
	
	private int id;
	private Student student;
	private Section section;
	private Mark mark;
	private EnrollmentStatus status;
	
	{
		this.id=++counter;
	}
	
	Enrollment(Student st, Section sec) {
		this.student = st;
		this.section = sec;
		this.mark = new Mark();
		this.status = EnrollmentStatus.ACTIVE;
	}
	
	int getId() {
		return this.id;
	}

	Student getStudent() {
		return this.student;
	}
	
	Section getSection() {
		return this.section;
	}
	
	Mark getMark() {
		return this.mark;
	}
	
	EnrollmentStatus getStatus() {
		return this.status;
	}
	
	void completeCourse() {
		this.status = EnrollmentStatus.COMPLETED;
	}
	
	void setMark(Mark mark) {
		this.mark = mark;
	}
	
	void setSection(Section sec) {
		this.section = sec;
	}
	
	void withdraw() {
	    this.status = EnrollmentStatus.WITHDRAWN;
	}
}
