package com.iny.opensoftware.application.encrypt.impl;

import com.iny.opensoftware.application.account.LoginService;
import com.iny.opensoftware.application.encrypt.EncryptModule;
import com.iny.opensoftware.application.encrypt.exception.EncryptException;
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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Optional;

// 이름 후보 AESMybatisService
// AESJpaService
@Service
@RequiredArgsConstructor
public class EncrtptServiceImpl implements EncryptModule {
	
	private final int TEMP_PASSWORD_LENGTH = 10;
	// 대문자로(상수라서)
	private final String specName = "AES/CBC/PKCS5Padding";
	
	// Exception
	@Override
	public String encrypt(String plainText, SecretKey key, IvParameterSpec iv) throws EncryptException {
		Assert.hasText(plainText, "평문은 null이거나, 공백일 수 없습니다.");
		Cipher cipher;
		byte[] encrypted = null;
		
		try {
			cipher = Cipher.getInstance(specName);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			
			encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
			throw new EncryptException("암호화 로직에서 실패하였습니다.");
		}
		
		
		if (encrypted == null) {
			throw new EncryptException("암호화된 구문을 반환받지 못하였습니다.");
		}
		
		return new String(Base64.getEncoder().encode(encrypted));
	}


	@Override
	public String decrypt(String cipherText,SecretKey key, IvParameterSpec iv ) throws Exception {
		Cipher cipher = Cipher.getInstance(specName);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(decrypted, StandardCharsets.UTF_8);
	}

	@Override
	public String tmpRandomPwd() {
		return randomString();
	}

	@Override
	public boolean hashMatch(String plainText, String ciperText){
		return BCrypt.checkpw(plainText, ciperText);
	}

	@Override
	public String hashEncrypt(String plainText){
		return BCrypt.hashpw(plainText,BCrypt.gensalt());
	}

	private String randomString() {
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
