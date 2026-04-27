package oop_project;

import java.util.List;

public class Teacher extends User {
	private School school;
	private TeacherType teacherType;
	
	public Teacher(String name, int id, String password, School school, TeacherType teacherType) {
		super(name, id, password);
		this.school = school;
		this.teacherType = teacherType;
	}
	
	public List<Section> getSections(){
		return OfficeRegister.getOfficeRegister().getTeacherSections(this);
	}
	
	public List<Enrollment> getSectionInfo(Section sec){
		return OfficeRegister.getOfficeRegister().getSectionInfo(sec);
	}
	
	public void putMark(Student st, Section sec, Mark mark) {
		OfficeRegister.getOfficeRegister().setEnrollment(this, st, sec, mark);
	}
}
