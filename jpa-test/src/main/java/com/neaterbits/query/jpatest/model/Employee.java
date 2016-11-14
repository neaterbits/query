package com.neaterbits.query.jpatest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
	
	private long companyId;
	private String personId;
	
	public Employee() {
		
	}
	
	public Employee(long companyId, String personId) {
		this.companyId = companyId;
		this.personId = personId;
	}

	@Id @GeneratedValue
	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
}
