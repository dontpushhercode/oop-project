package test;

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
	
	public List<Enrollment> getMarks(Section sec){
		return OfficeRegister.getOfficeRegister().getSectionMarks(sec);
	}
}
