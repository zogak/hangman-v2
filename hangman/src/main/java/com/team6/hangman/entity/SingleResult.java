package com.team6.hangman.entity;

import com.team6.hangman.dto.response.CommonResponse;

public class SingleResult<T> extends CommonResponse{
	private T result;

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
}
