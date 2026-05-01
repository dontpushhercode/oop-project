package oop_project;

import java.util.List;

public class Section{
	private static int counter = 0;
	
	private int id;
	private Course course;
	private List<Lesson> lessons;
	private Teacher teacher;
	private Semester semester;
	
	{
		this.id = ++counter;
	}
	
	Section(Course course, List<Lesson> lessons, Teacher teacher, Semester semester) {
		this.course = course;
		this.lessons = lessons;
		this.teacher = teacher;
		this.semester = semester;
	}
	
	Section(Course course, List<Lesson> lessons, Semester semester) {
		this.course = course;
		this.lessons = lessons;
		this.teacher = null;
		this.semester = semester;
	}
	
	Section(Course course, Semester semester){
		this.course = course;
		this.lessons = null;
		this.teacher = null;
		this.semester = semester;
	}
	
	Teacher getTeacher() {
		return this.teacher;
	}
	
	Course getCourse() {
		return this.course;
	}
	
	List<Lesson> getLessons(){
		return this.lessons;
	}
	
	void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	void setCourse(Course course) {
		this.course = course;
	}
	
	void addLesson(Lesson lesson) {
		this.lessons.add(lesson);
	}
	
	void dropLesson(Lesson lesson) {
		this.lessons.remove(lesson);
	}
	
	void setSemester(Semester semester) {
		this.semester = semester;
	}
	
	int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return "Section of " + this.course.getName() + " course, semester" + this.semester + "\n"; 
	}
}
