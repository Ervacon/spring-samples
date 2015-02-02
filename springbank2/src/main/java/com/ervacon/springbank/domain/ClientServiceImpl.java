package com.ervacon.springbank.domain;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ClientServiceImpl implements ClientService {
	
	private ClientRepository clientRepository;
	
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public Client createClient(Client clientData) {
		return clientRepository.createClient(clientData);
	}
	
	public Client getClient(long id) {
		return clientRepository.getClient(id);
	}
}
