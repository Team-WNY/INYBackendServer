package com.iny.opensoftware.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.iny.opensoftware.infrastructure.mybatis.dto.TestEmployeeObject;

/**
 * TODO: Mybatis 테스트 코드(삭제 예정)
 * Mybatis 3.x 오면서 '@Mapper'는 사용하지 않아도 된다고 한다.
 */

@Mapper
public interface TestEmployeeMapper {
	TestEmployeeObject selectEmployeeById(@Param("id") Long id);
}