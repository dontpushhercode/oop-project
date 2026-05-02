package oop_project;

import java.util.List;

public class Teacher extends Employee {
	private School school;
	private TeacherType teacherType;
	
	private double ratingSum = 0;
    private int ratingCount = 0;
	
	Teacher(String firstname, String secondname, String password, String username, School school, TeacherType teacherType) {
		super(firstname, secondname, password, username, DepartmentType.EDUCATION);
		this.school = school;
		this.teacherType = teacherType;
	}
	
	public School getSchool() {
		return school;
	}
	
	public TeacherType getTeacherType() {
		return teacherType;
	}
	
	public double getRating() {
        return ratingCount == 0?0:ratingSum/ratingCount;
    }
	
	void addRating(double score) {
        this.ratingSum += score;
        this.ratingCount++;
    }
}
