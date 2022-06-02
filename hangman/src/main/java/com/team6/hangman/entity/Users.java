package com.team6.hangman.entity;

import javax.persistence.*;


@Entity
public class Users {
	
	@Id //pk mapping을 알리는 annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String user_id;
    private String user_pw;
    private String user_nickname;


    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_pw() {
        return user_pw;
    }
    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }
    public String getUser_nickname() {
        return user_nickname;
    }
    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

}
