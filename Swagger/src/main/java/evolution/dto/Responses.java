package evolution.dto;

import java.util.Map;

public class Responses {
	private Map<String, Response> responses;

	public Map<String, Response> getResponses() {
		return responses;
	}

	public void setResponses(Map<String, Response> responses) {
		this.responses = responses;
	}

	@Override
	public String toString() {
		return "Responses [responses=" + responses + "]";
	}
}
