package com.team6.hangman.entity;

public enum StatusCode {
	SUCCESS(200),
	ACCEPTED(202),
	BAD_REQUEST(400),
	INTERNAL_SERVER_ERROR(500);
	
	private final Integer code;

	private StatusCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
	
	
}
