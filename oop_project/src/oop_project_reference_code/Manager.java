package oop_project_reference_code;

import java.util.List;

public class Manager extends Employee {
	
//	private ManagerType type;
	
	Manager(String firstname, String secondname, String password, String username){
		super(firstname, secondname, password, username, DepartmentType.MANAGEMENT);
//		this.type = type;
	}
	
//	public ManagerType getManagerType() {
//		return type;
//	}
//	
//	void setManagerType(ManagerType type) {
//		this.type = type;
//	}
}
