package com.team6.hangman.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team6.hangman.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Long>{
	Optional<Score> findByUserId(String userId);
	List<Score> findAllByOrderByScoreDesc();
}