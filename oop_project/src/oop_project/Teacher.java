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
	
	public double getRating() {
        return ratingCount == 0?0:ratingSum/ratingCount;
    }
	
	public String getSectionInfo(Section sec){
		return OfficeRegister.getOfficeRegister().getSectionInfo(sec).toString();
	}
	
	public List<Section> getSections(){
		return OfficeRegister.getOfficeRegister().getTeacherSections(this);
	}
	
	public List<Course> getManageredCourses(){
		return OfficeRegister.getOfficeRegister().getTeacherCourses(this);
	}
	
	public List<Enrollment> getCourseEnrollments(Course course) {
		return OfficeRegister.getOfficeRegister().getTeacherCourseEnrollments(this, course);
	}
	
	public void putMark(Student student, Section section, Mark mark) {
		OfficeRegister.getOfficeRegister().putStudentMarkForSection(this, student, section, mark);
	}
	
	void addRating(double score) {
        this.ratingSum += score;
        this.ratingCount++;
    }
}
