package com.iny.opensoftware.application.account.impl;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iny.opensoftware.application.account.LoginService;
import com.iny.opensoftware.application.exception.AccountFindFailedException;
import com.iny.opensoftware.application.exception.AccountVerifyFailException;
import com.iny.opensoftware.application.mail.EmailVerificationService;
import com.iny.opensoftware.domain.account.Account;
import com.iny.opensoftware.domain.account.AccountFactory;
import com.iny.opensoftware.domain.account.AccountRepository;
import com.iny.opensoftware.domain.account.auth.Authorize;
import com.iny.opensoftware.domain.account.convert.AccountConverter;
import com.iny.opensoftware.domain.account.convert.impl.MyBatisAccountConverter;
import com.iny.opensoftware.domain.account.heart.Heart;
import com.iny.opensoftware.domain.account.profile.UserProfile;
import com.iny.opensoftware.domain.account.repository.impl.MybatisAccountRepository;
import com.iny.opensoftware.infrastructure.mybatis.dto.AccountObject;
import com.iny.opensoftware.infrastructure.mybatis.mapper.AccountMapper;
import com.iny.opensoftware.presentation.api.v1.account.obj.SignUpObject;

import io.jsonwebtoken.lang.Assert;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	private final AccountMapper mapper;
	private final EmailVerificationService emailVerificationService;
	private AccountFactory factory;
	private AccountRepository repository;
	
	private AccountConverter<AccountObject> converter;
	
	// 임시 비밀번호 길이
    private final int TEMP_PASSWORD_LENGTH = 10;

    @PostConstruct
    public void init() {
    	this.converter = new MyBatisAccountConverter();
    	this.repository = new MybatisAccountRepository(this.mapper, this.converter);
    	this.factory = new AccountFactory();
    }

    /**
     * 계정 중복 확인
     */
	@Override
	public Boolean accountDuplication(String accountId) {
		Assert.hasText(accountId, "중복을 확인할, 계정값이 없을 수 없습니다.");
		
		Account account = this.repository.getOneAccountByAccountId(accountId);
		
		return Optional.ofNullable(account).isPresent();
	}

	/**
	 * 이메일 인증 완료 절차
	 */
	@Override
	public void emailAuth(String email, String code) throws AccountVerifyFailException {
		Assert.hasText(email, "email값은 null이거나, 공백일 수 없습니다.");
		Assert.notNull(code, "code값이 없으면 인증 절차를 진행할 수 없습니다.");
		
		Account account = this.factory.getInstance();
		account.setProfile(new UserProfile());
		account.getProfile().setEmail(email);
		
		account.setAccountId(account.findAccountId(this.repository));
		
		if (this.emailVerificationService.verifyVerificationCode(email, code)) {
			account.fetchByAccountId(this.repository);
			account.authorizeAccount(this.repository);
		} else {
			throw new AccountVerifyFailException();
		}
	}

	/**
	 * 회원 가입
	 */
	@Override
	public void signUp(SignUpObject data) {
		Account account = this.factory.getInstance();
		UserProfile profile = new UserProfile();
		
		account.setAccountId(data.getAccountId());
		account.setPassword(data.getPassword());
		profile.setAddress(data.getAddress());
		profile.setEmail(data.getEmail());
		profile.setPhoneNumber(data.getPhoneNumber());
		profile.setBirthDay(LocalDate.parse(data.getBirthDay())
				.atStartOfDay(ZoneId.of("Asia/Seoul"))
				.toInstant());
		profile.setName(data.getName());
		profile.setNickName(data.getNickName());
		account.setProfile(profile);
		account.setHeart(new Heart());
		account.setAuth(Authorize.NONAUTH);
		
		account.save(this.repository);
	}

	/**
	 * 계정 찾기
	 */
	@Override
	public String accountFind(String email) {
		Assert.hasText(email, "email값은 null이거나, 공백일 수 없습니다.");
		
		Account account = this.factory.getInstance();
		account.setProfile(new UserProfile());
		account.getProfile().setEmail(email);
		
		return account.findAccountId(this.repository);
	}

	/**
	 * 계정 임시 패스워드로 변경 및 반환
	 */
	@Override
	public String passwordFind(String accountId, String email, String code) throws AccountFindFailedException {
		Assert.hasText(accountId, "계정이 없습니다.");
		Assert.hasText(email, "이메일이 없습니다.");
		Assert.hasText(code, "코드가 없습니다.");
		
		Account account = this.factory.getInstance();
		account.setAccountId(accountId);
		account.setProfile(new UserProfile());
		account.getProfile().setEmail(email);

		// 계정이 DB에 있는지 체크 / DB에 저장된 계정의 이메일 값으로 계정을 찾아서 현재 입력된 계정과 매칭
		if (!account.checkAccount(this.repository) || !this.emailVerificationService.verifyVerificationCode(email, code)) {
			throw new AccountFindFailedException();
		}
		
		account.fetchByAccountId(this.repository);
		
		String tmpPassword = this.generateTemporaryPassword();
		account.setPassword(tmpPassword);
		
		account.save(this.repository);
		
		return tmpPassword;
	}
	
	/**
	 * 패스워드 변경
	 */
	@Override
	public void changePassword(String accountId, String passowrd) {
		Assert.hasText(accountId, "계정이 없습니다.");
		Assert.hasText(passowrd, "패스워드가 없습니다.");
		
		Account account = this.factory.getInstance();
		account.setAccountId(accountId);
		account.fetchByAccountId(this.repository);
		
		account.setPassword(passowrd);
		account.save(repository);
	}
	
	/**
	 * AccountId 값으로 계정 정보 반환
	 */
	@Override
	public Account getAccountStatus(String accountId) {
		Assert.hasText(accountId, "계정이 없습니다.");
		
		return this.repository.getOneAccountByAccountId(accountId);
	}
	
	/**
	 * 임시 패스워드 발급
	 * @return
	 */
	private String generateTemporaryPassword() {
        // 안전한 난수 생성
        SecureRandom secureRandom = new SecureRandom();
        
        // 임시 비밀번호 생성을 위한 랜덤 바이트 생성
        byte[] randomBytes = new byte[TEMP_PASSWORD_LENGTH];
        secureRandom.nextBytes(randomBytes);
        
        // 랜덤 바이트를 Base64 인코딩하여 임시 비밀번호 생성
        String temporaryPassword = Base64.getEncoder().encodeToString(randomBytes);
        
        return temporaryPassword;
    }

}
