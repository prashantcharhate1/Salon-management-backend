package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ServiceRepository;
import com.app.pojos.Service;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/service")
public class ServiceController {

	
	@Autowired
	private ServiceRepository serviceRepository;
	
	//To get all services
	@GetMapping("/getAllServices")
	public ResponseEntity<?> getAllServices() {
		try {
			List<Service> serviceList = serviceRepository.findAll();
			if(serviceList.isEmpty())
			{
				return new ResponseEntity<>("No service in database",HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(serviceList, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

	}
	
	//To get service by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getServiceById(@PathVariable Integer id) {
		Optional<Service> service = serviceRepository.findByServiceId(id);
		if (service.isPresent()) {
			return new ResponseEntity<>(service, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

}
