package evolution.controller.dto;


public class AnotherDto {
	private String uri;

	public String getUri() {
		return uri;
	}

	@Override
	public String toString() {
		return "AnotherDto [uri=" + uri + "]";
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
