package com.ervacon.springbank.sampledata;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.ervacon.springbank.domain.AccountNumber;
import com.ervacon.springbank.domain.AccountService;
import com.ervacon.springbank.domain.Address;
import com.ervacon.springbank.domain.Beneficiary;
import com.ervacon.springbank.domain.Client;
import com.ervacon.springbank.domain.ClientService;
import com.ervacon.springbank.domain.Payment;
import com.ervacon.springbank.domain.PaymentProcessingEngine;
import com.ervacon.springbank.user.UserService;

public class SampleDataMother {
	
	public long CLIENT_ID;
	public AccountNumber ACCOUNT_NUMBER_1; 
	public AccountNumber ACCOUNT_NUMBER_2;
	public String USER_ID;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PaymentProcessingEngine engine;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService;

	@PostConstruct
	public void initSampleData() throws Exception {
		Client erwin = clientService.createClient(
				new Client("Erwin", "Vervaet", new Address("Schoolstraat", "41", "3360", "Bierbeek")));
		CLIENT_ID = erwin.getId();
		
		ACCOUNT_NUMBER_1 = accountService.openAccount(CLIENT_ID);
		engine.deposit(Payment.deposit(
				new BigDecimal(10000.0d), "Initial amount", accountService.getAccount(ACCOUNT_NUMBER_1)));
		ACCOUNT_NUMBER_2 = accountService.openAccount(CLIENT_ID);
		engine.deposit(Payment.deposit(
				new BigDecimal(2000.0d), "Initial amount", accountService.getAccount(ACCOUNT_NUMBER_2)));
		
		accountService.addBeneficiary(CLIENT_ID, new Beneficiary("Bieke", "ING-123"));
		accountService.addBeneficiary(CLIENT_ID,
				new Beneficiary("Accountant", new Address("Nieuwstraat", "12/4", "1000", "Brussel"), "ABN-339"));
		
		USER_ID = "erwinv";
		userService.registerUser(USER_ID, "foobar", erwin.getId());
	}
}
