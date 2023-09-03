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
@Alias("BoardObject")
public class BoardObject {
	
	private long id;
	
	private String title;
	
	private String contents;
	
	private String register;
	
	private Timestamp upLoadTime;
	
	private String location;
	
	private String roomStatus;
	
	private String roomType;
	
	private String delType;
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;
}
