package com.team6.hangman.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import ch.qos.logback.core.net.server.Client;

public class JpaClientRepository implements ClientRepository{

	private final EntityManager em;
	
	@Autowired
	public JpaClientRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Client signIn(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Client> verifyId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Client> verifyPw(String pw) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
