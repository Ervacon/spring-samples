package com.ervacon.springbank.domain;

import java.math.BigDecimal;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
public class PaymentProcessingEngineImpl implements PaymentProcessingEngine {

	private AccountService accountService;
	
	public PaymentProcessingEngineImpl(AccountService accountService) {
		Assert.notNull(accountService, "An account service is required");
		this.accountService = accountService;
	}
	
	public void deposit(Payment payment) throws PaymentProcessingException {
		Account creditAccount = accountService.getAccount(payment.getCreditAccount().getNumber());
		new Deposit(payment.getAmount(), payment.getMessage(), creditAccount).execute();
	}
	
	public void transfer(Payment payment) throws PaymentProcessingException {
		Account debitAccount = accountService.getAccount(payment.getDebitAccount().getNumber());
		Account creditAccount = accountService.getAccount(payment.getCreditAccount().getNumber());
		enforcePaymentPolicy(debitAccount, creditAccount, payment.getAmount());
		new Transfer(payment.getAmount(), payment.getMessage(), debitAccount, creditAccount).execute();
	}
	
	public void withdraw(Payment payment) throws PaymentProcessingException {
		Account debitAccount = accountService.getAccount(payment.getDebitAccount().getNumber());
		new Withdrawal(payment.getAmount(), payment.getMessage(), debitAccount).execute();
	}

	private void enforcePaymentPolicy(Account debitAccount, Account creditAccount, BigDecimal amount)
			throws PaymentProcessingException {
		// in a real system this would probably delegate to strategy
		if (debitAccount.equals(creditAccount)) {
			throw new PaymentProcessingException(
					"error.invalidAccounts",
					"The debit and credit account of a payment cannot be the same");
		}
		if (debitAccount.getBalance().compareTo(amount) < 0) {
			throw new PaymentProcessingException(
					"error.insufficientBalance",
					"The balance of the debit account is insufficient");
		}
	}
}
