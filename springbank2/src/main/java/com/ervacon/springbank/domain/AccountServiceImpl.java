package com.ervacon.springbank.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	private ClientRepository clientRepository;

	public AccountServiceImpl(AccountRepository accountRepository, ClientRepository clientRepository) {
		Assert.notNull(accountRepository, "An account repository is required");
		this.accountRepository = accountRepository;
		Assert.notNull(clientRepository, "A client repository is required");
		this.clientRepository = clientRepository;
	}

	public AccountNumber openAccount(Long clientId) {
		Client client = clientRepository.getClient(clientId);
		AccountNumber newAccountNumber = accountRepository.generateAccountNumber();
		LocalAccount newAccount = new LocalAccount(client, newAccountNumber);
		accountRepository.store(newAccount);
		client.addAccountNumber(newAccountNumber);
		return newAccountNumber;
	}
	
	public void closeAccount(AccountNumber localAccountNumber) {
		LocalAccount account = (LocalAccount)accountRepository.findAccount(localAccountNumber);
		Assert.notNull(account, "Only local accounts can be closed");
		Assert.state(account.empty(),
				"This account cannot be closed, it is not empty");
		account.getClient().removeAccountNumber(account.getNumber());
		accountRepository.delete(localAccountNumber);
	}
	
	public List<Account> getAccounts(Long clientId) {
		Client client = clientRepository.getClient(clientId);
		List<Account> res = new LinkedList<Account>();
		for (AccountNumber number : client.getAccountNumbers()) {
			res.add(getAccount(number));
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public <A extends Account> A getAccount(AccountNumber accountNumber) {
		Account account = accountRepository.findAccount(accountNumber);
		if (account == null) {
			// assume its some unknown beneficiary
			account = new Beneficiary(accountNumber);
		}
		return (A)account;
	}
	
	@SuppressWarnings("unchecked")
	public <A extends Account> A getAccount(String accountNumber) {
		return (A)getAccount(new AccountNumber(accountNumber));
	}
	
	public List<Entry> getAccountEntries(AccountNumber accountNumber) {
		Account account = getAccount(accountNumber);
		if (account instanceof LocalAccount) {
			// we only have history for local accounts
			return ((LocalAccount)account).getHistory();
		}
		else {
			return Collections.emptyList();
		}
	}
	
	public List<Beneficiary> getBeneficiaries(Long clientId){
		Client client = clientRepository.getClient(clientId);
		List<Beneficiary> res = new LinkedList<Beneficiary>();
		for (AccountNumber number : client.getBeneficiaryAccountNumbers()) {
			Beneficiary beneficiary = getAccount(number);
			res.add(beneficiary);
		}
		return res;
	}
	
	public void addBeneficiary(Long clientId, Beneficiary beneficiary) {
		Client client = clientRepository.getClient(clientId);
		client.addBeneficiaryAccountNumbers(beneficiary.getNumber());
		accountRepository.store(beneficiary);
	}
		
	public void removeBeneficiary(Long clientId, AccountNumber beneficiaryAccountNumber) {
		Client client = clientRepository.getClient(clientId);
		client.removeBeneficiaryAccountNumbers(beneficiaryAccountNumber);
		accountRepository.delete(beneficiaryAccountNumber);
	}
}
