package com.team6.hangman.repository;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.team6.hangman.entity.Users;


public class JpaUserRepository implements UserRepository{

	private final EntityManager em;
	
	@Autowired
	public JpaUserRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Users signIn(Users user) {
		em.persist(user);
		return user;
	}

	@Override
	public Boolean verifyId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verifyPw(String pw) {
		// TODO Auto-generated method stub
		return null;
	}
}
