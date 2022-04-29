package com.team6.hangman.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team6.hangman.dto.request.ImageRequestDto;
import com.team6.hangman.entity.Image;
import com.team6.hangman.service.ImageService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ImageController {
	
	private ImageService imageService;
	
	@PostMapping("/image")
	public String saveImage(@RequestBody ImageRequestDto imageRequestDto) {
		imageService.saveImage(imageRequestDto);
		return "success";
	}
}
