package com.neaterbits.query.sql.dsl.api._short;

public class NameLength {
	private String name;
	private Integer length;
	private Double lengthSqrt;

	public NameLength() {

	}
	
	public NameLength(String name) {
		super();
		this.name = name;
	}

	NameLength(String name, Integer  length) {
		super();
		this.name = name;
		this.length = length;
	}
	
	public NameLength(String name, Double lengthSqrt) {
		super();
		this.name = name;
		this.lengthSqrt = lengthSqrt;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Double getLengthSqrt() {
		return lengthSqrt;
	}

	public void setLengthSqrt(Double lengthSqrt) {
		this.lengthSqrt = lengthSqrt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((lengthSqrt == null) ? 0 : lengthSqrt.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		NameLength other = (NameLength) obj;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (lengthSqrt == null) {
			if (other.lengthSqrt != null)
				return false;
		} else if (!lengthSqrt.equals(other.lengthSqrt))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NameLength [name=" + name + ", length=" + length + "]";
	}
}
