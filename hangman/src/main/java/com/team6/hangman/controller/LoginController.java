package com.team6.hangman.controller;

import com.team6.hangman.entity.UserDAO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team6.hangman.entity.UserForm;
import com.team6.hangman.entity.Users;
import com.team6.hangman.service.UserService;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


@RestController
@Slf4j
public class LoginController {
	
	private final UserDAO userDAO;

    @Autowired
    public LoginController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @PostMapping(value = "/log-in", consumes = "application/json")
    public String logIn(@RequestBody Users u) {

        int verification = userDAO.login(u.getUser_id(), u.getUser_pw());
        System.out.println(verification);
        if (verification == 1)
            return "login success";
        else
            return "login fail";
    }
}
