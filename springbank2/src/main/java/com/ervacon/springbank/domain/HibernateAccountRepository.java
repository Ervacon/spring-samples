package com.ervacon.springbank.domain;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateAccountRepository implements AccountRepository {

	private long ACCOUNT_NUMBER_COUNTER = 0L;

	private SessionFactory sessionFactory;

	public HibernateAccountRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public AccountNumber generateAccountNumber() {
		// TODO real implementation
		return new AccountNumber("SpringBank-" + (ACCOUNT_NUMBER_COUNTER++));
	}
	
	public void store(Account account) {
		sessionFactory.getCurrentSession().save(account);
	}
	
	public void delete(AccountNumber number) {
		Account account = findAccount(number);
		if (account != null) {
			sessionFactory.getCurrentSession().delete(account);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <A extends Account> A findAccount(AccountNumber number) {
		return (A)sessionFactory.getCurrentSession()
			.createCriteria(Account.class).add(Restrictions.eq("number", number)).uniqueResult();
	}
}
