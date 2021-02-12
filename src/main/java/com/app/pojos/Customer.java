package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private int custId;

	@Column(name = "cust_name")
	private String custName;

	@Column(name = "email_id" , unique = true)
	private String email;

	private String password;

	private Gender gender;
	
	//status is provided to mark customer as deleted 
	//If status is false then Customer is marked as delete
	@Column
	private boolean status;

	@Column(name = "contact_no", length = 10)
	private String contactNo;

	public Customer() {
		System.out.println("in constructor " + getClass().getName());
	}

	public Customer(String custName, String email, String password, Gender gender, String contactNo) {
		super();
		this.custName = custName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.contactNo = contactNo;
		this.status = true;
	}
	
	public Customer(int customer, Object object, Object object2, Object object3, Object object4, Object object5) {
		custId = customer;
	}


	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", email=" + email + ", password=" + password
				+ ", gender=" + gender + ", contactNo=" + contactNo + "]";
	}

	

}
