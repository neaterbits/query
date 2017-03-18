package com.neaterbits.query.test.model.ownership;

import java.util.List;

import javax.persistence.OneToMany;

import com.neaterbits.query.test.model.Person;

/**
 *  For "sameie", collective ownership
 *
 */

public class OwnerGroup {

	private List<Person> persons;

	
	@OneToMany
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}
