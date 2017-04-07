package evolution.bean.swagger.http.httpValue;

import java.util.List;

import evolution.bean.swagger.http.httpValue.parameter.Parameter;
import evolution.bean.swagger.http.httpValue.responses.Responses;

public class HttpValue {
	private List<String> tags;
	private String summary;
	private String description;
	private String operationId;
	private List<String> consumes;
	private List<String> produces;
	private List<Parameter> parameters;
	private Responses responses;
	private List<Object> security;
}
