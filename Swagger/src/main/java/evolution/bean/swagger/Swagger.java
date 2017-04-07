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
	

	public String getBasePath() {
		return basePath;
	}

	public Map<String, Dto> getDefinitions() {
		return definitions;
	}

	public String getHost() {
		return host;
	}

	public Info getInfo() {
		return info;
	}

	public Map<String, Http> getPaths() {
		return paths;
	}

	public Object getRequestBodyDto() {
		return requestBodyDto;
	}
	
	public String getRequestMethod() {
		return requestMethod;
	}
	
	public Object getResponseBodyDto() {
		return responseBodyDto;
	}

	public List<String> getSchemes() {
		return schemes;
	}

	public Map<String, Object> getSecurityDefinitions() {
		return securityDefinitions;
	}

	public String getSwagger() {
		return swagger;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public String getUri() {
		return uri;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setDefinitions(Map<String, Dto> definitions) {
		this.definitions = definitions;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public void setPaths(Map<String, Http> paths) {
		this.paths = paths;
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

	public void setSchemes(List<String> schemes) {
		this.schemes = schemes;
	}

	public void setSecurityDefinitions(Map<String, Object> securityDefinitions) {
		this.securityDefinitions = securityDefinitions;
	}

	public void setSwagger(String swagger) {
		this.swagger = swagger;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Swagger [swagger=" + swagger + ", info=" + info + ", host=" + host + ", basePath=" + basePath
				+ ", tags=" + tags + ", schemes=" + schemes + ", paths=" + paths + ", uri=" + uri
				+ ", securityDefinitions=" + securityDefinitions + ", definitions=" + definitions + ", requestMethod="
				+ requestMethod + ", requestBodyDto=" + requestBodyDto + ", responseBodyDto=" + responseBodyDto + "]";
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
