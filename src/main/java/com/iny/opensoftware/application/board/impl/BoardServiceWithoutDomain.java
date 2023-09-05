package com.iny.opensoftware.application.board.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iny.opensoftware.application.board.BoardService;
import com.iny.opensoftware.infrastructure.mybatis.dto.BoardObject;
import com.iny.opensoftware.infrastructure.mybatis.mapper.BoardMapper;
import com.iny.opensoftware.presentation.api.v1.board.obj.CommentAddObject;
import com.iny.opensoftware.presentation.api.v1.board.obj.EnrollBoardObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceWithoutDomain implements BoardService{
	
	private final BoardMapper boardMapper;

	@Override
	public List<BoardObject> getBoardList(String roomType) {
		return this.boardMapper.selectAllBoardByRoomType(roomType);
	}

	@Override
	public void createBoard(EnrollBoardObject data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(EnrollBoardObject data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeRoomType(String roomType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComment(long boardId, CommentAddObject data) {
		// TODO Auto-generated method stub
		
	}

}
