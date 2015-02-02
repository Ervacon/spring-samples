package com.ervacon.springbank.user;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:/service-layer.xml")
public class TransactionSanityTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private UserService userService;
	
	@Test(expected = UserServiceException.class)
	public void testInvokeUserService() {
		userService.login("foo", "bar");
		Assert.fail("Should have thrown an exception");
	}
}
