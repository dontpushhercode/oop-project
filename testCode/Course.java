package test;

public class Course {
	private String code;
	private String name;
	private String description;
	private School school;
	private int credits;
	
	public Course(String name, String description, School school, int credits) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.school = school;
		this.credits = credits;
		
	}
	
	public String getCode() {
		return this.code;
	}
}
