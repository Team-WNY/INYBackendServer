package com.iny.opensoftware.presentation.api.v1.account.obj;

import lombok.Data;

@Data
public class SignUpObject {
	
	private String accountId;
	
	private String password;
	
	private String name;
	
	private String nickName;
	
	private String birthDay;
	
	private String phoneNumber;
	
	private String email;
	
	private String address;

}
