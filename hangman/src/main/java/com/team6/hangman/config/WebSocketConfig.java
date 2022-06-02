package com.team6.hangman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.team6.hangman.service.GameroomService;
import com.team6.hangman.service.ScoreService;
import com.team6.hangman.service.WebSocketHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer{
	private final ScoreService scoreService;
	private final GameroomService gameroomService;
	
	@Bean
	public WebSocketHandler websocketHandler() {
		return new WebSocketHandler(scoreService, gameroomService);
	}
	//private final WebSocketHandler webSocketHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(websocketHandler(), "/gameroom/*").setAllowedOrigins("*");
	}
}
