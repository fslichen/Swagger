package evolution.dto;

import java.util.Map;

public class Items {
	private String type;
	private String $ref;
	private Map<String, Property> properties;
	
	public Map<String, Property> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Property> properties) {
		this.properties = properties;
	}

	public String get$ref() {
		return $ref;
	}

	public String getType() {
		return type;
	}

	public void set$ref(String $ref) {
		this.$ref = $ref;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Items [type=" + type + ", $ref=" + $ref + ", properties=" + properties + "]";
	}
}
