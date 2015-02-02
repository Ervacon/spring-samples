package com.ervacon.springbank.user;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:/service-layer.xml")
public class UserRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testStoreAndFind() {
		User user = new User("foo", "bar", 10L);
		userRepository.store(user);
		Assert.assertNotNull(userRepository.findUser("foo"));
		Assert.assertNull(userRepository.findUser("boo"));
	}
}
