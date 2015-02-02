package com.ervacon.springbank.web;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import com.ervacon.springbank.domain.Account;
import com.ervacon.springbank.domain.AccountNumber;
import com.ervacon.springbank.domain.AccountService;

public class PaymentPropertyEditorRegistrar implements PropertyEditorRegistrar {
	
	private AccountService accountService;
	
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void registerCustomEditors(PropertyEditorRegistry registry) {
		// when setting the debit account on the payment, load it
		registry.registerCustomEditor(Account.class, "debitAccount", new PropertyEditorSupport() {
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(accountService.getAccount(new AccountNumber(text)));
			}
		});
	}
}
