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
		
		account.setAccountId("Test5");
		account.setPassword("1234");
		account.setProfile(new UserProfile("홍길동", "TestNick", Timestamp.valueOf("2023-01-01 11:12:11").toInstant(),"010-1234-5678", "aaaa4@gmail.net", "서울 어딘가 니네집"));
		account.setHeart(new Heart(2, 2));
		account.setFollower(10);
		account.setFollowing(12);
		account.setUploadAmiCount(0);
		account.setAuth(Authorize.Admin);
		
		account.save(repository);
	}
	
	@Test
	public void 변경테스트() {
		Account account = this.factory.getInstance();
		
		account.setId(AccountId.of(5));
		account.setAccountId("Test3");
		account.setPassword("1234");
		account.setProfile(new UserProfile("홍길동2", "TestNick", Timestamp.valueOf("2023-01-01 11:12:11").toInstant(),"010-1234-5671", "aaaa4@gmail.net", "서울 어딘가 니네집"));
		account.setHeart(new Heart(2, 2));
		account.setFollower(11);
		account.setFollowing(12);
		account.setUploadAmiCount(1);
		account.setAuth(Authorize.Admin);
		
		account.save(repository);
	}
	
	@Test
	public void 도메인_테스트() {
		Account account = this.factory.getInstance();
		
		account.setId(AccountId.of(5));
		account.fetchById(this.repository);
		
		log.info("---------------------------------------------------------");
		log.info("account" + account.toString());
		log.info("---------------------------------------------------------");
		
		int tmp = account.followerUp(repository);
		
		log.info("---------------------------------------------------------");
		log.info("account" + account.toString() + " : " + tmp);
		log.info("---------------------------------------------------------");
		
		tmp = account.followerDown(repository);
		
		log.info("---------------------------------------------------------");
		log.info("account" + account.toString() + " : " + tmp);
		log.info("---------------------------------------------------------");
		
		tmp = account.followingUp(repository);
		
		log.info("---------------------------------------------------------");
		log.info("account" + account.toString() + " : " + tmp);
		log.info("---------------------------------------------------------");
		
		tmp = account.followingDown(repository);
		
		log.info("---------------------------------------------------------");
		log.info("account" + account.toString() + " : " + tmp);
		log.info("---------------------------------------------------------");
		
		account.authorizeAccount(repository);
		
		log.info("---------------------------------------------------------");
		log.info("account" + account.toString());
		log.info("---------------------------------------------------------");
		
		account.setAuth(Authorize.Admin);
		repository.save(account);
		
		log.info("---------------------------------------------------------");
		log.info("account" + account.toString());
		log.info("---------------------------------------------------------");
		
		tmp = account.upLoadAmiCountUp(repository);
		
		log.info("---------------------------------------------------------");
		log.info("account" + account.toString() + " : " + tmp);
		log.info("---------------------------------------------------------");
		
		tmp = account.upLoadAmiCountDown(repository);
		
		log.info("---------------------------------------------------------");
		log.info("account" + account.toString() + " : " + tmp);
		log.info("---------------------------------------------------------");
		
		Account account2 = this.factory.getInstance();
		
		account2.setAccountId("Test");
		account2.fetchByAccountId(repository);
		account2.setPassword("1234");
		
		log.info("---------------------------------------------------------");
		log.info("account2" + account2.toString());
		log.info("---------------------------------------------------------");
		
		boolean tmpBool = account2.authenticateAccount(repository, "Test", "1234");
		
		log.info("---------------------------------------------------------");
		log.info("account2 yes? : " + tmpBool);
		log.info("---------------------------------------------------------");
		
		tmpBool = account2.authenticateAccount(repository, "Test1", "12346");
		
		log.info("---------------------------------------------------------");
		log.info("account2 yes? : " + tmpBool);
		log.info("---------------------------------------------------------");
	}
}
