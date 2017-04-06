package evolution.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import evolution.controller.dto.RequestDto;
import evolution.controller.dto.ResponseDto;

@RequestMapping(value = "/app")
public class AnyController {
	@ResponseBody
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseDto post(@RequestBody RequestDto dto) {
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/patch", method = RequestMethod.PATCH)
	public String patch(@RequestBody String json) {
		return null;
	}
}
