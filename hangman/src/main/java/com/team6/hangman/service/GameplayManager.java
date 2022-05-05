package com.team6.hangman.service;

import java.util.HashMap;
import java.util.HashSet;

import com.team6.hangman.dto.request.GameplayDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameplayManager {

	private Integer gameroomId;
	//private HashSet<WebSocketSession> sessions = new HashSet<>();
	private HashMap<WebSocketSession, String> targetWords = new HashMap<>();
	private String myTargetWord;
	@Autowired
	private GameplayService gameplayService;
	@Autowired
	private ImageService imageService;
	
	public GameplayManager(Integer gameroomId) {
		this.gameroomId = gameroomId;
	}
	
	public void handleActions(WebSocketSession session, GameplayDto gameplayDto) {
		if(gameplayDto.getType().equals(GameplayDto.Type.ENTER)) {
			//sessions.add(session);
			//TODO gameroom db player count ++
			//TODO 선택한 단어 (세션, 단어) 쌍으로 저장
			targetWords.put(session, gameplayDto.getLetter());
			
		}
		else {
			for(WebSocketSession key : targetWords.keySet()) {
				if (!key.equals(session)) {
					myTargetWord = targetWords.get(key);
					break;
				}
			}
			
			if (!gameplayDto.getIsWord()) {
				String predictedLetter = gameplayDto.getLetter();
				if (gameplayService.isLetterExists(myTargetWord, predictedLetter)) {
					//1. 알파벳 있는 경우
				}
				
				//2. 알파벳 없는 경우
				else {
					// 그림 업데이트
					//imageService.getImageByOrder();
					
				}
				
				
				//TODO 그림 업데이트 상대방한테 보낼 것
			}
			else {
				String predictedWord = gameplayDto.getLetter();
				gameplayService.isWordCorrect(myTargetWord, predictedWord);
			}
		}
			
	}
}
