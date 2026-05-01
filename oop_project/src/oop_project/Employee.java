package oop_project;

public class Employee extends User{
	private DepartmentType department;
	
	Employee(String firstname, String secondname, String password, String username, DepartmentType department) {
		super(firstname, secondname, password, username);
		this.department = department;
	}
	
	DepartmentType getDepartment() {
		return this.department;
	}
}
