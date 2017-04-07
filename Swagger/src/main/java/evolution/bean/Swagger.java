package evolution.bean;

public class Swagger {
	private String uri;
	private String requestMethod;
	private Object requestBodyDto;
	private Object responseBodyDto;
	

	@Override
	public String toString() {
		return "Swagger [uri=" + uri + ", requestMethod=" + requestMethod + ", requestBodyDto=" + requestBodyDto
				+ ", responseBodyDto=" + responseBodyDto + "]";
	}

	public Object getRequestBodyDto() {
		return requestBodyDto;
	}

	public void setRequestBodyDto(Object requestBodyDto) {
		this.requestBodyDto = requestBodyDto;
	}

	public Object getResponseBodyDto() {
		return responseBodyDto;
	}

	public void setResponseBodyDto(Object responseBodyDto) {
		this.responseBodyDto = responseBodyDto;
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
