package com.team6.hangman.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.team6.hangman.dto.request.ImageRequestDto;
import com.team6.hangman.entity.Image;
import com.team6.hangman.repository.ImageRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ImageService {
	private ImageRepository imageRepository;
	
	public boolean saveImage(ImageRequestDto imageRequestDto) {
		
		Image image = Image.builder()
				.imageName(imageRequestDto.getImageName())
				.imageOrder(imageRequestDto.getImageOrder())
				.build();
		
		imageRepository.save(image);
		
		return true;
	}
	
	public String getImageByOrder(Integer order) {
		return imageRepository.getImageByImageOrder(order);
	}
}
