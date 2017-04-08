package evolution;

import org.junit.Test;

import evolution.controller.AnyController;
import evolution.controller.dto.RequestDto;
import evolution.util.Sys;

public class ApplicationTest {
	@Test
	public void testRun() {
		Application.run(AnyController.class);
	}
	
	@Test
	public void testDefaultObject() {
		RequestDto dto = new RequestDto();
		dto = (RequestDto) Ref.defaultObject(dto);
		Sys.println(dto);
	}
}
