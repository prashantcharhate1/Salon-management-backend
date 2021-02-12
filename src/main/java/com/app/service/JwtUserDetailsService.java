package com.app.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.CustomerRepository;
import com.app.dao.EmployeeRepository;
import com.app.pojos.Customer;
import com.app.pojos.Employee;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository custService;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	//To store user information
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Customer> cust = custService.findByEmail(email);
		Optional<Employee> emp = employeeRepository.findByEmail(email);
		
		System.out.println("In loadUserByUsername()"+email);
		
		//Checking if customer is present and not deleted
		if (cust.isPresent() && cust.get().isStatus()) {
			System.out.println("Customer fetched : "+cust.get());
			return new User(cust.get().getEmail(),cust.get().getPassword(),
					new ArrayList<>());
		} 
		//Checking if employee is present and not deleted
		else if(emp.isPresent() && emp.get().isStatus())
		{
			System.out.println("Employee fetched : "+emp.get());
			return new User(emp.get().getEmail(),emp.get().getPassword(),
					new ArrayList<>());
		}
		
		else {
			System.out.println("In loadUserByUsername() else clause");
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
	}
	
}
