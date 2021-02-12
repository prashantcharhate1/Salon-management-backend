package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private int paymentId;
	
	@Column(name = "payment_mode")
	private PayMode modeOfPayment;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@Column(name = "payment_amount")
	private double paymentAmount;
	
	@Column(name = "payment_status")
	private boolean paymentStatus;
	
	@Column(name = "app_id")
	private int appId; 
	
	public Payment() {
		// TODO Auto-generated constructor stub
	}

	public Payment(PayMode modeOfPayment, LocalDate paymentDate,double paymentAmount,boolean paymentStatus, int appId) {
		super();
		this.modeOfPayment = modeOfPayment;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.paymentStatus = paymentStatus;
		this.appId = appId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public PayMode getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(PayMode modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}


	

}
