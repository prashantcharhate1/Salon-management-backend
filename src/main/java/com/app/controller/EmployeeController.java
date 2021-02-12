package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.EmployeeRepository;
import com.app.pojos.Employee;
import com.app.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmployeeRepository employeeRepository;

	// To fetch employee by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable Integer id) {
		Optional<Employee> employee = employeeService.getEmployeeByIdAndStatus(id);
		if (employee.isPresent()) {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// To fetch all employees
	@GetMapping("/getAllEmployees")
	public ResponseEntity<?> getAllEmployees() {
		try {
			List<Employee> empList = employeeService.getAllEmployees();
			if (empList.isEmpty()) {
				return new ResponseEntity<>("No employee in database", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(empList, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

	}

	// To fetch all valid employees
	@GetMapping("/getAllEmployeesByStatus")
	public ResponseEntity<?> getAllEmployeesByStatus() {
		try {
			List<Employee> empList = employeeService.getAllEmployeesByStatus();
			if (empList.isEmpty()) {
				return new ResponseEntity<>("No employee in database", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(empList, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}

	// To update employee's info
	@PostMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		System.out.println(employee);
		try {

			return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}

	// To delete employee by id
	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable int id) {
		System.out.println(id);
		try {
			Optional<Employee> emp = employeeService.getEmployeeInfo(id);
			emp.get().setStatus(false);
			employeeService.updateEmployee(emp.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To register employee with image file
	@PostMapping("/registerWithImage")
	public ResponseEntity<?> fileUploadWithParams(@RequestParam("employee") String emp,
			@RequestParam("imageFile") MultipartFile imageFile) {
		try {

			// To convert string data of employee into employee object
			Employee employee = new ObjectMapper().readValue(emp, Employee.class);

			// To encrypt the password using bCrypt so that it should not be exposed
			// to anyone (especially database handling guy)
			employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
			employee.setStatus(true);
			employee.setPicByte(imageFile.getBytes());
			employee.setType(imageFile.getContentType());

			employeeRepository.save(employee);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Email already exists", HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
