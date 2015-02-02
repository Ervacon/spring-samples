package com.ervacon.springbank.domain;

import java.io.Serializable;

public class AccountHolder implements Serializable {
	
	private String name;
	
	private Address address;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddress() {
		if (address == null) {
			address = new Address();
		}
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
}
