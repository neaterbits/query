package com.neaterbits.query.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Base class for all animals
 * @author nhl
 *
 */


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Animal {


	private Long id;
	
	// For old style name animals (Dagros, Ferdinand), for demo purposes
	private String name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
