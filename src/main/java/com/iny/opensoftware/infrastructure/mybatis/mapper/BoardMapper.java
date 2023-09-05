package com.iny.opensoftware.infrastructure.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.iny.opensoftware.infrastructure.mybatis.dto.BoardObject;

@Mapper
public interface BoardMapper {
	
	// RoomType에 따른 게시글 반환
	List<BoardObject> selectAllBoardByRoomType(@Param("roomType") String roomType);
	
	// 작성자의 모든 게시글
	List<BoardObject> selectBoardByRegister(@Param("register") String register);
	
	// 게시글 1개 반환(자세히)
	BoardObject selectBoardById(@Param("id") long id);
	
	// 게시글 작성
	Integer insertBoard(BoardObject data);
	
	// 게시글 수정 or 삭제
	Integer updateBoard(BoardObject data);
}
