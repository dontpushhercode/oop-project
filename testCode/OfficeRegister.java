package test;

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
	
	public void assignStudent(Student st, Section sec) {
		Enrollment e = new Enrollment(st, sec);
		this.db.addEnrollment(e);
	}
	
	public List<Enrollment> getStudentEnrollments(Student st) {
		return this.db.getFilteredEnrollments(st);
	}
	
	public void assignTeacherToSection(Section sec, Teacher teacher) {
		
	}
	
	public List<Section> getTeacherSections(Teacher teacher) {
		return this.db.getFilteredSections(teacher);
	}
	
	public List<Enrollment>getSectionMarks(Section sec) {
		return this.db.getFilteredEnrollments(sec);
	}
	
}
