package test;

public abstract class User {
	private String name;
	private int id;
	private String password;
	
	public User(String name, int id, String password) {
		this.name=name;
		this.id=id;
		this.password=password;
	}
	
	public int getId() {
		return this.id;
	}
}
