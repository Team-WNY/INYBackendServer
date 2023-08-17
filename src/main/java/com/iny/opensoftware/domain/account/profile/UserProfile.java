package com.iny.opensoftware.domain.account.profile;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
	String name;
	String nickName;
	Instant birthDay;
	String email;
	String address;
}
