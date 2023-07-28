package com.iny.opensoftware.infrastructure.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TODO: Mybatis 테스트 코드(삭제 예정)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestEmployeeObject {
	Long id;
	String name;
	int age;
}