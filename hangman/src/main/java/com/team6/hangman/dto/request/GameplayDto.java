package com.team6.hangman.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameplayDto {
	public enum Type {
		CHECK, START, WORD, TURN, PLAY, EMOJI, RESULT
	}
	private Type type;
	private Integer gameroomId;
	
	private String counterpart;
	private String wordForCounterpart;
	private String user_nickname;	// for leaderboard
	private Integer diceNumber;
	private Boolean isCorrect;
	private String winner;	// for leaderboard
	private Integer emoji;
	private String userId;

}
