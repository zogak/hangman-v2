package com.team6.hangman.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team6.hangman.entity.ListResult;
import com.team6.hangman.service.ResponseService;
import com.team6.hangman.service.WordService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WordController {
	private WordService wordService;
	private ResponseService responseService;
	
	@GetMapping("/word/random")
	public ResponseEntity<ListResult<String>> getWordsToChoose(@RequestParam Integer wordCount){
		List<String> randomWords = wordService.getWordsToChoose(wordCount);
		System.out.println(randomWords);
		return new ResponseEntity<>(responseService.getListResult(randomWords), HttpStatus.OK);
	}
}
