package com.neaterbits.query.jpatest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

	private long id;
	private String name;

	public Role() {
		
	}
	
	public Role(Integer id, String name) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

