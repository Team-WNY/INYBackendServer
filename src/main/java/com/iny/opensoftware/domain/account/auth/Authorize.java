package com.iny.opensoftware.domain.account.auth;

public enum Authorize {
	AUTH(0, "인증된 사용자"),
	NONAUTH(-1, "인증되지 사용자"),
	ADMIN(1, "운영자");
	
	private final Integer code;
	private final String comment;

	Authorize(Integer code, String comment) {
		this.code = code;
		this.comment = comment;
	}
	
	public static Authorize valueOfCode(Integer code) {
		for(Authorize auth : values()) {
			if(auth.code.equals(code)) {
				return auth;
			}
		}
		throw new IllegalArgumentException("코드가 일치하지 않습니다.");
	}
	
	public Integer getCode() {
		return this.code;
	}
}