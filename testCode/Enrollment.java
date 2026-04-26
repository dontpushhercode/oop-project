package test;

public class Enrollment {
	private Student student;
	private Section section;
	private Mark mark;
	
	public Enrollment(Student st, Section sec) {
		this.student = st;
		this.section = sec;
		this.mark = new Mark();
	}

	public Student getStudent() {
		return this.student;
	}
	
	public Section getSection() {
		return this.section;
	}
}
