package com.iny.opensoftware.domain.account.profile;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
	private String name;
	private String nickName;
	private Instant birthDay;
	private String phoneNumber;
	private String email;
	private String address;
}
