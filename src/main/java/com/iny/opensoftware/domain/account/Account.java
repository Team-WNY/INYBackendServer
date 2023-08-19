package com.iny.opensoftware.domain.account;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.iny.opensoftware.domain.account.auth.Authorize;
import com.iny.opensoftware.domain.account.heart.Heart;
import com.iny.opensoftware.domain.account.profile.UserProfile;
import com.iny.opensoftware.domain.common.DomainEntity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@DomainEntity
public class Account {
	private AccountId id;
	private String accountId;
	private String password;
	private UserProfile profile;
	private Heart heart;
	private Integer follower;
	private Integer following;
	private Integer uploadAmiCount;
	private Authorize auth;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(AccountRepository repository) {
		Assert.notNull(this.accountId, "계정의 ID 값도 필수 값입니다.");
		
		repository.save(this);
	}
	

}
