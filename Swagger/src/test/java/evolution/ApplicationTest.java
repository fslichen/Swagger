package evolution;

import org.junit.Test;

import evolution.controller.AnyController;

public class ApplicationTest {
	@Test
	public void test() {
		Application.run(AnyController.class);
	}
}
