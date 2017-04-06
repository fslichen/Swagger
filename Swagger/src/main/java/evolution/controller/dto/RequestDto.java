package evolution.controller.dto;

public class RequestDto {
	private String name;
	private AnyDto anyDto;
	
	@Override
	public String toString() {
		return "RequestDto [name=" + name + ", anyDto=" + anyDto + "]";
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
