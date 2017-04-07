package evolution.bean.swagger.http.httpValue.parameter;

public class Items {
	private String type;
	// TODO Key words like enum and default are not allowed in Java.

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Items [type=" + type + "]";
	}

	public void setType(String type) {
		this.type = type;
	}
}
