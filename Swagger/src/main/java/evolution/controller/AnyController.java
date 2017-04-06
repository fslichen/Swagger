package evolution.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/app")
public class AnyController {
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public Object post(@RequestBody Object dto) {
		return null;
	}
}
