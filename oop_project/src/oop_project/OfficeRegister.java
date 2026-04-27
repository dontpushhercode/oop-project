package oop_project;

import java.util.HashMap;
import java.util.List;

public class OfficeRegister {
	private Database db;
	private static OfficeRegister officeRegister;
	
	private OfficeRegister() {
		this.db = Database.getDb();
	}
	
	public static OfficeRegister getOfficeRegister() {
	    if(officeRegister == null) {
	    	officeRegister = new OfficeRegister();
	    }
	    return officeRegister;
	}
	
	public void setEnrollment(Teacher teacher, Student st, Section sec, Mark mark) {
	    if (!sec.getTeacher().equals(teacher)) {
	        throw new IllegalArgumentException("Teacher is not assigned to this section");
	    }

	    Enrollment e = new Enrollment(st, sec, mark);
	    this.db.setOrCreateEnrollment(e);
	}
	
	public void setEnrollment(Student st, Section sec, Mark mark) {
	    Enrollment e = new Enrollment(st, sec, mark);
	    this.db.setOrCreateEnrollment(e);
	}
	
	public void setEnrollment(Student st, Section sec) {
		Mark mark = new Mark();
	    setEnrollment(st, sec, mark);
	}
	
	public List<Enrollment> getStudentEnrollments(Student st) {
		return this.db.getFilteredEnrollments(st);
	}
	
	public List<Section> getTeacherSections(Teacher teacher) {
		return this.db.getFilteredSections(teacher);
	}
	
	public List<Enrollment>getSectionInfo(Section sec) {
		return this.db.getFilteredEnrollments(sec);
	}
	
	public void assignTeacherToSection(Section sec, Teacher teacher) {
		sec.setTeacher(teacher);
		this.db.setOrCreateSection(sec);
	}
	
	public void registerStudentForCourse(Student st, Course course) {
		
	}
	
}
