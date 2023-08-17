package com.iny.opensoftware.domain.account.repository.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.iny.opensoftware.domain.account.Account;
import com.iny.opensoftware.domain.account.AccountId;
import com.iny.opensoftware.domain.account.AccountRepository;
import com.iny.opensoftware.domain.account.convert.AccountConverter;
import com.iny.opensoftware.infrastructure.mybatis.dto.AccountObject;
import com.iny.opensoftware.infrastructure.mybatis.mapper.AccountMapper;

/**
 * 기존 하위 엔티티가 있다면 여기서 하위 엔티티 레포지토리 필드로 받는다.
 */
public class MybatisAccountRepository implements AccountRepository{
	
	private final AccountMapper mapper;
	private final AccountConverter<AccountObject> converter;
	
	public MybatisAccountRepository(AccountMapper mapper, AccountConverter<AccountObject> converter) {
		Assert.notNull(mapper, "AccountMapper is not Null!");
		Assert.notNull(converter, "AccountConverter is not Null!");
		this.mapper = mapper;
		this.converter = converter;
	}
	
	// 신규 생성되는 아이디인 경우 null Id값이 null이라 추후에 다시 넣어줘야 하는거고,
	// 기존 ID는 그냥 진행하면 됨
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Account account) {
		//ID 값을 검색해서 이미 존재하면 가져오고, 없다면 null
		final AccountObject persistObject = Optional.ofNullable(account.getId())
				.map(id -> this.mapper.selectAccountById(id.getValue()))
				.orElse(null);
		
		// 새롭게 Account로 들어온 데이터가 업데이트 되는 곳(도메인 -> DB 오브젝트로 변환)
		final AccountObject paramobj = this.convert(account);
		
		if (Objects.isNull(persistObject)) {
			this.mapper.insertAccount(paramobj);
			//방금 테이블에 저장된 계정의 ID값을 가져와 현재 Account 객체에 주입
			account.setId(AccountId.of(this.mapper.nowId(account.getAccountId())));
		} else {
			this.mapper.updateAccount(paramobj);
		}
		
	}

	@Override
	public Account getOneAccountById(Long id) {
		return Optional.ofNullable(this.mapper.selectAccountById(id))
				.map(this.converter::convert)
				.orElse(null);
	}

	@Override
	public Account getOneAccountByAccountId(String id) {
		return Optional.ofNullable(this.mapper.selectAccountByAccountId(id))
				.map(this.converter::convert)
				.orElse(null);
	}

	@Override
	public List<Account> getAllAccount() {
		return Optional.ofNullable(this.mapper.selectAllAccount())
				.map(accountList -> accountList.stream()
						.map(this.converter::convert)
						.collect(Collectors.toList()))
				.orElse(Collections.emptyList());
	}
	
	@Override
	public Boolean isAccount(String accountId) {
		return Optional.ofNullable(this.mapper.isAccountId(accountId))
				.filter(id -> !id.isEmpty())
				.isPresent();
	}

	/**
	 * 도메인에서 DB 오브젝트로 변경하는 함수
	 * @param account
	 * @return AccountObject
	 */
	private AccountObject convert(Account account) {
		AccountObject obj = new AccountObject();
		
		if (account.getId() != null) {
			obj.setId(Optional.ofNullable(account.getId().getValue()).orElse(null));
		}
		obj.setAccountId(Optional.ofNullable(account.getAccountId()).orElse(""));
		obj.setPassword(Optional.ofNullable(account.getPassword()).orElse(""));
		obj.setName(Optional.ofNullable(account.getProfile().getName()).orElse(""));
		obj.setNickName(Optional.ofNullable(account.getProfile().getNickName()).orElse(""));
		obj.setBirthDay(Optional.ofNullable(Timestamp.from(account.getProfile().getBirthDay())).orElse(Timestamp.from(Instant.now())));
		obj.setEmail(Optional.ofNullable(account.getProfile().getEmail()).orElse(""));
		obj.setAddress(Optional.ofNullable(account.getProfile().getAddress()).orElse(""));
		obj.setBlackHeart(Optional.ofNullable(account.getHeart().getBlackHeart()).orElse(0));
		obj.setRedHeart(Optional.ofNullable(account.getHeart().getRedHeart()).orElse(0));
		obj.setFollower(Optional.ofNullable(account.getFollower()).orElse(0));
		obj.setFollowing(Optional.ofNullable(account.getFollowing()).orElse(0));
		obj.setUploadAmiCount(Optional.ofNullable(account.getUploadAmiCount()).orElse(0));
		obj.setAuth(Optional.ofNullable(account.getAuth().name()).orElse("NonAuth"));
		
		return obj;
	}

}
