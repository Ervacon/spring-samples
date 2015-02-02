package com.ervacon.springbank.domain;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountRepository implements AccountRepository {

	private long ACCOUNT_NUMBER_COUNTER = 0L;

	private Map<AccountNumber, Account> accounts = new HashMap<AccountNumber, Account>();

	public AccountNumber generateAccountNumber() {
		return new AccountNumber("SpringBank-" + (ACCOUNT_NUMBER_COUNTER++));
	}

	public void store(Account account) {
		accounts.put(account.getNumber(), account);
	}
	
	public void delete(AccountNumber number) {
		accounts.remove(number);
	}
	
	@SuppressWarnings("unchecked")
	public <A extends Account> A findAccount(AccountNumber number) {
		return (A)accounts.get(number);
	}
}
