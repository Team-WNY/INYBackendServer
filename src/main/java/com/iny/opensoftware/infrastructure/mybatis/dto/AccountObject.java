package com.iny.opensoftware.infrastructure.mybatis.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Alias("AccountObject")
public class AccountObject {
	
	private Long id;
	
	private String accountId;
	
	private String password;
	
	private String name;
	
	private String nickName;
	
	private Timestamp birthDay;
	
	private String email;
	
	private String address;
	
	private String phoneNumber;
	
	private Integer blackHeart;
	
	private Integer redHeart;
	
	private Integer follower;
	
	private Integer following;
	
	private Integer uploadAmiCount;
	
	private String auth;
	
	private boolean delType;
	
	private Timestamp createAt;
	
	private Timestamp updateAt;

}
