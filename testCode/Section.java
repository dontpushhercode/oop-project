package test;

public class Section{
	private Course course;
	private String time;
	private Teacher teacher;
	private Semester semester;
	
	public Section(Course course, String time, Teacher teacher, Semester semester) {
		this.course = course;
		this.time = time; 
		this.teacher = teacher;
		this.semester = semester;
	}
	
	public Teacher getTeacher() {
		return this.teacher;
	}
	
	public Course getCourse() {
		return this.course;
	}
}
