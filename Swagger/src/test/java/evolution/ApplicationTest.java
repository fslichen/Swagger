package evolution;

import org.junit.Test;

import evolution.controller.dto.RequestDto;

public class ApplicationTest {
	@Test
	public void testIsBasic() {
		System.out.println(Ref.isBasic("Hello World"));
	}
	
	@Test
	public void testDefaultObject() {
		RequestDto dto = new RequestDto();
		dto = (RequestDto) Ref.defaultObject(dto);
		System.out.println(dto);
	}
}
