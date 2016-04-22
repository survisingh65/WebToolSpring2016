package com.neu.edu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="admintbl")
@PrimaryKeyJoinColumn(name="personid")
public class Admin extends Person {
	
	@Column(name="emailid")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="status")
	private String status;

	public Admin() {

	}

	public Admin(String email, String password, String status){
		  this.email = email;
		  this.password = password;
		  this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
