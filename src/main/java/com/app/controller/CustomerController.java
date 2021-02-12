package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Customer;
import com.app.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	//To fetch customer by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable Integer id) {
		Optional<Customer> customer = customerService.getCustomerByIdAndStatus(id);
		if (customer.isPresent()) {
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//To register new customer
	@PostMapping("/register")
		public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
			System.out.println(customer);
			try {
				System.out.println("In add customer");
				customer.setStatus(true);
				customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
				return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
	
			} catch (Exception e) {
				return new ResponseEntity<>("Email already exists",HttpStatus.NOT_ACCEPTABLE);
			}
		}
		
		//To fetch all customers
		@GetMapping("/getAllCustomers")
		public ResponseEntity<?> getAllCustomers() {
			try {
				List<Customer> custList = customerService.getAllCustomers();
				if (custList.isEmpty()) {
					return new ResponseEntity<>("No customer in database", HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(custList, HttpStatus.OK);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
		
		
		//To fetch all valid customers 
		@GetMapping("/getAllCustomersByStatus")
		public ResponseEntity<?> getAllCustomersByStatus() {
			try {
				List<Customer> custList = customerService.getAllCustomersByStatus();
				if (custList.isEmpty()) {
					return new ResponseEntity<>("No customer in database", HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(custList, HttpStatus.OK);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	
		}

	//To update customer info
	@PostMapping("/update")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		System.out.println(customer);
		try {

			return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

	}

	//To delete customer by
	@DeleteMapping("/delete/{id}")
	public void deleteCustomer(@PathVariable int id) {
		System.out.println(id);
		try {
			Optional<Customer> c = customerService.getCustomerInfo(id);
			c.get().setStatus(false);
			customerService.updateCustomer(c.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
