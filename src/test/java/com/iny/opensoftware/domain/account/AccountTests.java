package com.iny.opensoftware.domain.account;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iny.opensoftware.domain.account.auth.Authorize;
import com.iny.opensoftware.domain.account.convert.AccountConverter;
import com.iny.opensoftware.domain.account.convert.impl.MyBatisAccountConverter;
import com.iny.opensoftware.domain.account.heart.Heart;
import com.iny.opensoftware.domain.account.profile.UserProfile;
import com.iny.opensoftware.domain.account.repository.impl.MybatisAccountRepository;
import com.iny.opensoftware.infrastructure.mybatis.dto.AccountObject;
import com.iny.opensoftware.infrastructure.mybatis.mapper.AccountMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class AccountTests {
	
	private AccountRepository repository;
	
	private AccountFactory factory;
	
	private AccountConverter<AccountObject> converter;
	
	@Autowired
	private AccountMapper mapper;
	
	@PostConstruct
	public void init() {
		this.factory = new AccountFactory();
		this.converter = new MyBatisAccountConverter();
		this.repository = new MybatisAccountRepository(this.mapper, this.converter);
	}
	
	@Test
	public void 생성테스트() {
		Account account = this.factory.getInstance();
		
		account.setAccountId("Test3");
		account.setPassword("1234");
		account.setProfile(new UserProfile("홍길동", "TestNick", Timestamp.valueOf("2023-01-01 11:12:11").toInstant(), "aaaa3@gmail.net", "서울 어딘가 니네집"));
		account.setHeart(new Heart(2, 2));
		account.setFollower(10);
		account.setFollowing(12);
		account.setUploadAmiCount(0);
		account.setAuth(Authorize.Admin);
		
		account.save(repository);
	}
	
}
