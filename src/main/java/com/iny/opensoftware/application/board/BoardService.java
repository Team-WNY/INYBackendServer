package com.iny.opensoftware.application.board;

import java.util.List;

import com.iny.opensoftware.infrastructure.mybatis.dto.BoardObject;
import com.iny.opensoftware.presentation.api.v1.board.obj.CommentAddObject;
import com.iny.opensoftware.presentation.api.v1.board.obj.EnrollBoardObject;

/**
 * 현재는 Application에서 모든 처리를 하도록 개발함
 * 도메인으로 리팩터링 필요
 * 개발 중단, 도메인 개발 우선 필요
 */

public interface BoardService {
	
	// 게시글 리스트 반환
	List<BoardObject> getBoardList(String roomType);
	// 게시글 등록
	void createBoard(EnrollBoardObject data);
	// 게시글 삭제 및 비활성
	void deleteBoard(long id);
	// 게시글 수정
	void updateBoard(EnrollBoardObject data);
	// 코멘트 추가
	void addComment(long boardId, CommentAddObject data);
	// 게시글 상태 변경
	void changeRoomType(String roomType);

}
