package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Payment;
import com.app.service.PaymentService;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	//To add payment 
	@PostMapping("/register")
	public ResponseEntity<?> addPayment(@RequestBody Payment payment) {
		System.out.println(payment);
		try {

			return new ResponseEntity<>(paymentService.addPayment(payment), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

	}
}
