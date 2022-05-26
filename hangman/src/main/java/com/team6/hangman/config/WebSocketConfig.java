package com.team6.hangman.config;

import com.team6.hangman.service.ChatSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.team6.hangman.service.WebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{


	@Bean
	public WebSocketHandler websocketHandler() {
		return new WebSocketHandler();
	}

	@Bean
	public ChatSocketHandler chatSocketHandler(){
		return new ChatSocketHandler();
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(websocketHandler(), "/gameroom/*").setAllowedOrigins("*");
		registry.addHandler(chatSocketHandler(), "/chat");
	}
}
