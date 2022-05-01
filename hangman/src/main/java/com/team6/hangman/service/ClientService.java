package com.team6.hangman.service;

import javax.transaction.Transactional;

import com.team6.hangman.entity.Client;
import com.team6.hangman.repository.ClientRepository;

@Transactional
public class ClientService {
	
	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}
	
	public String signIn(Client client) {
		
		
		
		return client.getId();
	}
	
	
	public void verifyId(Client client) {
		
	}
	
	public void verifyPw(Client client) {
		
	}
	
	
	
	

}
