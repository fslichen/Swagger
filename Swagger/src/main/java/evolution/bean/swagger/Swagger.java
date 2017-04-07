package evolution.bean.swagger;

import java.util.List;
import java.util.Map;

import evolution.bean.swagger.definitions.Dto;
import evolution.bean.swagger.http.Http;
import evolution.bean.swagger.info.Info;
import evolution.bean.swagger.tag.Tag;

public class Swagger {
	private String swagger;
	private Info info;
	private String host;
	private String basePath;
	private List<Tag> tags;
	private List<String> schemes;
	private Map<String, Http> paths;
	private String uri;
	private Map<String, Object> securityDefinitions;
	private Map<String, Dto> definitions;
	
	@Deprecated
	private String requestMethod;
	@Deprecated
	private Object requestBodyDto;
	@Deprecated
	private Object responseBodyDto;
	

	public Object getRequestBodyDto() {
		return requestBodyDto;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public Object getResponseBodyDto() {
		return responseBodyDto;
	}

	public String getUri() {
		return uri;
	}

	public void setRequestBodyDto(Object requestBodyDto) {
		this.requestBodyDto = requestBodyDto;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	public void setResponseBodyDto(Object responseBodyDto) {
		this.responseBodyDto = responseBodyDto;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	@Override
	public String toString() {
		return "Swagger [uri=" + uri + ", requestMethod=" + requestMethod + ", requestBodyDto=" + requestBodyDto
				+ ", responseBodyDto=" + responseBodyDto + "]";
	}
}
