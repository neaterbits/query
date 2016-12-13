package com.neaterbits.query.jpatest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
	
	private long id;
	private long companyId;
	private long personId;
	
	public Employee() {
		
	}
	
	public Employee(long id, long companyId, long personId) {
		this.id = id;
		this.companyId = companyId;
		this.personId = personId;
	}

	@Id @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column
	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@Column
	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}
}
