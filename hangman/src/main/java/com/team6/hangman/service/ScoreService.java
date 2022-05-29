package com.team6.hangman.service;

import org.springframework.stereotype.Service;

import com.team6.hangman.entity.Score;
import com.team6.hangman.repository.ScoreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScoreService {
	private ScoreRepository scoreRepository;
	
	public boolean saveResult(String winner, String loser) {
		Score winnerScore = Score.builder().
				user_id(winner).
				win(1).
				lose(0).
				build();
		
		Score loserScore = Score.builder().
				user_id(loser).
				win(0).
				lose(1).
				build();
		
		scoreRepository.save(winnerScore);
		scoreRepository.save(loserScore);
		return true;
	}
}
