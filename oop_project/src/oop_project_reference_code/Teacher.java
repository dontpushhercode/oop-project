package oop_project_reference_code;

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
	
	@Override
	public String toString() {
	    return super.toString() + "School: " + school + ", Type: " + teacherType + "\n";
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Teacher other = (Teacher) obj; 
	    return this.getId() == other.getId();
	}

	@Override
	public int hashCode() {
	    return Integer.hashCode(getId());
	}
}
