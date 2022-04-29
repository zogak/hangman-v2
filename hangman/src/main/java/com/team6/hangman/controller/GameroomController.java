package com.team6.hangman.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team6.hangman.dto.request.GameroomRequestDto;
import com.team6.hangman.dto.response.GameroomResponseDto;
import com.team6.hangman.entity.ListResult;
import com.team6.hangman.service.GameroomService;
import com.team6.hangman.service.ResponseService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class GameroomController {
	private GameroomService gameroomService;
	private ResponseService responseService;
	
	@PostMapping("/gameroom")
	public String createRoom(@RequestBody GameroomRequestDto gameroomDto) {
		gameroomService.createGameroom(gameroomDto);
		return "success";
	}
	
	@GetMapping("/gameroom")
	public ResponseEntity<ListResult<GameroomResponseDto>> getGameroom(){
		return new ResponseEntity<>(responseService.getListResult(gameroomService.getAllGameroom()), HttpStatus.OK);
	}
}
