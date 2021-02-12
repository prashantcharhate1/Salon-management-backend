package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CustomerRepository;
import com.app.pojos.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Optional<Customer> getCustomerInfo(Integer id) {
		return customerRepository.findByCustId(id);
	}

	public Optional<Customer> getCustomerByIdAndStatus(Integer id) {
		return customerRepository.findByCustIdAndStatus(id, true);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
		;
	}

	public List<Customer> getAllCustomersByStatus() {
		return customerRepository.findByStatus(true);
	}

}
