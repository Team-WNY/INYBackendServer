package com.iny.opensoftware.application.exception;

public class AccountFindFailedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AccountFindFailedException() {
		super("계정이 없습니다.");
	}

}
