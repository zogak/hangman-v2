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

    public String signIn(Users users) {
        userRepository.signIn(users);
        return users.getUser_id();
    }


    public boolean verifyId(Users users) {
        if(!userRepository.verifyId(users.getUser_id()))
            return false;
        return true;
    }

    public boolean verifyPw(Users users) {
        if(!userRepository.verifyPw(users.getUser_pw()))
            return false;
        return true;
    }
}
