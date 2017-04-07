package evolution.bean.swagger.definitions;

import java.util.Map;

public class Dto {
	private String type;
	private Map<String, DtoType> properties;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, DtoType> getProperties() {
		return properties;
	}
	@Override
	public String toString() {
		return "Dto [type=" + type + ", properties=" + properties + "]";
	}
	public void setProperties(Map<String, DtoType> properties) {
		this.properties = properties;
	}
}
