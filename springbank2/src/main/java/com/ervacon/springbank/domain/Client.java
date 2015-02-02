package com.ervacon.springbank.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Client implements Serializable {
	
	private Long id;
	private String firstName;
	private String lastName;
	private Address address;
	private Set<AccountNumber> accountNumbers = new HashSet<AccountNumber>();
	private Set<AccountNumber> beneficiaryAccountNumbers = new HashSet<AccountNumber>();
	
	Client() {
	}
	
	public Client(String firstName, String lastName, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	public Client(long id, String firstName, String lastName, Address address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getDisplayName() {
		return firstName + " " + lastName;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public Set<AccountNumber> getAccountNumbers() {
		return Collections.unmodifiableSet(accountNumbers);
	}
	
	protected void addAccountNumber(AccountNumber number) {
		accountNumbers.add(number);
	}
	
	protected void removeAccountNumber(AccountNumber number) {
		accountNumbers.remove(number);
	}
	
	public Set<AccountNumber> getBeneficiaryAccountNumbers() {
		return Collections.unmodifiableSet(beneficiaryAccountNumbers);
	}
	
	protected void addBeneficiaryAccountNumbers(AccountNumber number) {
		beneficiaryAccountNumbers.add(number);
	}
	
	protected void removeBeneficiaryAccountNumbers(AccountNumber number) {
		beneficiaryAccountNumbers.remove(number);
	}
}
