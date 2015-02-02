package com.ervacon.springbank.user;

public interface UserRepository {
	
	public void store(User user);
	public User findUser(String userName);

}
