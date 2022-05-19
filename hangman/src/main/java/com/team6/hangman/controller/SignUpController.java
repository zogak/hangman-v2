package com.team6.hangman.controller;


import com.team6.hangman.entity.UserForm;
import com.team6.hangman.entity.UserFormDAO;
import com.team6.hangman.entity.Users;
import com.team6.hangman.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SignUpController {

    private final UserService userService;
    private final UserFormDAO userFormDAO;

    @Autowired
    public SignUpController(UserService userService, UserFormDAO userFormDAO) {
        this.userService = userService;
        this.userFormDAO = userFormDAO;
    }

    @PostMapping(value = "/sign-up", consumes = "application/json")
    public String create(@RequestBody UserForm u) {

        log.info("id : " + u.getUser_id());
        log.info("pw : " + u.getUser_pw());
        log.info("nickname : " + u.getUser_nickname());

        boolean idVerification = userFormDAO.signUpIdCheck(u.getUser_id());
        boolean nickNameVerification = userFormDAO.signUpNickNameCheck(u.getUser_nickname());

        if(idVerification){
            if(nickNameVerification){
                Users newUser = new Users();
                newUser.setUser_id(u.getUser_id());
                newUser.setUser_pw(u.getUser_pw());
                newUser.setUser_nickname(u.getUser_nickname());

                String acceptedID = userService.signIn(newUser);
                return "sign-up success";
            }
        }
        return "sign-up failed";

    }



}
