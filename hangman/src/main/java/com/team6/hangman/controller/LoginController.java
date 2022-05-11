package com.team6.hangman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team6.hangman.entity.UserForm;
import com.team6.hangman.entity.Users;
import com.team6.hangman.service.UserService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class LoginController {
	
	private final UserService userService;

    @Autowired
    public LoginController(UserService userService){
        super();
        this.userService = userService;
    }

    @PostMapping(value = "/log-in", consumes = "application/json")
    public String logIn(@RequestBody Users u) {

        System.out.println("Received ID: " + u.getUser_id());
        System.out.println("Received PW: " + u.getUser_pw());

        if(!userService.verifyId(u))
            return "errorpage";
        if(!userService.verifyPw(u))
            return "errorpage";

        return "login success";
    }

    @PostMapping(value = "/sign-up", consumes = "application/json")
    public String create(@RequestBody UserForm u) {

        //System.out.println("New user ID: " + u.getId());
        //System.out.println("New user PW: " + u.getPw());
        //System.out.println("New user NickName: " + u.getNickname());
        
        log.info("id : " + u.getId());
        log.info("pw : " + u.getPw());
        log.info("nickname : " + u.getNickname());

        Users newUser = new Users();
        newUser.setUser_id(u.getId());
        newUser.setUser_pw(u.getPw());
        newUser.setUser_nickname(u.getNickname());

        String acceptedID = userService.signIn(newUser);
        return "sign-up success";
    }
	

}
