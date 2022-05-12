package com.team6.hangman.entity;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ExecutionException;

@Transactional
@Component
public class UserDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public UserDAO(){
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

    public int login(String user_id, String user_pw){
        String SQL = "SELECT user_pw FROM users WHERE user_id = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();

            if(rs.next()){
                if(rs.getString(1).contentEquals(user_pw)){
                    return 1;
                }else{
                    return 0;
                }
            }
            return -1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -2;
    }
}

