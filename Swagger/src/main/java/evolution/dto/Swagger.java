package evolution.dto;

public class Swagger {
	private String uri;
	private String requestMethod;
	
	@Override
	public String toString() {
		return "Swagger [uri=" + uri + ", requestMethod=" + requestMethod + "]";
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
}
