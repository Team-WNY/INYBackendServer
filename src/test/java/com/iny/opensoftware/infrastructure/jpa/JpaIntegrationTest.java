package com.iny.opensoftware.infrastructure.jpa;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import com.iny.opensoftware.infrastructure.jpa.Service.TestEmployeeService;
import com.iny.opensoftware.infrastructure.jpa.entity.TestEmployee;

/**
 * TODO: JPA 테스트 코드(삭제 예정)
 */

@SpringBootTest
public class JpaIntegrationTest {
    @Autowired
    private TestEmployeeService employeeService;

    @Test
    public void testJpaIntegration() {
        // 직원 정보 저장
        TestEmployee employee1 = new TestEmployee("John", 30);
        employeeService.saveEmployee(employee1);

        TestEmployee employee2 = new TestEmployee("Alice", 25);
        employeeService.saveEmployee(employee2);

        // 모든 직원 정보 조회
        List<TestEmployee> employees = employeeService.getAllEmployees();
        Assert.assertEquals(2, employees.size());

        // ID로 직원 정보 조회
        Long employeeId = employee1.getId();
        TestEmployee retrievedEmployee = employeeService.getEmployeeById(employeeId);
        Assert.assertEquals("John", retrievedEmployee.getName());

        // ID로 직원 정보 삭제
        employeeService.deleteEmployeeById(employeeId);

        // 삭제 후 모든 직원 정보 조회
        employees = employeeService.getAllEmployees();
        Assert.assertEquals(1, employees.size());
    }
}

