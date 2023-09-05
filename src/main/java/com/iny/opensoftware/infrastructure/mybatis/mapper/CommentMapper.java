package com.iny.opensoftware.infrastructure.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.iny.opensoftware.infrastructure.mybatis.dto.CommentObject;

@Mapper
public interface CommentMapper {
	
	List<CommentObject> getComments(@Param("boardId") long boardId);
	
	void addComment(CommentObject data);
}
