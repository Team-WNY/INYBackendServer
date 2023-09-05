package com.iny.opensoftware.presentation.api.v1.board.obj;

import lombok.Data;

@Data
public class CommentAddObject {
	private long userId;
	
	private String commentRegister;
	
	private String commentContents;
}
