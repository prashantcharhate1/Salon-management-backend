package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
	
	Optional<Service> findByServiceId(int id);

}
