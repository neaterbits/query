package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public class Foo {
	
	
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

}
