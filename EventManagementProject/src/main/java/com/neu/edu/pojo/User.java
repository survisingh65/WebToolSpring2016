package com.neu.edu.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="usertbl")
@PrimaryKeyJoinColumn(name="personid")
public class User extends Person {
	
  @Column(name="emailid")
  private String email;
  
  @Column(name="password")
  private String password;
  
  @Column(name="status")
  private String status;
  
  public User(String email, String password, String status){
	  this.email = email;
	  this.password = password;
	  this.status = status;
  }
  
  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name="user_events", 
              joinColumns={@JoinColumn(name="personid")}, 
              inverseJoinColumns={@JoinColumn(name="eventid")})
  private Set<Event> events = new HashSet<Event>();
  
  public Set<Event> getEvents() {
	return events;
}

public void setEvents(Set<Event> events) {
	this.events = events;
}

public User(){
	  
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
