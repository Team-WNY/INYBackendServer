package com.iny.opensoftware.infrastructure.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.iny.opensoftware.infrastructure.mybatis.dto.AccountObject;

@Mapper
public interface AccountMapper {
	AccountObject selectAccountById(@Param("id") Long id);
	AccountObject selectAccountByAccountId(@Param("accountId") String accountId);
	List<AccountObject> selectAllAccount();
	
	// 중복 계정 체크용
	String isAccountId(@Param("accountId") String accountId);
	Integer insertAccount(AccountObject data);
	Integer updateAccount(AccountObject data);
	Long nowId(@Param("accountId") String accountId);
	String findAccountIdByEmail(@Param("email") String email);
}
