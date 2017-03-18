package com.neaterbits.query.test.model.ownership;

import javax.persistence.OneToOne;

import com.neaterbits.query.test.model.Person;

/**
 * When the owner is a single person
 * @author nhl
 *
 */

public class OwnerPerson extends Owner {

	
	private Person person;

	@OneToOne
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
