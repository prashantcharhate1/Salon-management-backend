package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Optional<Customer> findByCustId(int id);
	
	Optional<Customer> findByEmail(String email);

	List<Customer> findByStatus(boolean status);
	
	Optional<Customer> findByCustIdAndStatus(Integer id, boolean status);
}
