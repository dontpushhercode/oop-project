package oop_project;

public abstract class User {
	private String firstName;
	private String surName;
	private int id;
	private String password;
	private String username;
	private ResearchProfile researchProfile;
	
	public User(String name, int id, String password) {
		this.firstName=name;
		this.id=id;
		this.password=password;
		this.researchProfile = null;
	}
	
	public int getId() {
		return this.id;
	}
	
	public addResearchProfile(ResearchProfile researchProfile) {
		OfficeRegister.getOfficeRegister().assignResearchProfile(researchProfile);
	}
}
