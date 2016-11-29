package com.neaterbits.query.sql.dsl.api;

public class ResultVO {
    private Long companyId;
    private Long personId;
    private String firstName;
    private String lastName;
    private String roleName;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
