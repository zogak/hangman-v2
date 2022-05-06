package com.team6.hangman.service;

import javax.transaction.Transactional;

import com.team6.hangman.entity.Users;
import com.team6.hangman.repository.UserRepository;

@Transactional
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public String signIn(Users user) {
		userRepository.signIn(user);
		return user.getId();
	}
	
	
	public boolean verifyId(Users user) {
		if(!userRepository.verifyId(user.getId()))
			return false;
		return true;
	}
	
	public boolean verifyPw(Users user) {
		if(!userRepository.verifyPw(user.getPw()))
			return false;
		return true;
	}
}
