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


public class WebSocketHandler extends TextWebSocketHandler{
	private HashMap<WebSocketSession, TupleInfo<Integer, String>> players = new HashMap<>(); //key:session, value:<gameroomId, nickName>
	private HashMap<WebSocketSession, WebSocketSession> matching = new HashMap<>(); //key : me, value : counterpart
	private String[] anonymousNickname = {"player1", "player2"};
	private HashMap<String, String> targetWord = new HashMap<>(); //key : session id, value : target word
	private HashMap<WebSocketSession, Integer> turn = new HashMap<>(); //key : session, value : dice num
	
	//private GameplayManager gameManager;
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		
		String[] uriArray = session.getUri().toString().split("/");
		Integer gameroomId = Integer.valueOf(uriArray[4]);
		System.out.println("gameroomId: " + gameroomId);
		boolean isPlayer2 = false;
		String myNickname="";
		
		for (WebSocketSession key : players.keySet()) {
			if(players.get(key).getGameroomId().equals(gameroomId)) {
				myNickname = anonymousNickname[1];
				players.put(session, new TupleInfo(gameroomId, myNickname));
				isPlayer2 = true;
				break;
			}
		}
		if(!isPlayer2) {
			myNickname = anonymousNickname[0];
			players.put(session, new TupleInfo(gameroomId, myNickname));
		}
		System.out.println("myNickname : " + myNickname);
		//System.out.println(session);
		//System.out.println(players.get(session).getGameroomId());
		//System.out.println(players.get(session).getNickName());
		
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String receivedMessage = message.getPayload();
		System.out.println("receivedMessage:");
		System.out.println(receivedMessage);
		
		ObjectMapper objectMapper = new ObjectMapper();
		GameplayDto gameplayDto = objectMapper.readValue(receivedMessage, GameplayDto.class);
		Integer gameroomId = gameplayDto.getGameroomId();
//		WebSocketSession counterpart;
//		for (WebSocketSession key : players.keySet()) {
//			if(players.get(key).equals(gameroomId) && !key.equals(session)) {
//				counterpart = key;
//			}
//		}
		
		//JSONObject jsonObject = new JSONObject();
		if (gameplayDto.getType().equals(GameplayDto.Type.START)) {
			
			WebSocketSession counterpart = null;
			
			for (Map.Entry<WebSocketSession, TupleInfo<Integer, String>> entry : players.entrySet()) {
				WebSocketSession key = entry.getKey();
				Integer value1 = entry.getValue().getGameroomId();
				String value2 = entry.getValue().getNickName();
				
				if(value1.equals(gameroomId)) {
					//jsonObject.put(key.getId().toString(), value2);
					if(!key.equals(session)) {
						counterpart = key;
					}
				}
			}
			
			//save me-counterpart session info
			matching.put(session, counterpart);
			System.out.println(session);
			System.out.println(counterpart);
			
			if (matching.containsKey(counterpart)) {
				for (WebSocketSession player : players.keySet()) {
					if (players.get(player).getGameroomId().equals(gameroomId)) {
						//player.sendMessage(new TextMessage(jsonObject.toJSONString()));
						player.sendMessage(new TextMessage("Choose the word!"));
					}
				}
			}
			
			//session.sendMessage(new TextMessage(session.getId().toString()));
			
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
		//GameplayManager gameManager = GameplayService.findManagerById(gameroomId);
		//gameManager.handleActions();
		
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.out.println("Player left");
		players.remove(session);
	}

}
