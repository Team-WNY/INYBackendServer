package com.iny.opensoftware.application.exception;

public class AccountVerifyFailException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public AccountVerifyFailException() {
		super("인증값이 맞지 않습니다.");
	}

}
