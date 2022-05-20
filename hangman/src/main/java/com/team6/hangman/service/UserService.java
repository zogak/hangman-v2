package com.team6.hangman.service;

import javax.transaction.Transactional;

import com.team6.hangman.entity.Users;
import com.team6.hangman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public String signIn(Users users) {
        userRepository.signIn(users);
        return users.getUser_id();
    }

    public void validateDuplicateId(Users users){
        userRepository.findById(users.getUser_id()).ifPresent(m -> {
            throw new IllegalStateException("Duplicate ID");
        });
    }

    public void validateDuplicateNn(Users users){
        userRepository.findByNn(users.getUser_nickname()).ifPresent(m ->{
            throw new IllegalStateException("Duplicate NickName");
        });
    }
}
