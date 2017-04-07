package evolution.bean.swagger.http.httpValue.parameter.schema;

public class Items {
	private String $ref;

	public String get$ref() {
		return $ref;
	}

	@Override
	public String toString() {
		return "Items [$ref=" + $ref + "]";
	}

	public void set$ref(String $ref) {
		this.$ref = $ref;
	}
}
