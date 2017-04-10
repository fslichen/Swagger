package evolution.controller.dto;

import java.util.List;
import java.util.Map;

public class AnyDto {
	private int id;
	private Integer zipCode;
	private double rent;
	private Double salary;
	private String gender;
	private List<String> addresses;
	private AnotherDto anotherDto;
	private List<AnotherDto> anotherDtoList;
	private Map<String, TheOtherDto> theOtherDtoMap;
	
	public List<String> getAddresses() {
		return addresses;
	}

	public AnotherDto getAnotherDto() {
		return anotherDto;
	}
	
	public List<AnotherDto> getAnotherDtoList() {
		return anotherDtoList;
	}
	
	public String getGender() {
		return gender;
	}
	
	public int getId() {
		return id;
	}
	
	public double getRent() {
		return rent;
	}
	
	public Double getSalary() {
		return salary;
	}
	
	public Integer getZipCode() {
		return zipCode;
	}
	
	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}
	
	public void setAnotherDto(AnotherDto anotherDto) {
		this.anotherDto = anotherDto;
	}

	public void setAnotherDtoList(List<AnotherDto> anotherDtoList) {
		this.anotherDtoList = anotherDtoList;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "AnyDto [id=" + id + ", zipCode=" + zipCode + ", rent=" + rent + ", salary=" + salary + ", gender="
				+ gender + ", addresses=" + addresses + ", anotherDto=" + anotherDto + ", anotherDtoList="
				+ anotherDtoList + ", theOtherDtoMap=" + theOtherDtoMap + "]";
	}
}
