package com.ervacon.springbank;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringConfigTest {

	@Test
	public void testConfig() {
		new ClassPathXmlApplicationContext("/service-layer.xml");
	}

}
