package com.iny.opensoftware.infrastructure.jpa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iny.opensoftware.infrastructure.jpa.entity.TestEmployee;
import com.iny.opensoftware.infrastructure.jpa.repository.TestEmployeeRepository;

/**
 * TODO: JPA 테스트 코드(삭제 예정)
 */

@Service
public class TestEmployeeService {
	@Autowired
    private TestEmployeeRepository employeeRepository;

    // 직원 정보 저장
    public TestEmployee saveEmployee(TestEmployee employee) {
        return employeeRepository.save(employee);
    }

    // 모든 직원 정보 조회
    public List<TestEmployee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // ID로 직원 정보 조회
    public TestEmployee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // ID로 직원 정보 삭제
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
