package com.team6.hangman.entity;

import java.util.List;

import com.team6.hangman.dto.response.CommonResponse;

public class ListResult<T> extends CommonResponse{
	private List<T> result;

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
	
}
