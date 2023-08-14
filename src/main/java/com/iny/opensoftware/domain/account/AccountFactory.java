package com.iny.opensoftware.domain.account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountFactory {
	
	public Account getInstance() { return new Account(); }
}
