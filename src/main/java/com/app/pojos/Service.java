package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "services")
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private int serviceId;

	@Column(name = "service_name")
	private String serviceName;
	
	@Column(name = "service_rate")
	private double serviceRate;

	public Service() {
	}
	
	public Service(String serviceName, double serviceRate) {
		super();
		this.serviceName = serviceName;
		this.serviceRate = serviceRate;
	}	

	public Service(int serviceId, String serviceName, double serviceRate) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.serviceRate = serviceRate;
	}

	public Service(int service, Object object, Object object2) {
		serviceId = service;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getServiceRate() {
		return serviceRate;
	}

	public void setServiceRate(double serviceRate) {
		this.serviceRate = serviceRate;
	}
	
	
	

}
