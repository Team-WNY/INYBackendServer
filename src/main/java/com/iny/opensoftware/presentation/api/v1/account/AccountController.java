package com.iny.opensoftware.presentation.api.v1.account;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iny.opensoftware.application.account.LoginService;
import com.iny.opensoftware.application.mail.EmailVerificationService;
import com.iny.opensoftware.domain.account.Account;
import com.iny.opensoftware.presentation.ApiResponse;
import com.iny.opensoftware.presentation.api.v1.account.obj.SignUpObject;

import io.jsonwebtoken.lang.Assert;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "계정 관련 컨트롤러", description = "nt는 noToken으로써, 토큰 인증 없이 요청 가능 / 나머지는 토큰 필요")
@RequestMapping("/api/v1/account")
public class AccountController {

	private final LoginService loginService;
	private final EmailVerificationService mailService;
	
	// 회원가입
	@PostMapping("/nt/signup")
	public ApiResponse<Message> singUp(@RequestBody SignUpObject data){
		
		ApiResponse<Message> res = ApiResponse.of(new Message());
		Message resData = new Message();
		
		try {
			Assert.notNull(data, "계정 정보가 들어오지 않았습니다.");
		
			this.loginService.signUp(data);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setMessage("Success");
			
		} catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.setMessage("Fail");
			resData.setMessage(e.getMessage());
			res.setPayload(resData);
			e.printStackTrace();
		}
		
		return res;
	}
	
	// 이메일 인증번호 요청
	@PostMapping("/nt/auth/code-request")
	public ApiResponse<Message> emailAuthNumberRequest(@RequestParam(name = "email") String email) {
		
		ApiResponse<Message> res = ApiResponse.of(new Message());
		Message resData = new Message();
		
		try {
			
			String code = this.mailService.generateVerificationCode(email);
			this.mailService.sendVerificationCode(email, code);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setMessage("Success");
			
		} catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.setMessage("Fail");
			resData.setMessage(e.getMessage());
			res.setPayload(resData);
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	// 아이디 찾기
	@PostMapping("/nt/find/accountId")
	public ApiResponse<Data> findAccountId(@RequestParam(name = "email") String email){
		
		ApiResponse<Data> res = ApiResponse.of(new Data());
		Data resData = new Data();
		
		try {
			
			String userAccountId = this.loginService.accountFind(email);
			
			resData.setAccountId(userAccountId);
			res.setStatus(HttpServletResponse.SC_OK);
			res.setMessage("Success");
			res.setPayload(resData);
			
		} catch(IllegalArgumentException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			res.setMessage("Fail");
			resData.setMessage("이메일을 입력하지 않음");
			res.setPayload(resData);
			e.printStackTrace();
		} catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.setMessage("Fail");
			resData.setMessage("알수 없는 에러");
			res.setPayload(resData);
			e.printStackTrace();
		}
		
		return res;
	}
	
	// 임시 비밀번호 발급
	@PostMapping("/nt/find/password")
	public ApiResponse<Data> findPassword(@RequestParam(name = "email") String email,
									  @RequestParam(name = "accountId") String accountId,
									  @RequestParam(name = "code") String code){
		
		ApiResponse<Data> res = ApiResponse.of(new Data());
		Data resData = new Data();
		
		try {
			
			String tmpPassword = this.loginService.passwordFind(accountId, email, code);
			
			resData.setAccountId(accountId);
			resData.setPassword(tmpPassword);
			res.setStatus(HttpServletResponse.SC_OK);
			res.setMessage("Success");
			res.setPayload(resData);
			
		} catch(IllegalArgumentException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			res.setMessage("Fail");
			resData.setMessage("이메일, 아이디, 코드 값이 없음");
			res.setPayload(resData);
			e.printStackTrace();
		} catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.setMessage("Fail");
			resData.setMessage("알수 없는 에러");
			res.setPayload(resData);
			e.printStackTrace();
		}
		
		return res;
	}
	
	// 비밀번호 변경
	@PostMapping("/change/password")
	public ApiResponse<Data> changePassword(@RequestParam(name = "accountId") String accountId,
									  @RequestParam(name = "password") String password){
		
		ApiResponse<Data> res = ApiResponse.of(new Data());
		Data resData = new Data();
		
		try {
			
			this.loginService.changePassword(accountId, password);
			
			resData.setAccountId(accountId);
			resData.setPassword(password);
			res.setStatus(HttpServletResponse.SC_OK);
			res.setMessage("Success");
			res.setPayload(resData);
			
		} catch(IllegalArgumentException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			res.setMessage("Fail");
			resData.setMessage("아이디, 패스워드 값이 없음");
			res.setPayload(resData);
			e.printStackTrace();
		} catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.setMessage("Fail");
			resData.setMessage("알수 없는 에러");
			res.setPayload(resData);
			e.printStackTrace();
		}
		
		return res;
	}
	
	// 계정 중복 체크
	@PostMapping("/nt/check/accountId")
	public ApiResponse<Data> changePassword(@RequestParam(name = "accountId") String accountId){
		
		ApiResponse<Data> res = ApiResponse.of(new Data());
		Data resData = new Data();
		
		try {
			
			Boolean check = this.loginService.accountDuplication(accountId);
			
			resData.setAccountId(accountId);
			resData.setMessage("중복 계정 여부 : " + check);
			resData.setDuplication(check);
			res.setStatus(HttpServletResponse.SC_OK);
			res.setMessage("Success");
			res.setPayload(resData);
			
		} catch(IllegalArgumentException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			res.setMessage("Fail");
			resData.setMessage("아이디, 패스워드 값이 없음");
			res.setPayload(resData);
			e.printStackTrace();
		} catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.setMessage("Fail");
			resData.setMessage("알수 없는 에러");
			res.setPayload(resData);
			e.printStackTrace();
		}
		
		return res;
	}
	
	// 계정 정보 반환
	@PostMapping("/getinfo")
	public ApiResponse<Account> getAccountInfo(@RequestParam(name = "accountId") String accountId) {
		ApiResponse<Account> res = ApiResponse.of(new Account());
		
		try {
			
			Account accountInfo = this.loginService.getAccountStatus(accountId);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setMessage("Success");
			res.setPayload(accountInfo);
			
		} catch(IllegalArgumentException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			res.setMessage("Fail");
			res.setPayload(null);
			e.printStackTrace();
		} catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.setMessage("Fail");
			res.setPayload(null);
			e.printStackTrace();
		}
		
		return res;
	}
	
	// 이메일 인증 완료 절차
	@PostMapping("/nt/auth/authorization")
	public ApiResponse<Data> authAccount(@RequestParam(name = "email") String email,
										 @RequestParam(name = "code") String code) {
		
		ApiResponse<Data> res = ApiResponse.of(new Data());
		Data resData = new Data();
		
		try {
			
			this.loginService.emailAuth(email, code);
			
			res.setStatus(HttpServletResponse.SC_OK);
			res.setMessage("Success");
			
		} catch(IllegalArgumentException e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			res.setMessage("Fail");
			res.setPayload(null);
			e.printStackTrace(); 
		} catch(Exception e) {
			res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res.setMessage("Fail");
			resData.setMessage(e.getMessage());
			res.setPayload(null);
			e.printStackTrace();
		}
		
		return res;
	}
	
	@lombok.Data
	static class Data {
		@JsonProperty("account_id")
		private String accountId;
		@JsonProperty("password")
		private String password;
		@JsonProperty("Duplication")
		private boolean duplication;
		@JsonProperty("message")
		private String Message;
	}
	
	@lombok.Data
	static class Message {
		@JsonProperty("message")
		private String Message;
	}
}