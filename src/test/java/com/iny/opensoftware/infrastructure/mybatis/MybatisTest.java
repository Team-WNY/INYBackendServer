package com.iny.opensoftware.infrastructure.mybatis;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iny.opensoftware.infrastructure.mybatis.dto.TestEmployeeObject;
import com.iny.opensoftware.infrastructure.mybatis.mapper.TestEmployeeMapper;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO: Mybatis 테스트 코드(삭제 예정)
 * '@RequiredArgsConstructor' 사용 불가 '@Autowired'이용할 것
 */

@SpringBootTest
@Slf4j
public class MybatisTest {
	
	@Autowired
	private TestEmployeeMapper mapper;
	
	@PostConstruct
	public void init() {
		
	}
	
	@Test
	public void MybatisEmployeeTest() {
		TestEmployeeObject emp1 = this.mapper.selectEmployeeById(2L);
		
		log.info("Employee : " + emp1.toString());
	}
}
