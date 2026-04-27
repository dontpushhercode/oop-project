package oop_project;

import java.util.List;

public class Section{
	private Course course;
	private List<Lesson> lessons;
	private Teacher teacher;
	private Semester semester;
	
	public Section(Course course, List<Lesson> lessons, Teacher teacher, Semester semester) {
		this.course = course;
		this.lessons = lessons;
		this.teacher = teacher;
		this.semester = semester;
	}
	
	public Section(Course course, List<Lesson> lessons, Semester semester) {
		this.course = course;
		this.lessons = lessons;
		this.teacher = null;
		this.semester = semester;
	}
	
	public Teacher getTeacher() {
		return this.teacher;
	}
	
	public Course getCourse() {
		return this.course;
	}
	
	void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
