package com.iny.opensoftware.domain.account.repository.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.iny.opensoftware.domain.account.Account;
import com.iny.opensoftware.domain.account.AccountRepository;
import com.iny.opensoftware.infrastructure.mybatis.mapper.AccountMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 기존 하위 엔티티가 있다면 여기서 하위 엔티티 레포지토리 필드로 받는다.
 */
@Slf4j
public class MybatisAccountRepository implements AccountRepository{
	
	private final AccountMapper mapper;
	
	public MybatisAccountRepository(AccountMapper mapper) {
		Assert.notNull(mapper, "AccountMapper is not Null!");
		this.mapper = mapper;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save() {
		
		
	}

	@Override
	public void updateById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getOneAccountById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getOneAccountByAccountId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}

}
