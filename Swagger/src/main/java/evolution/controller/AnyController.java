package evolution.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import evolution.controller.dto.RequestDto;
import evolution.controller.dto.ResponseDto;
import evolution.controller.dto.TheOtherDto;

@RequestMapping(value = "/app")
@RestController
public class AnyController {
	@RequestMapping(value = "/evolution/0", method = RequestMethod.POST)
	public ResponseDto evolution0(@RequestBody RequestDto dto) {
		return null;
	}
	
	@RequestMapping(value = "/evolution/1", method = RequestMethod.PATCH)
	public List<ResponseDto> evolution1(@RequestBody Map<String, RequestDto> dtos) {
		return null;
	}
	
	@RequestMapping(value = "/evolution/2", method = RequestMethod.POST)
	public Map<String, TheOtherDto> evolution2(@RequestBody List<TheOtherDto> dtos) {
		return null;
	}
	
	@RequestMapping(value = "/evolution/3", method = RequestMethod.POST)
	public String evolution3(@RequestBody String requestString) {
		return null;
	}
	
	@RequestMapping(value = "/evolution/4/{name}/{gender}", method = RequestMethod.POST)
	public ResponseDto evolution3(@PathVariable("name") String name,
			@PathVariable("gender") String gender) {
		return null;
	}
}
