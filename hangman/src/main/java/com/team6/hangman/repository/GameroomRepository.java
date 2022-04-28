package com.team6.hangman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team6.hangman.entity.Gameroom;

public interface GameroomRepository extends JpaRepository<Gameroom, Long>{
	
}
