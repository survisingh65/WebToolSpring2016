package com.neu.edu.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;





@Entity
@Table(name="eventtbl")
public class Event {
	
	@Id
    @Column(name="eventid")
	private double eventId;
	
	@Column(name="eventname")
	private String eventName;
	
	@Column(name="time")
	private String time;
	
	@Column(name="description")
	private String description;
	
	@Column(name="venue")
	private String venue;
	
	@Column(name="img", length = 8000)
	private String img;
		
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	@ManyToMany(mappedBy="events")
    private Set<User> users = new HashSet<User>();
	
	public Event() {
		
	}
	
	
	
	public double getEventId() {
		return eventId;
	}
	public void setEventId(double eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	

}
