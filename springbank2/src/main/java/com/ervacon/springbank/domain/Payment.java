package com.ervacon.springbank.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Payment implements Serializable {
	
	private Account debitAccount = new Beneficiary();
	private Account creditAccount = new Beneficiary();
	private BigDecimal amount;
	private String message = "";
	
	public Account getDebitAccount() {
		return debitAccount;
	}
	
	public void setDebitAccount(Account debitAccount) {
		this.debitAccount = debitAccount;
	}
	
	public Account getCreditAccount() {
		return creditAccount;
	}
	
	public void setCreditAccount(Account creditAccount) {
		this.creditAccount = creditAccount;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	// static factory methods
	
	public static Payment deposit(BigDecimal amount, String message, Account creditAccount) {
		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setMessage(message);
		payment.setCreditAccount(creditAccount);
		return payment;
	}

	public static Payment transfer(BigDecimal amount, String message, Account debitAccount, Account creditAccount) {
		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setMessage(message);
		payment.setDebitAccount(debitAccount);
		payment.setCreditAccount(creditAccount);
		return payment;
	}

	public static Payment withdraw(BigDecimal amount, String message, Account debitAccount) {
		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setMessage(message);
		payment.setDebitAccount(debitAccount);
		return payment;
	}
}
