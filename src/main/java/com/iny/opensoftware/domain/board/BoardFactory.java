package com.iny.opensoftware.domain.board;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardFactory {
	
	public Board getInstance() { return new Board(); }
}
