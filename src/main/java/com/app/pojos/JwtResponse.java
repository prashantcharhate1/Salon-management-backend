package com.app.pojos;

import java.io.Serializable;

//To send the response packet to the front-end with JwtToken and other info
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private int custId;
	private Role role;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public JwtResponse(String token, int custId) {
		this.jwttoken = token;
		this.custId = custId;
	}
	public JwtResponse(String token, int custId , Role role) {
		this.jwttoken = token;
		this.custId = custId;
		this.role = role;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public int getCustId() {
		return custId;
	}

	public Role getRole() {
		return role;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	
	
	
}
