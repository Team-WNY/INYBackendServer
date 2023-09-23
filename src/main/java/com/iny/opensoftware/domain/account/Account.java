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
	
	/**
	 * 계정 신규 생성 및 계정 정보 수정
	 * @param repository
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(AccountRepository repository) {
		Assert.notNull(this.accountId, "계정의 ID 값도 필수 값입니다.");
		
		repository.save(this);
	}
	
	
	/**
	 * 이미 존재하는 계정인지 체크
	 * @param repository
	 * @param accountId
	 * @return
	 */
	public Boolean checkAccount(AccountRepository repository) {
		Assert.notNull(this.accountId, "계정의 ID 값도 필수 값입니다.");
		
		return repository.isAccount(this.accountId);
	}
	
	/**
	 * 계정 정보 가져오기(By AccountId)
	 * @param repository
	 * @param accountId
	 * @return
	 */
	public void fetchByAccountId(AccountRepository repository) {
		Account stored = repository.getOneAccountByAccountId(this.accountId);
		
		this.setId(stored.getId());
		this.setAuth(stored.getAuth());
		this.setProfile(stored.getProfile());
		this.setHeart(stored.getHeart());
		this.setFollower(stored.getFollower());
		this.setFollowing(stored.getFollowing());
		this.setUploadAmiCount(stored.getUploadAmiCount());
	}

	/**
	 * 계정 정보 가져오기(By Id)
	 * @param repository
	 * @param Id
	 * @return
	 */
	public void fetchById(AccountRepository repository) {
		Account stored = repository.getOneAccountById(this.id.getValue());
		
		this.setAccountId(stored.getAccountId());
		this.setAuth(stored.getAuth());
		this.setProfile(stored.getProfile());
		this.setHeart(stored.getHeart());
		this.setFollower(stored.getFollower());
		this.setFollowing(stored.getFollowing());
		this.setUploadAmiCount(stored.getUploadAmiCount());
	}
	
	/**
	 * 팔로워 증가
	 * @param repository
	 * @return
	 */
	public Integer followerUp(AccountRepository repository) {
		Assert.notNull(this.follower, "팔로워는 null일 수 없습니다.");
		
		this.follower += 1;
		repository.save(this);
		
		return this.follower;
	}
	
	/**
	 * 팔로워 감소
	 * @param repository
	 * @return
	 */
	public Integer followerDown(AccountRepository repository) {
		Assert.notNull(this.follower, "팔로워는 null일 수 없습니다.");
		
		this.follower -= 1;
		repository.save(this);
		
		return this.follower;
	}
	
	/**
	 * 팔로윙 증가
	 * @param repository
	 * @return
	 */
	public Integer followingUp(AccountRepository repository) {
		Assert.notNull(this.following, "팔로윙은 null일 수 없습니다.");
		
		this.following += 1;
		repository.save(this);
		
		return this.following;
	}
	
	/**
	 * 팔로윙 감소
	 * @param repository
	 * @return
	 */
	public Integer followingDown(AccountRepository repository) {
		Assert.notNull(this.following, "팔로윙은 null일 수 없습니다.");
		
		this.following -= 1;
		repository.save(this);
		
		return this.following;
	}
	
	/**
	 * Am I 카운트 증가
	 * @param repository
	 * @return
	 */
	public Integer upLoadAmiCountUp(AccountRepository repository) {
		Assert.notNull(this.uploadAmiCount, "Am I 개수 카운트는 null일 수 없습니다.");
		
		this.uploadAmiCount += 1;
		repository.save(this);
		
		// TODO : Board 완성되면 이 부분에서 Board랑 연결이 필요할 것으로 보인다.
		
		return this.uploadAmiCount;
	}
	
	/**
	 * Am I 카운트 감소
	 * @param repository
	 * @return
	 */
	public Integer upLoadAmiCountDown(AccountRepository repository) {
		Assert.notNull(this.uploadAmiCount, "Am I 개수 카운트는 null일 수 없습니다.");
		
		this.uploadAmiCount -= 1;
		repository.save(this);
		
		// TODO : Board 완성되면 이 부분에서 Board랑 연결이 필요할 것으로 보인다.
		
		return this.uploadAmiCount;
	}
	
	/**
	 * 계정 인증 완료 후, 계정의 권한 변경
	 * @param repository
	 */
	public void authorizeAccount(AccountRepository repository ) {
		Assert.notNull(this.auth, "권한은 null일 수 없습니다.");
		
		this.setAuth(Authorize.AUTH);
		repository.save(this);
	}
	
	/**
	 * 로그인 시, 계정 체크
	 * @param repository
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean authenticateAccount(AccountRepository repository, String username, String password) {
		Assert.notNull(this.accountId, "계정 Id는 null일 수 없습니다.");
		Assert.notNull(this.password, "계정 password는 null일 수 없습니다.");
		
		if(this.accountId.equals(username) && this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Email값을 통해서 AccountID 반환
	 * @param repository
	 * @return
	 */
	public String findAccountId(AccountRepository repository) {
		return repository.getAccountIdByEmail(this.profile.getEmail());
	}
	
	
	// TODO : Heart 관련 기획 확정 후 개발 진행
	
	
}
