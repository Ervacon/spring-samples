package com.ervacon.springbank.domain;

public interface PaymentProcessingEngine {
	
	public void deposit(Payment payment) throws PaymentProcessingException;
	
	public void transfer(Payment payment) throws PaymentProcessingException;

	public void withdraw(Payment payment) throws PaymentProcessingException;
}
