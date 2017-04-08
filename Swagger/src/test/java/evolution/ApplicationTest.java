package evolution;

import org.junit.Test;

import evolution.controller.AnyController;
import evolution.controller.dto.RequestDto;

public class ApplicationTest {
	@Test
	public void testSwagger() {
		Application.swagger(AnyController.class);
	}
	
	@Test
	public void testDefaultObject() {
		RequestDto dto = new RequestDto();
		dto = (RequestDto) Ref.defaultObject(dto);
		System.out.println(dto);
	}
}
