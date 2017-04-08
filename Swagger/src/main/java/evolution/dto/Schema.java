package evolution.dto;

public class Schema {
	private String $ref;
	private String type;
	private Items items;
	private Object additionalProperties;
	
	public Object getAdditionalProperties() {
		return additionalProperties;
	}
	
	public void setAdditionalProperties(Object additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	public String get$ref() {
		return $ref;
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
	
	public void setItems(Items items) {
		this.items = items;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Schema [$ref=" + $ref + ", type=" + type + ", items=" + items + ", additionalProperties="
				+ additionalProperties + "]";
	}
}
