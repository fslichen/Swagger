package evolution.dto;

import java.util.Map;

public class Property {
	private String type;
	private String format;
	private String description;
	private String $ref;
	private Items items;
	private Object example;
	private Map<String, Property> properties;// Properties within Property is allowed.
	
	public Map<String, Property> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Property> properties) {
		this.properties = properties;
	}
	
	public String get$ref() {
		return $ref;
	}

	public String getDescription() {
		return description;
	}

	public Object getExample() {
		return example;
	}

	public String getFormat() {
		return format;
	}

	public Items getItems() {
		return items;
	}
	
	public String getType() {
		return type;
	}
	
	public void set$ref(String $ref) {
		this.$ref = $ref;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setExample(Object example) {
		this.example = example;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Property [type=" + type + ", format=" + format + ", description=" + description + ", $ref=" + $ref
				+ ", items=" + items + ", example=" + example + ", properties=" + properties + "]";
	}
}
