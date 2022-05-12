package com.team6.hangman.controller;

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
	
	private final UserService userService;

    @Autowired
    public LoginController(UserService userService){
        super();
        this.userService = userService;
    }

    @PostMapping(value = "/log-in", consumes = "application/json")
    public String logIn(@RequestBody Users u) {

        log.info("id: " + u.getUser_id());
        log.info("pw: " + u.getUser_pw());


        if(Optional.of(userService.findId(u.getUser_id())).isEmpty())
            return "Error";

        if(Optional.of(userService.findPw(u.getUser_pw())).isEmpty())
            return "Error";

        return "login success";
    }

    /*
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

     */
	

}
