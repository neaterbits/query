package com.neaterbits.query.jpatest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Company {

	private long id;
	private String name;
	
	public Company() {
		
	}
	
	public Company(long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
