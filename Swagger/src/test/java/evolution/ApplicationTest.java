package evolution;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import evolution.controller.AnyController;
import evolution.controller.dto.RequestDto;
import evolution.dto.Dto;
import evolution.dto.Dtos;
import evolution.util.Sys;

public class ApplicationTest {
	@Test
	public void testRun() {
		Application.run(AnyController.class);
	}
	
	@Test
	public void testDefaultObject() {
		RequestDto dto = new RequestDto();
		dto = (RequestDto) Application.defaultObject(dto);
		Sys.println(dto);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void tes() {
		List list = new LinkedList();// Don't make it List<Object>, and you can plug it into dtos.
		list.add(new Dto());
		Dtos dtos = new Dtos();
		dtos.setDtos(list);
	}
}
