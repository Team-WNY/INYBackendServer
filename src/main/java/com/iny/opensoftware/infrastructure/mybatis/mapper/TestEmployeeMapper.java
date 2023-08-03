package com.iny.opensoftware.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.iny.opensoftware.infrastructure.mybatis.dto.TestEmployeeObject;

/**
 * TODO: Mybatis 테스트 코드(삭제 예정)
 */

@Mapper
public interface TestEmployeeMapper {
	TestEmployeeObject selectEmployeeById(@Param("id") Long id);
}