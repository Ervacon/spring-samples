package com.ervacon.springbank.user;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
	
	private Map<String, User> users = new HashMap<String, User>();
	
	public void store(User user) {
		users.put(user.getUserName(), user);
	}
	
	public User findUser(String userName) {
		return users.get(userName);
	}
}
