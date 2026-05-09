package oop_project_reference_code;

import java.util.List;

public class Student extends User {
	private int year;
	private School school;
	private AcademicDegree degree;
	private User supervisor;
	
	Student(String firstname, String surname, String password, String username, int year, School school) {
		super(firstname, surname, password, username);
		this.year = year;
		this.school = school;
	}
	
	void setSuperVisor(User supervisor){
		this.supervisor = supervisor;
	}
	
	public int getYear() {
		return year;
	}
	
	public School getSchool() {
		return school;
	}
	
	public AcademicDegree getAcademicDegree() {
		return degree;
	}
	
	public User getSuperVisor() {
		return supervisor;
	}
	
	@Override
	public String toString() {
	    return super.toString() + "School: " + school + ", Year: " + year +  "\n";
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Student other = (Student) obj; 
	    return this.getId() == other.getId();
	}

	@Override
	public int hashCode() {
	    return Integer.hashCode(getId());
	}
}
