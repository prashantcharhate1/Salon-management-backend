package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private int empId;

	@Column(name = "emp_name")
	private String empName;

	@Column(name = "email_id" , unique = true)
	private String email;

	private String password;

	@Column(name = "type")
	private String type;

	@Lob
	@Column(name = "pic_byte", length = 1000)
	private byte[] picByte;

	private String address;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	private Gender gender;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@Column(name = "join_date")
	private LocalDate joinDate;

	@Column(name = "experience")
	private int experience;

	@Column(name = "contact_no", length = 10)
	private String contactNo;

	private Role role;

	// status is provided to mark employee as deleted
	// If status is false then employee is marked as delete
	@Column
	private boolean status;

	public Employee() {
		System.out.println("in constructor " + getClass().getName());
	}

	public Employee(String empName, String email, String password, String type, byte[] picByte, String address,
			LocalDate dateOfBirth, Gender gender, LocalDate joinDate, int experience, String contactNo, Role role) {
		super();
		this.empName = empName;
		this.email = email;
		this.password = password;
		this.type = type;
		this.picByte = picByte;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.joinDate = joinDate;
		this.experience = experience;
		this.contactNo = contactNo;
		this.status = true;
		this.role = role;
	}

	public Employee(int employee, Object object, Object object2, Object object3, Object object4, Object object5,
			Object object6, Object object7, Object object8, Object object9, Object object10, Object object11,
			Object object12, Object object13) {

		empId = employee;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
