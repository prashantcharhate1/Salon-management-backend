package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.JwtTokenUtil;
import com.app.dao.CustomerRepository;
import com.app.dao.EmployeeRepository;
import com.app.pojos.Customer;
import com.app.pojos.Employee;
import com.app.pojos.JwtRequest;
import com.app.pojos.JwtResponse;
import com.app.service.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/authenticate")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		System.out.println("In create token");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		Optional<Customer> c = custRepo.findByEmail(authenticationRequest.getUsername());

		JwtResponse jwtResponse = new JwtResponse(token, c.get().getCustId());
		return ResponseEntity.ok(jwtResponse);

	}

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTokenForEmployee(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		System.out.println("In create token");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		Optional<Employee> e = empRepo.findByEmail(authenticationRequest.getUsername());

		JwtResponse jwtResponse = new JwtResponse(token, e.get().getEmpId(), e.get().getRole());
		return ResponseEntity.ok(jwtResponse);

	}

	private void authenticate(String email, String password) throws Exception {
		try {
			System.out.println("In authenticate");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
