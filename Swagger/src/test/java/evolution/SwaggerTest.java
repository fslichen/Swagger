package evolution;

import org.junit.Test;

import evolution.controller.AnyController;
import evolution.dto.DefaultSwagger;

public class SwaggerTest {
	@Test
	public void testSwagger() {
		SwaggerFactory.swagger(AnyController.class, 
				"/Users/chenli/Desktop/swagger.yml", new DefaultSwagger());
	}
}
