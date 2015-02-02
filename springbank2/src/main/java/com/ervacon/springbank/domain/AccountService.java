package com.ervacon.springbank.domain;

import java.util.List;

public interface AccountService {

	// local account management
	public AccountNumber openAccount(Long clientId);
	public void closeAccount(AccountNumber localAccountNumber);
	public List<Account> getAccounts(Long clientId);

	// accessing accounts by number
	public <A extends Account> A getAccount(AccountNumber accountNumber);
	public <A extends Account> A getAccount(String accountNumber);
	
	// account entries
	public List<Entry> getAccountEntries(AccountNumber accountNumber);
	
	// beneficiary management
	public List<Beneficiary> getBeneficiaries(Long clientId);
	public void addBeneficiary(Long clientId, Beneficiary beneficiary);
	public void removeBeneficiary(Long clientId, AccountNumber beneficiaryAccountNumber);

}
