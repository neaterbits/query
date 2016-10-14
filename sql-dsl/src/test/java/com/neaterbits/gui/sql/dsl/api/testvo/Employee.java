package com.neaterbits.gui.sql.dsl.api.testvo;

public class Employee {

	private final Integer companyId;
	private final String personId;
	
	public Employee(Integer companyId, String personId) {
		this.companyId = companyId;
		this.personId = personId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public String getPersonId() {
		return personId;
	}
}
