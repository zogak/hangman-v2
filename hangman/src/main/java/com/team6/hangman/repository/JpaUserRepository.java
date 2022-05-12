package com.team6.hangman.repository;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.team6.hangman.entity.Users;

import java.util.Optional;


public class JpaUserRepository implements UserRepository{

	private final EntityManager em; // spring boot가 자동으로 entity manager를 생성, 현재 database와 연결해서

    @Autowired
    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Users signIn(Users users) {
        em.persist(users); // jpa가 insert query 만들어서 db에 넣고 db의 id까지 set해서 넣어준다
        return users;
    }

}
