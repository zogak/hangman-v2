package com.team6.hangman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team6.hangman.entity.Word;

public interface WordRepository extends JpaRepository<Word, Long>{
	@Query(value="select word from word where word_count = :wordCount order by rand() limit 3", nativeQuery = true)
	List<String> getWordsByWordCount(@Param(value="wordCount")Integer wordCount);
	
	Word getWordByWord(String word);
}
