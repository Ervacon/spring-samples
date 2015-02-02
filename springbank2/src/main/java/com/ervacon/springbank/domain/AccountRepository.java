package com.ervacon.springbank.domain;

public interface AccountRepository {
	
	public AccountNumber generateAccountNumber();
	
	public void store(Account account);
	
	public void delete(AccountNumber number);
	
	public <A extends Account> A findAccount(AccountNumber number);
}
