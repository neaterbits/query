package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public class Foo {
	
	
	public Foo() {
		
	}
	
	public Foo(int value, int value2, BigDecimal decimal) {
		this.value = value;
		this.value2 = value2;
		this.decimal = decimal;
	}

	private int value;
	private int value2;
	private BigDecimal decimal;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	public BigDecimal getDecimal() {
		return decimal;
	}

	public void setDecimal(BigDecimal decimal) {
		this.decimal = decimal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((decimal == null) ? 0 : decimal.hashCode());
		result = prime * result + value;
		result = prime * result + value2;
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
		Foo other = (Foo) obj;
		if (decimal == null) {
			if (other.decimal != null)
				return false;
		} else if (!decimal.equals(other.decimal))
			return false;
		if (value != other.value)
			return false;
		if (value2 != other.value2)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Foo [value=" + value + ", value2=" + value2 + ", decimal=" + decimal + "]";
	}
}
