package evolution.bean.swagger.http.httpValue.parameter.schema;

public class Schema {
	public String get$ref() {
		return $ref;
	}
	public void set$ref(String $ref) {
		this.$ref = $ref;
	}
	@Override
	public String toString() {
		return "Schema [$ref=" + $ref + ", type=" + type + ", items=" + items + "]";
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
	private String $ref;
	private String type;
	private Items items;
}
