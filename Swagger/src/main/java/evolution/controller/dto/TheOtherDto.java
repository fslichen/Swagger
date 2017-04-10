package evolution.controller.dto;

public class TheOtherDto {
	private double gpa;
	private boolean isGraduate;
	
	public double getGpa() {
		return gpa;
	}
	
	public boolean isGraduate() {
		return isGraduate;
	}
	
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	public void setGraduate(boolean isGraduate) {
		this.isGraduate = isGraduate;
	}
	
	@Override
	public String toString() {
		return "TheOtherDto [gpa=" + gpa + ", isGraduate=" + isGraduate + "]";
	}
}
