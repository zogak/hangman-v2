package com.team6.hangman.repository;

import com.team6.hangman.entity.Users;


public interface UserRepository {
	
	Users signIn(Users user);
	Boolean verifyId(String id);
	Boolean verifyPw(String pw);
	
}
