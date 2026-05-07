package oop_project_reference_code;

public class Mark {
	private static int counter = 0;
	
	private int id;
	private double firstAttestation;
	private double secondAttestation;
	private double finalExam;
	
	{
		this.id = ++counter;
	}
	
	Mark() {
		this.firstAttestation = 0;
		this.secondAttestation = 0;
		this.finalExam = 0;
	}
	
	public double getTotalPoints() {
		return firstAttestation+secondAttestation+finalExam;
	}
	
	public LiteralGrade getLiteralGrade() {
		double total = getTotalPoints();

        if (total >= 95) return LiteralGrade.A;
        if (total >= 90) return LiteralGrade.A_MINUS;
        if (total >= 85) return LiteralGrade.B_PLUS;
        if (total >= 80) return LiteralGrade.B;
        if (total >= 75) return LiteralGrade.B_MINUS;
        if (total >= 70) return LiteralGrade.C_PLUS;
        if (total >= 65) return LiteralGrade.C;
        if (total >= 60) return LiteralGrade.C_MINUS;
        if (total >= 55) return LiteralGrade.D_PLUS;
        if (total >= 50) return LiteralGrade.D;
        return LiteralGrade.F;
	}
}
