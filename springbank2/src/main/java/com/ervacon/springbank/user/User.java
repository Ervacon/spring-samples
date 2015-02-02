package com.ervacon.springbank.user;

public class User {
	
	private Long id;
	private String userName;
	private String password;
	private long clientId;
	
	User() {
	}
	
	public User(String userName, String password, long clientId) {
		this.userName = userName;
		this.password = password;
		this.clientId = clientId;
	}

	public User(Long id, String userName, String password, long clientId) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.clientId = clientId;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = encrypt(password);
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(encrypt(password));
	}
	
	public long getClientId() {
		return clientId;
	}
	
	// internal helpers
	
	private String encrypt(String password) {
		// a real app would encrypt the password
		return password;
	}
}
