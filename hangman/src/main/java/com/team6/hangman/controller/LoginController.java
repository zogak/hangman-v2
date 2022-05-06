package com.team6.hangman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team6.hangman.entity.Users;
import com.team6.hangman.service.UserService;


@RestController
public class LoginController {
	
	private final UserService userService;
	
	@Autowired
	public LoginController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping(value = "/log-in", consumes = "application/json")
	public String logIn(@RequestBody Users u) {
		
		System.out.println("Received ID: " + u.getId());
		if(!userService.verifyId(u))
			return "errorpage";
		if(!userService.verifyPw(u))
			return "errorpage";
		
		return "loginsuccess";
	}
	
	@PostMapping(value = "/sign-in", consumes = "application/json")
	public void create(@RequestBody Users u) {
		
		System.out.println("New sign in: " + u.getId());
		userService.signIn(u);
	}
	

}
