package evolution.dto;

public class DtoType {
	private String type;
	private String format;
	private String description;
	
	public String getDescription() {
		return description;
	}
	
	public String getFormat() {
		return format;
	}
	
	public String getType() {
		return type;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "DtoType [type=" + type + ", format=" + format + ", description=" + description + "]";
	}
}
