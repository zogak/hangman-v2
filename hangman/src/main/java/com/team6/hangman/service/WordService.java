package com.team6.hangman.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team6.hangman.repository.WordRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WordService {
	private WordRepository wordRepository;
	
	public List<String> getWordsToChoose(Integer wordCount){
		List<String> wordList = wordRepository.getWordsByWordCount(wordCount);
		return wordList;
	}
}
