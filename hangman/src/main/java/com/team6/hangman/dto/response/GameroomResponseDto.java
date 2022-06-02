package com.team6.hangman.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@AllArgsConstructor
@Getter
@Setter
public class GameroomResponseDto {
	private long gameroomId;
	private String title;
	private Integer wordCount;
	private Integer playerCount;
	private String gameCharacter;
	

}
