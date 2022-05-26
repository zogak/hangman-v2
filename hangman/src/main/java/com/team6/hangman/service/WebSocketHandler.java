package com.team6.hangman.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;
import org.json.simple.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team6.hangman.dto.TupleInfo;
import com.team6.hangman.dto.request.GameplayDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketHandler extends TextWebSocketHandler{
	private HashMap<WebSocketSession, TupleInfo<Integer, String>> players = new HashMap<>(); //key:session, value:<gameroomId, nickName>
	private HashMap<WebSocketSession, WebSocketSession> matching = new HashMap<>(); //key : me, value : counterpart
	private String[] anonymousNickname = {"player1", "player2"};
	private HashMap<String, String> targetWord = new HashMap<>(); //key : session id, value : target word
	private HashMap<WebSocketSession, Integer> turn = new HashMap<>(); //key : session, value : dice num
	
	//private GameplayManager gameManager;
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		//give nick name to the entered players
		
		String[] uriArray = session.getUri().toString().split("/");
		Integer gameroomId = Integer.valueOf(uriArray[4]);
		log.info("gameroomId: " + gameroomId);
		boolean isPlayer2 = false;
		String myNickname="";
		
		for (WebSocketSession key : players.keySet()) {
			// counterpart already exists
			if(players.get(key).getGameroomId().equals(gameroomId)) {
				myNickname = anonymousNickname[1];
				log.info("myNickname : " + myNickname);
				players.put(session, new TupleInfo(gameroomId, myNickname));
				isPlayer2 = true;
				break;
			}
		}
		if(!isPlayer2) {
			myNickname = anonymousNickname[0];
			log.info("myNickname : " + myNickname);
			players.put(session, new TupleInfo(gameroomId, myNickname));
		}
		
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String receivedMessage = message.getPayload();
		log.info("receivedMessage : ");
		log.info(receivedMessage);
		
		ObjectMapper objectMapper = new ObjectMapper();
		GameplayDto gameplayDto = objectMapper.readValue(receivedMessage, GameplayDto.class);
		Integer gameroomId = gameplayDto.getGameroomId();
		
		if(gameplayDto.getType().equals(GameplayDto.Type.CHECK)) {
			Integer count = 0;
			for(WebSocketSession player : players.keySet()) {
				if(players.get(player).getGameroomId().equals(gameroomId)) {
					count += 1;
					if(count.equals(2)) {
						break;
					}
				}
			}
			
			if(count.equals(2)) {
				for(WebSocketSession player : players.keySet()) {
					if(players.get(player).getGameroomId().equals(gameroomId)) {
						player.sendMessage(new TextMessage("Two players are in the gameroom. Start the game!"));
					}
				}
			}
		}
		
		else if (gameplayDto.getType().equals(GameplayDto.Type.START)) {			
			WebSocketSession counterpart = getCounterpartSession(session, gameroomId);
			
			//counterpart already did the START
			if(matching.containsKey(counterpart)) {
				//save me-counterpart info
				matching.put(session, counterpart);
				
				for (WebSocketSession player : players.keySet()) {
					if (players.get(player).getGameroomId().equals(gameroomId)) {
						player.sendMessage(new TextMessage("Players are ready. Choose the word!"));
					}
				}
			}
			//save me-counterpart info
			matching.put(session, counterpart);
					
		}
		else if (gameplayDto.getType().equals(GameplayDto.Type.WORD)) {
			//String counterpart = gameplayDto.getCounterpart();
			String counterpart = matching.get(session).getId();
			String wordForCounterpart = gameplayDto.getWordForCounterpart();
			
			targetWord.put(counterpart, wordForCounterpart);
			
			//TODO 둘 다 던졌을 때만 리턴
			if (targetWord.containsKey(session.getId().toString())) {
				for (WebSocketSession player : players.keySet()) {
					if (players.get(player).getGameroomId().equals(gameroomId)) {
						player.sendMessage(new TextMessage("Word matching is finished!"));
					}
				}
			}
			
		}
		else if (gameplayDto.getType().equals(GameplayDto.Type.TURN)){
			Integer myDice = gameplayDto.getDiceNumber();
			WebSocketSession counterpart = matching.get(session);
			Integer order = 0; //0:me first, 1:draw 2:you first
			//info exists in hashmap turn
			if (turn.containsKey(counterpart)) {
				Integer counterpartDice = turn.get(counterpart);
				
				//compare
				if (myDice < counterpartDice) {
					order = 2;
					String winner = players.get(counterpart).getNickName();
					for (WebSocketSession player : players.keySet()) {
						if (players.get(player).getGameroomId().equals(gameroomId)) {
							player.sendMessage(new TextMessage(winner + " goes first"));
						}
					}
				}
				else if(myDice.equals(counterpartDice)) {
					order = 1;
					
					for (WebSocketSession player : players.keySet()) {
						if (players.get(player).getGameroomId().equals(gameroomId)) {
							player.sendMessage(new TextMessage("DRAW. Roll the dice again."));
						}
					}
				}
				else {
					String winner = players.get(session).getNickName();
					for (WebSocketSession player : players.keySet()) {
						if (players.get(player).getGameroomId().equals(gameroomId)) {
							player.sendMessage(new TextMessage(winner + " goes first"));
						}
					}
				}
			//no info in hashmap turn	
			}
			else {
				turn.put(session, myDice);
			}
		}
		
		else if(gameplayDto.getType().equals(GameplayDto.Type.PLAY)) {
			Boolean isCorrect = gameplayDto.getIsCorrect();
			if (isCorrect) {
				for (WebSocketSession player : players.keySet()) {
					if (players.get(player).getGameroomId().equals(gameroomId)) {
						player.sendMessage(new TextMessage("1"));
					}
				}
			}
			else {
				for (WebSocketSession player : players.keySet()) {
					if (players.get(player).getGameroomId().equals(gameroomId)) {
						player.sendMessage(new TextMessage("-1"));
					}
				}
			}
		}

		else if(gameplayDto.getType().equals(GameplayDto.Type.EMOJI)) {
			Integer emoji = gameplayDto.getEmoji();
			
			for (WebSocketSession player : players.keySet()) {
				if (players.get(player).getGameroomId().equals(gameroomId))
					player.sendMessage(new TextMessage(emoji.toString()));
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		log.info("Player left");
		players.remove(session);
		matching.remove(session);
		targetWord.remove(session.getId());
		turn.remove(session);
	}
	
	public WebSocketSession getCounterpartSession(WebSocketSession mySession, Integer gameroomId) {
		WebSocketSession counterpart = null;
		
		for (Map.Entry<WebSocketSession, TupleInfo<Integer, String>> entry : players.entrySet()) {
			WebSocketSession key = entry.getKey();
			Integer value1 = entry.getValue().getGameroomId();
			String value2 = entry.getValue().getNickName();
			
			if(value1.equals(gameroomId)) {
				if(!key.equals(mySession)) {
					counterpart = key;
				}
			}
		}
		return counterpart;
	}

}
