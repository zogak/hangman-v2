package com.team6.hangman.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameplayDto {
	public enum Type {
		ENTER, PLAY
	}
	private Type type;
	//private Integer gameroomId;
	private String sender;
	private Boolean isWord;
	private String letter;
}
