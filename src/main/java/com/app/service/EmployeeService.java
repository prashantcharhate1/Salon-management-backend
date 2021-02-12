package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.EmployeeRepository;
import com.app.pojos.Employee;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> getEmployeeInfo(Integer id) {
		return employeeRepository.findByEmpId(id);

	}
	
	public Optional<Employee> getEmployeeByIdAndStatus(Integer id) {
		return employeeRepository.findByEmpIdAndStatus(id, true);

	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);	
	}
	
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);;
	}
	
	public List<Employee> getAllEmployeesByStatus() {
		return employeeRepository.findByStatus(true);
	}
}
