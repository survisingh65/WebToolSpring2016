package com.neu.edu.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="preferencetbl")
public class Preference {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="p_id" )
    private int pId;
	
	@Column(name="pcategory")
    private String preferenceCategory;

	@ManyToOne
	@JoinColumn(name="personid")
	private Person person;
	
	public Preference(){
		
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getPreferenceCategory() {
		return preferenceCategory;
	}

	public void setPreferenceCategory(String preferenceCategory) {
		this.preferenceCategory = preferenceCategory;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
