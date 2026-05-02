package oop_project;

import java.util.List;

public class Manager extends Employee {
	
	private ManagerType type;
	
	Manager(String firstname, String secondname, String password, String username, ManagerType type){
		super(firstname, secondname, password, username, DepartmentType.MANAGEMENT);
		this.type = type;
	}
	
	public ManagerType getManagerType() {
		return type;
	}
	
	void setManagerType(ManagerType type) {
		this.type = type;
	}
}
