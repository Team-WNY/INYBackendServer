package com.iny.opensoftware.domain.account;

import java.util.List;

public interface AccountRepository {
	void save(Account account);
	Account getOneAccountById(Long id);
	Account getOneAccountByAccountId(String accountId);
	List<Account> getAllAccount();
	Boolean isAccount(String accountId);
	String getAccountIdByEmail(String email);
	Boolean deleteAccount(String accountId);
}
