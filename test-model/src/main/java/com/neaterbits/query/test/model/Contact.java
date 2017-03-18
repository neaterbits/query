package com.neaterbits.query.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.neaterbits.query.test.model.enums.ContactType;

@Entity
public class Contact {

	private Long id;
	private ContactType type;
	private String value;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	public ContactType getType() {
		return type;
	}

	public void setType(ContactType type) {
		this.type = type;
	}

	@Column
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
