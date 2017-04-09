package evolution;

import org.junit.Test;

import evolution.controller.dto.RequestDto;
import evolution.pojo.ListOfStrings;

public class ApplicationTest {
	@Test
	public void testIsBasic() {
		System.out.println(Ref.isBasic("Hello World"));
	}
	
	@Test
	public void testDefaultObject() {
		RequestDto dto = new RequestDto();
		dto = (RequestDto) Ref.defaultObject(RequestDto.class);
		System.out.println(dto);
	}
	
	@Test
	public void testList() {
		Object obj = Ref.defaultObject(ListOfStrings.class);
		System.out.println(obj);
	}
}
