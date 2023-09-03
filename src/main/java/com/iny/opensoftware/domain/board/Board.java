package com.iny.opensoftware.domain.board;

import com.iny.opensoftware.domain.account.Account;
import com.iny.opensoftware.domain.board.comment.Comment;
import com.iny.opensoftware.domain.board.content.Content;
import com.iny.opensoftware.domain.board.enums.RoomType;
import com.iny.opensoftware.domain.board.enums.Status;
import com.iny.opensoftware.domain.board.image.Image;
import com.iny.opensoftware.domain.common.DomainEntity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@DomainEntity
@RequiredArgsConstructor
public class Board {
	
	private BoardId id;
	private Account register;
	private Instant upLoadTime;
	private Content content;
	private List<Comment> comments;
	private List<Image> images;
	private Status roomStatus;
	private RoomType roomType;
	private Boolean delType;
	
}
