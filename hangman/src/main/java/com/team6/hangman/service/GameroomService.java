package com.team6.hangman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.team6.hangman.dto.request.GameroomRequestDto;
import com.team6.hangman.dto.response.GameroomResponseDto;
import com.team6.hangman.entity.Gameroom;
import com.team6.hangman.repository.GameroomRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class GameroomService {
	private GameroomRepository gameroomRepository;

	public Gameroom createGameroom(GameroomRequestDto gameroomDto) {
		Gameroom gameroom = Gameroom.builder()
				.title(gameroomDto.getTitle())
				.wordCount(gameroomDto.getWordCount())
				.gameCharacter(gameroomDto.getGameCharacter())
				.build();
		
		return gameroomRepository.save(gameroom);
	}
	
	public GameroomResponseDto toGameroomResponseDto(Gameroom gameroom) {
		return GameroomResponseDto.builder()
				.title(gameroom.getTitle())
				.wordCount(gameroom.getWordCount())
				.playerCount(gameroom.getPlayerCount())
				.gameCharacter(gameroom.getGameCharacter())
				.build();
	}
	
	public List<GameroomResponseDto> getAllGameroom() {
		List<GameroomResponseDto> gameroomListResponse = new ArrayList<>();
		List<Gameroom> gameroomList = gameroomRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
		for (Gameroom gameroom : gameroomList) {
			gameroomListResponse.add(toGameroomResponseDto(gameroom));
		}
		return gameroomListResponse;
	
	}
	
}
