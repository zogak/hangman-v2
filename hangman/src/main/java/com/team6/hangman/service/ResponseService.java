package com.team6.hangman.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.team6.hangman.dto.response.CommonResponse;
import com.team6.hangman.entity.ListResult;
import com.team6.hangman.entity.SingleResult;
import com.team6.hangman.entity.StatusCode;

@Service
public class ResponseService {
	
	public void setSuccessResult(CommonResponse commonResponse) {
		commonResponse.setStatusCode(StatusCode.SUCCESS.getCode());
	}
	
	public <T> SingleResult<T> getSingleResult(T data){
		SingleResult<T> result = new SingleResult<>();
		result.setResult(data);
		setSuccessResult(result);
		return result;
	}
	
	public <T> ListResult<T> getListResult(List<T> data){
		ListResult<T> result = new ListResult<>();
		result.setResult(data);
		setSuccessResult(result);
		return result;
	}
	
}
