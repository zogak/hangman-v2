package com.team6.hangman.controller;


import com.team6.hangman.entity.UserDAO;
import com.team6.hangman.entity.UserForm;
import com.team6.hangman.entity.Users;
import com.team6.hangman.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SignUpController {

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign-up", consumes = "application/json")
    public String create(@RequestBody UserForm u) {

        log.info("id : " + u.getUser_id());
        log.info("pw : " + u.getUser_pw());
        log.info("nickname : " + u.getUser_nickname());

        Users newUser = new Users();
        newUser.setUser_id(u.getUser_id());
        newUser.setUser_pw(u.getUser_pw());
        newUser.setUser_nickname(u.getUser_nickname());

        String acceptedID = userService.signIn(newUser);

        return "sign-up success";
    }

    @PostMapping(value = "/checkID", consumes = "application/json")
    public String checkId(@RequestBody UserForm u){

        //log.info("id : " + u.getUser_id());

        Users newID = new Users();
        newID.setUser_id(u.getUser_id());

        try{
            userService.validateDuplicateId(newID);
        } catch (IllegalStateException e){
            return "ID is already used";
        }
        return "Valid ID";
    }

    @PostMapping(value = "/checkNN", consumes = "application/json")
    public String checkPw(@RequestBody UserForm u){
        Users newNN = new Users();
        newNN.setUser_nickname(u.getUser_nickname());

        try{
            userService.validateDuplicateNn(newNN);
        } catch (IllegalStateException e){
            return "Nickname already exists";
        }
        return "Valid Nickname";
    }
}
