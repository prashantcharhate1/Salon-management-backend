package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AppointmentRepository;
import com.app.pojos.Appointment;
import com.app.pojos.Customer;
import com.app.pojos.Employee;

@Service
@Transactional
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	public Appointment addAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	public Optional<Appointment> getAppointmentInfo(Integer id) {
		return appointmentRepository.findByAppId(id);
	}

	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

	public Appointment updateAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	public void deleteAppointment(int id) {
		appointmentRepository.deleteById(id);
	}

	public List<Appointment> getAppointmentsByDateAndEmployee(LocalDate appDate, Employee emp) {
		return appointmentRepository.findByAppDateAndEmployee(appDate, emp);
	}

	public List<Appointment> getAppointmentsByCustomer(Customer customer) {
		return appointmentRepository.findByCustomer(customer);
	}
}
