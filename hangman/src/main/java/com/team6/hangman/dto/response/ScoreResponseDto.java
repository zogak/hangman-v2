package com.team6.hangman.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ScoreResponseDto {
	private String userId;
	private Integer win;
	private Integer lose;
	private Integer score;
}
