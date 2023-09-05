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
@Alias("CommentObject")
public class CommentObject {
	
	private long id;
	
	private long boardId;
	
	private String commentRegister;
	
	private String commentContents;

	private Timestamp createdAt;
	
	private Timestamp updatedAt;
}
