package com.neaterbits.query.test.model;

import javax.persistence.MappedSuperclass;

// Base for anything enumerated, always mapped super class

@MappedSuperclass
public class Enumerated {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
