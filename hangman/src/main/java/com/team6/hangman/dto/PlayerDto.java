package com.team6.hangman.dto;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {
	private WebSocketSession session;
	private Integer turn;
}
