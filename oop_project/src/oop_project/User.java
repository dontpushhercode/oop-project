package oop_project;

public abstract class User {
	private static int counter = 0;
	
	private int id;
	private String firstname;
	private String surname;
	private String password;
	private String username;
	private ResearchProfile researchProfile;
	
	{
		this.id = ++counter;
	}
	
	User(String firstname, String surname, String password, String username) {
		this.firstname=firstname;
		this.surname = surname;
		this.password=password;
		this.username = username;
		this.researchProfile = null;
	}
	
	int getId() {
		return this.id;
	}
	
	void setResearchProfile(ResearchProfile researchProfile) {
		this.researchProfile = researchProfile;
	}
	
}
