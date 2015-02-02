package com.ervacon.springbank.user;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUserRepository implements UserRepository {
	
	private SessionFactory sessionFactory;
	
	public HibernateUserRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void store(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	public User findUser(String userName) {
		return (User)sessionFactory.getCurrentSession()
			.createCriteria(User.class).add(Restrictions.eq("userName", userName)).uniqueResult();
	}
}
