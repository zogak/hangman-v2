package com.team6.hangman.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameplayDto {
	public enum Type {
		CHECK, START, WORD, TURN, PLAY, EMOJI
	}
	private Type type;
	private Integer gameroomId;
	
	private String counterpart;
	private String wordForCounterpart;
	private Integer diceNumber;
	private Boolean isCorrect;
	private Integer emoji;
	
	//private Boolean isWord;
	//private String letter;
}
