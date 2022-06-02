package com.team6.hangman.dto.request;

import lombok.Getter;

@Getter
public class ScoreRequestDto {
	private String user_id;
	private Integer win;
	private Integer lose;
}
