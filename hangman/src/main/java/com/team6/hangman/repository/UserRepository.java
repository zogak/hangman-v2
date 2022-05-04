package com.team6.hangman.repository;

import com.team6.hangman.entity.User;


public interface UserRepository {
	
	User signIn(User user);
	Boolean verifyId(String id);
	Boolean verifyPw(String pw);
	
}
