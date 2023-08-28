package com.iny.opensoftware.application.account;

import com.iny.opensoftware.application.exception.AccountFindFailedException;
import com.iny.opensoftware.application.exception.AccountVerifyFailException;
import com.iny.opensoftware.presentation.api.v1.account.obj.SignUpObject;

public interface LoginService {
	
	/**
	 * 중복 체크
	 */
	public Boolean accountDuplication(String accountId);
	
	/**
	 * 이메일 인증
	 */
	public void emailAuth(String email, String code) throws AccountVerifyFailException;
	
	/**
	 * 회원 가입
	 */
	public void signUp(SignUpObject data);
	
	/**
	 * 아이디 찾기
	 */
	public String accountFind(String email);
	
	/**
	 * 임시 비번 발급
	 */
	public String passwordFind(String accountId,  String email, String code) throws AccountFindFailedException;
	
	/**
	 * 패스워드 변경
	 */
	public void changePassword(String accountId, String passowrd);
}
