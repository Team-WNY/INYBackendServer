package com.iny.opensoftware.domain.account;

import java.util.List;

public interface AccountRepository {
	void save();
	void updateById();
	Account getOneAccountById();
	Account getOneAccountByAccountId();
	List<Account> getAllAccount();
}
