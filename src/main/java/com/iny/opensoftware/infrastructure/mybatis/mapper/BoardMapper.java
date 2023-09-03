package com.iny.opensoftware.infrastructure.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.iny.opensoftware.infrastructure.mybatis.dto.BoardObject;

@Mapper
public interface BoardMapper {
	List<BoardObject> selectAllBoard();
	BoardObject selectBoardByRegister(@Param("register") String register);
}
