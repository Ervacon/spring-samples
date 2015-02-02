package com.ervacon.springbank.domain;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateClientRepository implements ClientRepository {
	
	private SessionFactory sessionFactory;
	
	public HibernateClientRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Client createClient(Client clientData) {
		sessionFactory.getCurrentSession().save(clientData);
		return clientData;
	}
	
	public Client getClient(long id) {
		return (Client)sessionFactory.getCurrentSession().get(Client.class, id);
	}
}
