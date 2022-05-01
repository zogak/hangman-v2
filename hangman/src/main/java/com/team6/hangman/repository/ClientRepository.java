package com.team6.hangman.repository;

import java.util.Optional;

import ch.qos.logback.core.net.server.Client;

public interface ClientRepository {
	
	Client signIn(Client client);
	Optional<Client> verifyId(String id);
	Optional<Client> verifyPw(String pw);
}
