package evolution.bean.swagger.http.httpValue.responses.response;

import java.util.Map;

import evolution.bean.swagger.http.httpValue.responses.response.schema.Schema;

public class Response {
	private String description;
	private Schema schema;
	private Map<String, Object> headers;
}
