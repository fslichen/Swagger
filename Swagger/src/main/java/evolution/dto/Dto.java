package evolution.dto;

import java.util.Map;

public class Dto {
	private String type;
	private Map<String, DtoType> properties;
	
	public Map<String, DtoType> getProperties() {
		return properties;
	}
	
	public String getType() {
		return type;
	}
	
	public void setProperties(Map<String, DtoType> properties) {
		this.properties = properties;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Dto [type=" + type + ", properties=" + properties + "]";
	}
}
