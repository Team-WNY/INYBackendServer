package com.iny.opensoftware.infrastructure.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: JPA 테스트 코드(삭제 예정)
 */

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class TestEmployee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String name;
    private int age;
    
    public TestEmployee(String name, int age) {
    	this.setName(name);
    	this.setAge(age);
    }
}
