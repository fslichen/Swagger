package evolution;

import org.junit.Test;

import evolution.controller.AnyController;

public class SwaggerTest {
	@Test
	public void testSwagger() {
		Application.swagger(AnyController.class, 
				"/Users/chenli/Desktop/swagger.yml");
	}
}
