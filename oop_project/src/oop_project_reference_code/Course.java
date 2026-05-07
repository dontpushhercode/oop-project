package oop_project_reference_code;

import java.util.List;

public class Course {
	private static int counter=0;
	
	private int id;
	private String code;
	private String name;
	private String description;
	private School school;
	private List<Teacher> coordinators;
	private int credits;
	
	{
		this.id = ++counter;
	}
	
	Course(String code, String name, String description, School school, int credits) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.school = school;
		this.credits = credits;
		
	}
	
	Course(String code, String name, String description, School school, int credits, List<Teacher> coordinators){
		this.code = code;
		this.name = name;
		this.description = description;
		this.school = school;
		this.coordinators = coordinators;
		this.credits = credits;
	}
	
	String getCode() {
		return this.code;
	}
	
	String getName() {
		return this.name;
	}
	
	List<Teacher> getCoordinators(){
		return this.coordinators;
	}
	
	int getId() {
		return this.id;
	}
	
	void addInstructor(Teacher teacher) {
		this.coordinators.add(teacher);
	}
	
	void dropInstructor(Teacher teacher) {
		this.coordinators.remove(teacher);
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	void setDescription(String description) {
		this.description = description;
	}
	
	void setSchool(School school) {
		this.school = school;
	}
	
	void setCredits(int credits) {
		this.credits = credits;
	}
	
	@Override
	public String toString() {
		return this.code + ": " + this.name+", " + this.description + "\n";
	}
}
