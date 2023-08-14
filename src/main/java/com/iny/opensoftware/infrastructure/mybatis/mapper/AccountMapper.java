package com.iny.opensoftware.infrastructure.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.iny.opensoftware.infrastructure.mybatis.dto.AccountObject;

@Mapper
public interface AccountMapper {
	AccountObject selectAccountById(@Param("id") Long id);
}
