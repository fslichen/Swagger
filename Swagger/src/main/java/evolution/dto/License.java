package evolution.dto;

public class License {
	private String name;
	private String url;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "License [name=" + name + ", url=" + url + "]";
	}
}
