package com.ervacon.springbank.domain;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.ervacon.springbank.sampledata.SampleDataMother;

@ContextConfiguration(locations = "/service-layer.xml" )
public class PaymentProcessingEngineTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SampleDataMother sampleDataMother;
	
	@Autowired
	private PaymentProcessingEngine engine;
	
	@Test
	public void testTransfer() throws Exception {
		Payment payment = new Payment();
		payment.setAmount(new BigDecimal(100));
		payment.setDebitAccount(accountService.getAccount(sampleDataMother.ACCOUNT_NUMBER_1));
		payment.setCreditAccount(accountService.getAccount(sampleDataMother.ACCOUNT_NUMBER_2));
		payment.setMessage("Just testing");
		engine.transfer(payment);
	}
	
	@Test
	public void testTransferFailure() {
		Payment payment = new Payment();
		payment.setAmount(new BigDecimal(1000000));
		payment.setDebitAccount(accountService.getAccount(sampleDataMother.ACCOUNT_NUMBER_1));
		payment.setCreditAccount(accountService.getAccount(sampleDataMother.ACCOUNT_NUMBER_2));
		payment.setMessage("Just testing");
		try {
			engine.transfer(payment);
			Assert.fail();
		}
		catch (PaymentProcessingException e) {
			// expected
		}
	}
}
