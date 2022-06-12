package com.team6.hangman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.team6.hangman.dto.response.ScoreResponseDto;
import com.team6.hangman.entity.Score;
import com.team6.hangman.repository.ScoreRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScoreService {
	private ScoreRepository scoreRepository;
	
	public boolean saveResult(String winner, String loser) {
		Optional<Score> winnerFromRepository = scoreRepository.findByUserId(winner);
		if (winnerFromRepository.isEmpty()) { //no data
			Score winnerScore = Score.builder().
					userId(winner).
					win(1).
					lose(0).
					score(1).
					build();
			scoreRepository.save(winnerScore);
		}
		else {
			Integer winCount = winnerFromRepository.get().getWin()+1;
			Integer loseCount = winnerFromRepository.get().getLose();
			
			winnerFromRepository.get().setWin(winCount);
			winnerFromRepository.get().setLose(loseCount);
			winnerFromRepository.get().setScore(winCount-loseCount);
			
			scoreRepository.save(winnerFromRepository.get());
		}
		
		Optional<Score> loserFromRepository = scoreRepository.findByUserId(loser);
		if(loserFromRepository.isEmpty()) {
			Score loserScore = Score.builder().
					userId(loser).
					win(0).
					lose(1).
					score(-1).
					build();
			scoreRepository.save(loserScore);
		}
		else {
			Integer loseCount = loserFromRepository.get().getLose()+1;
			Integer winCount = loserFromRepository.get().getWin();

			loserFromRepository.get().setWin(winCount);
			loserFromRepository.get().setLose(loseCount);
			loserFromRepository.get().setScore(winCount-loseCount);
			scoreRepository.save(loserFromRepository.get());
		}

		return true;
	}
	
	public List<ScoreResponseDto> getScoreList(){
		List<ScoreResponseDto> scoreListResponse = new ArrayList<>();
		List<Score> allScore = scoreRepository.findAllByOrderByScoreDesc();
		for (Score score : allScore) {
			scoreListResponse.add(toScoreResponseDto(score));
		}
		
		return scoreListResponse;
	}
	
	public ScoreResponseDto toScoreResponseDto(Score score) {
		return ScoreResponseDto.builder()
				.userId(score.getUserId())
				.win(score.getWin())
				.lose(score.getLose())
				.score(score.getScore())
				.build();
	}
	
//	public ScoreResponseDto getScoreByUserId(String userId) {
//		return toScoreResponseDto(scoreRepository.findByUserId(userId));
//	}
}
