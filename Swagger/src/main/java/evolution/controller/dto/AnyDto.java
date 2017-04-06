package evolution.controller.dto;

public class AnyDto {
	private int id;
	@Override
	public String toString() {
		return "AnyDto [id=" + id + ", zipCode=" + zipCode + ", salary=" + salary + ", rent=" + rent + ", gender="
				+ gender + ", address=" + address + "]";
	}
	private Integer zipCode;
	private Double salary;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private double rent;
	private String gender;
	private String address;
}
