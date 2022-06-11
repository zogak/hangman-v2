package com.team6.hangman.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team6.hangman.dto.response.ScoreResponseDto;
import com.team6.hangman.service.ScoreService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ScoreController {
	private ScoreService scoreService;
	
	@GetMapping("/score")
	public List <ScoreResponseDto> getLeaderBoard(){
		return scoreService.getScoreList();
	}
	
//	@GetMapping("/score/{userId}")
//	public ScoreResponseDto getScoreByUserId(@PathVariable String userId) {
//		return scoreService.getScoreByUserId(userId);
//	}
}