package com.neaterbits.query.sql.dsl.api;

public class CompanyPersonResultVO {
	private Long companyId;
	private Long personId;
	private String firstName;
	private String lastName;
	
	CompanyPersonResultVO() {
		
	}
	
	public CompanyPersonResultVO(long companyId, long personId, String firstName, String lastName) {
		this.companyId = companyId;
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyPersonResultVO other = (CompanyPersonResultVO) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompanyPersonResultVO [companyId=" + companyId + ", personId=" + personId + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}
}
