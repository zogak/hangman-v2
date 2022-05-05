package com.team6.hangman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team6.hangman.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
	String getImageByImageOrder(Integer image_order);
}
