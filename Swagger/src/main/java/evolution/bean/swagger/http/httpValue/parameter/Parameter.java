package evolution.bean.swagger.http.httpValue.parameter;

import evolution.bean.swagger.http.httpValue.parameter.schema.Schema;

public class Parameter {
	private String in;
	private String name;
	private String description;
	private Boolean required;
	private Schema schema;
	private String type;
	private Items items;
	private String collectionFormat;
	private String format;
	private Integer maximum;
	private Integer minimum;
}
