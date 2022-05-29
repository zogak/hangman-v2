package com.team6.hangman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team6.hangman.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Long>{

}
