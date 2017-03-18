package com.neaterbits.query.test.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.OneToMany;

public class Person {

	private String firstName;
	
	private String lastName;
	
	private Collection<Contact> contacts;

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToMany
	public Collection<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}
}
