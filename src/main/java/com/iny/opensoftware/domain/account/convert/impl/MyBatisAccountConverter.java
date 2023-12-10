package com.iny.opensoftware.domain.account.convert.impl;

import com.iny.opensoftware.domain.account.Account;
import com.iny.opensoftware.domain.account.AccountFactory;
import com.iny.opensoftware.domain.account.AccountId;
import com.iny.opensoftware.domain.account.auth.Authorize;
import com.iny.opensoftware.domain.account.convert.AccountConverter;
import com.iny.opensoftware.domain.account.heart.Heart;
import com.iny.opensoftware.domain.account.profile.UserProfile;
import com.iny.opensoftware.infrastructure.mybatis.dto.AccountObject;

public class MyBatisAccountConverter implements AccountConverter<AccountObject> {
	
	private final AccountFactory factory = new AccountFactory();
	
	@Override
	public Account convert(AccountObject obj) {
		Account a = this.factory.getInstance();
		
		a.setId(AccountId.of(obj.getId()));
		a.setAccountId(obj.getAccountId());
		a.setPassword(obj.getPassword());
		a.setProfile(new UserProfile(obj.getName(), obj.getNickName(), obj.getBirthDay().toInstant(), obj.getPhoneNumber(),  obj.getEmail(), obj.getAddress()));
		a.setHeart(new Heart(obj.getBlackHeart(), obj.getRedHeart()));
		a.setFollower(obj.getFollower());
		a.setFollowing(obj.getFollowing());
		a.setUploadAmiCount(obj.getUploadAmiCount());
		a.setAuth(Authorize.valueOf(obj.getAuth()));
		a.setDelType(obj.isDelType());
		
		return a;
	}
	
}
