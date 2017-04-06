package evolution.controller.dto;

public class ResponseDto {
	private String name;
	@Override
	public String toString() {
		return "ResponseDto [name=" + name + ", nickName=" + nickName + ", anyDto=" + anyDto + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public AnyDto getAnyDto() {
		return anyDto;
	}
	public void setAnyDto(AnyDto anyDto) {
		this.anyDto = anyDto;
	}
	private String nickName;
	private AnyDto anyDto;
}
