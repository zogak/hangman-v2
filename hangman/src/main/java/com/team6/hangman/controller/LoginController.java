package com.team6.hangman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team6.hangman.service.ClientService;

@RestController
public class LoginController {
	
	private final ClientService clientService;
	
	@Autowired
	public LoginController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@PostMapping("/log-in")
	public String logIn() {
		
	}
	
	
	
	@PostMapping("/sign-in")
	public String create() {
		
	}
	

}
