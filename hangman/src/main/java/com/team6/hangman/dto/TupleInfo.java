package com.team6.hangman.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TupleInfo<G, N> {
	private G gameroomId;
	private N nickName;
	
	public TupleInfo(G gameroomId, N nickName) {
		this.gameroomId = gameroomId;
		this.nickName = nickName;
    }
	
}
