package com.neaterbits.query.test.model;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Base class for all animals
 * @author nhl
 *
 */


@Inheritance(strategy=InheritanceType.JOINED)
public class Animal {

	private 
	
	// For old style name animals (Dagros, Ferdinand), for demo purposes
	private String name;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
