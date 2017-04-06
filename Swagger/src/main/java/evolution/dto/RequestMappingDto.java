package evolution.dto;

public class RequestMappingDto {
	private String uri;
	
	public RequestMappingDto() {
		this.uri = "";
		this.requestMethod = "GET";
	}
	
	@Override
	public String toString() {
		return "RequestMappingDto [uri=" + uri + ", requestMethod=" + requestMethod + "]";
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	private String requestMethod;
}
