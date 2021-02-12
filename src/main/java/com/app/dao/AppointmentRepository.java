package com.app.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Appointment;
import com.app.pojos.Customer;
import com.app.pojos.Employee;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	Optional<Appointment> findByAppId(int id);

	List<Appointment> findByAppDateAndEmployee(LocalDate appDate, Employee emp);

	List<Appointment> findByCustomer(Customer c);
}
