package com.team6.hangman.entity;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Transactional
@Component
public class UserFormDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public UserFormDAO(){
        try {
            String dbURL = "jdbc:mysql://hangman.cj6l9zzxbm8j.ap-northeast-2.rds.amazonaws.com:3306/hangman?characterEncoding=utf8";
            String dbID = "admin";
            String dbPassword = "mseteam6";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean signUpIdCheck(String user_id){
        String SQL = "SELECT user_id FROM users WHERE user_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();

            if(rs.next()){
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }return true;
    }

    public boolean signUpNickNameCheck(String user_nickName){
        String SQL = "SELECT user_nickName FROM users WHERE user_nickName = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user_nickName);
            rs = pstmt.executeQuery();

            if(rs.next()){
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }return true;
    }


}

