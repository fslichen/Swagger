package evolution.controller.dto;

import java.util.List;

public class RequestDto {
	private String name;
	private AnyDto anyDto;
	private List<AnotherDto> anotherDtos;
	
	public List<AnotherDto> getAnotherDtos() {
		return anotherDtos;
	}

	public void setAnotherDtos(List<AnotherDto> anotherDtos) {
		this.anotherDtos = anotherDtos;
	}

	

	@Override
	public String toString() {
		return "RequestDto [name=" + name + ", anyDto=" + anyDto + ", anotherDtos=" + anotherDtos + "]";
	}

	public AnyDto getAnyDto() {
		return anyDto;
	}

	public void setAnyDto(AnyDto anyDto) {
		this.anyDto = anyDto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
