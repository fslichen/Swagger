package evolution.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import evolution.controller.dto.RequestDto;
import evolution.controller.dto.ResponseDto;

@RequestMapping(value = "/app")
@RestController
public class AnyController {
	@RequestMapping(value = "/evolution/0", method = RequestMethod.POST)
	public ResponseDto evolution0(@RequestBody RequestDto dto) {
		return null;
	}
}
