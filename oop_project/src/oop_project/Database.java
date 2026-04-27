package oop_project;

import java.util.ArrayList;
import java.util.List;

public class Database {
	private static Database db;
	private List<User> users; 
	private List<Course> courses;
	private List<Section> sections;
	private List<Enrollment> enrollments;
	
	private Database() {
		users = new ArrayList<>();
	    courses = new ArrayList<>();
	    sections = new ArrayList<>();
	    enrollments = new ArrayList<>();
	}
	
	public static Database getDb() {
		if(db==null) {
			db = new Database();
		}
		return db;
	}
	
	public List<Enrollment> getFilteredEnrollments(Student st){
		List<Enrollment> filtered = new ArrayList<Enrollment>();
		for(Enrollment e:enrollments) {
			if(e.getStudent().getId()==st.getId()) {
				filtered.add(e);
			}
		}
		return filtered;
	}
	
	public List<Enrollment> getFilteredEnrollments(Section sec){
		List<Enrollment> filtered = new ArrayList<Enrollment>();
		for(Enrollment e:enrollments) {
			if(e.getSection().equals(sec)) {
				filtered.add(e);
			}
		}
		return filtered;
	}
	
	public List<Section> getFilteredSections(Teacher teacher){
		List<Section> filtered = new ArrayList<Section>();
		for(Section s:sections) {
			if(s.getTeacher().getId()==teacher.getId()) {
				filtered.add(s);
			}
		}
		return filtered;
	}
	
	void setOrCreateEnrollment(Enrollment e) {
		for (int i = 0; i < enrollments.size(); i++) {
	        if (enrollments.get(i).equals(e)) {
	            enrollments.set(i, e);
	            return;
	        }
	    }
	    enrollments.add(e);
	}
	
	void setOrCreateSection(Section sec) {
		for (int i = 0; i < sections.size(); i++) {
	        if (sections.get(i).equals(sec)) {
	            sections.set(i, sec);
	            return;
	        }
	    }
	    sections.add(sec);
	}
}
