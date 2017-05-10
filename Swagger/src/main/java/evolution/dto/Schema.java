package evolution.dto;

import java.util.Map;

public class Schema {
	private String $ref;
	private String type;
	private Items items;
	private AdditionalProperties additionalProperties;
	private Map<String, Property> properties;// Plug in properties when using examples.
	
	public Map<String, Property> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Property> properties) {
		this.properties = properties;
	}

	public String get$ref() {
		return $ref;
	}
	
	public void set$ref(String $ref) {
		this.$ref = $ref;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Items getItems() {
		return items;
	}
	
	public void setItems(Items items) {
		this.items = items;
	}
	
	public AdditionalProperties getAdditionalProperties() {
		return additionalProperties;
	}
	
	public void setAdditionalProperties(AdditionalProperties additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "Schema [$ref=" + $ref + ", type=" + type + ", items=" + items + ", additionalProperties="
				+ additionalProperties + ", properties=" + properties + "]";
	}
}
