package oop_project;

public enum LiteralGrade {
	A("A"),
	A_MINUS("A-"),
	B_PLUS("B+"),
	B("B"),
	B_MINUS("B-"),
	C_PLUS("C+"),
	C("C"),
	C_MINUS("C-"),
	D_PLUS("D+"),
	D("D"),
	D_MINUS("D-"),
	F("F");  
	
	public final String display;
	
	private LiteralGrade(String display) {
		this.display = display;
	}
	
	@Override
	public String toString() {
		return this.display;
	}
}
