package com.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Appointment;
import com.app.pojos.Customer;
import com.app.pojos.Employee;
import com.app.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
//To avoid CORS policy error 
@CrossOrigin
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	// To add new appointment
	@PostMapping("/bookAppointment")
	public ResponseEntity<?> addAppointment(@RequestParam("appointment") String appointment) {
		System.out.println(appointment);
		try {
			Appointment app = new ObjectMapper().readValue(appointment, Appointment.class);
			return new ResponseEntity<>(appointmentService.addAppointment(app), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}

	// To fetch appointment by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getAppointment(@PathVariable Integer id) {
		Optional<Appointment> appointment = appointmentService.getAppointmentInfo(id);
		if (appointment.isPresent()) {
			return new ResponseEntity<>(appointment, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// To fetch appointments by customer
	@PostMapping("/getByCustomer")
	public ResponseEntity<?> getAppointmentsByCustomer(@RequestBody Customer customer) {
		List<Appointment> appointments = appointmentService.getAppointmentsByCustomer(customer);
		if (appointments.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}
	
	// To fetch all appointments info
	@GetMapping("/getAllAppointments")
	public ResponseEntity<?> getAllAppointments() {
		try {
			List<Appointment> aptList = appointmentService.getAllAppointments();
			if (aptList.isEmpty()) {
				return new ResponseEntity<>("No Appointment in database", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(aptList, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}

	// To fetch appointments of employee at given date
	@PostMapping("/getAppointmentsByDateAndEmployee/{appDate}")
	public ResponseEntity<?> getAppointmentsByDateAndEmployee(@PathVariable String appDate, @RequestBody Employee emp) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String to LocalDate
			LocalDate appDate2 = LocalDate.parse(appDate, formatter);
			List<Appointment> aptList = appointmentService.getAppointmentsByDateAndEmployee(appDate2, emp);
			if (aptList.isEmpty()) {
				return new ResponseEntity<>("No Appointment in database", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(aptList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

	}

	// To update appointment
	@PostMapping("/update")
	public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) {
		System.out.println(appointment);
		try {

			return new ResponseEntity<>(appointmentService.updateAppointment(appointment), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

	}

	// To delete appointment by id
	@DeleteMapping("/delete/{id}")
	public void deleteAppointment(@PathVariable int id) {
		System.out.println(id);
		try {
			appointmentService.deleteAppointment(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
