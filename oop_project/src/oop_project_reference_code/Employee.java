package oop_project_reference_code;

public class Employee extends User{
	private DepartmentType department;
	
	Employee(String firstname, String secondname, String password, String username, DepartmentType department) {
		super(firstname, secondname, password, username);
		this.department = department;
	}
	
	public DepartmentType getDepartment() {
		return this.department;
	}
	
	void setDepartment(DepartmentType department) {
		this.department = department;
	}
}
