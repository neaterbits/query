package com.neaterbits.query.test.model.ownership;

import java.util.List;

import javax.persistence.ManyToMany;

import com.neaterbits.query.test.model.Person;

/**
 *  For "sameie", collective ownership
 *
 */

public class OwnerGroup {

	private List<Person> persons;

	
	@ManyToMany // one group can be many persons and one person can be in many groups
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}
