package com.ervacon.springbank.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ervacon.springbank.domain.Account;
import com.ervacon.springbank.domain.AccountNumber;
import com.ervacon.springbank.domain.AccountService;
import com.ervacon.springbank.domain.Entry;
import com.ervacon.springbank.user.User;

public class BalancesController extends MultiActionController {
	
	private AccountService accountService;
	
	public BalancesController() {
		setCacheSeconds(0);
	}
	
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	/**
	 * Show balances for user accounts.
	 */
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User)request.getSession(false).getAttribute("user");
		List<Account> accounts = accountService.getAccounts(user.getClientId());
		return new ModelAndView("balances", "accounts", accounts);
	}
	
	/**
	 * Show entries for a particular account.
	 */
	public ModelAndView entries(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AccountNumber accountNumber = new AccountNumber(request.getParameter("accountNumber"));
		List<Entry> entries = accountService.getAccountEntries(accountNumber);
		return new ModelAndView("entries", "entries", entries);
	}
}
