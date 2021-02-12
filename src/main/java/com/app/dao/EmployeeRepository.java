package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmpId(int id);

	Optional<Employee> findByEmail(String email);

	List<Employee> findByStatus(boolean status);
	
	Optional<Employee> findByEmpIdAndStatus(Integer id, boolean status);
}
