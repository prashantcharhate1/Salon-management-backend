package com.app.pojos;

import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import custom_deserilazer.AppointmentDeserializer;
import custom_deserilazer.SqlTimeDeserializer;

@Entity
@Table(name = "appointments")
//To deserialize the appointment object coming from front-end we created custom deserializer
@JsonDeserialize(using = AppointmentDeserializer.class)
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "app_id")
	private int appId;

	//To serialize & deserialize the localDate
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@Column(name = "app_date")
	private LocalDate appDate;

	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	@Column(name = "app_time")
	private Time appTime;

	//One appointment have one service
	@OneToOne
	@JoinColumn(name = "service_id")
	private Service service;

	//One appointment have one employee
	@OneToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;

	//One appointment have one customer
	@OneToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;

	public Appointment() {
	}

	public Appointment(LocalDate appDate, Time appTime, Service service, Employee employee, Customer customer) {
		super();
		this.appDate = appDate;
		this.appTime = appTime;
		this.service = service;
		this.employee = employee;
		this.customer = customer;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public LocalDate getAppDate() {
		return appDate;
	}

	public void setAppDate(LocalDate appDate) {
		this.appDate = appDate;
	}

	public Time getAppTime() {
		return appTime;
	}

	public void setAppTime(Time appTime) {
		this.appTime = appTime;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
