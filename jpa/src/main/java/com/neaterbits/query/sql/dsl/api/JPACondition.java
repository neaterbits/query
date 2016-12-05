package com.neaterbits.query.sql.dsl.api;

abstract class JPACondition {

	private final String prefix;

	abstract void append(StringBuilder sb, ParamValueResolver resolver);
	
	JPACondition(String prefix) {
		
		if (prefix == null) {
			throw new IllegalArgumentException("prefix == null");
		}

		this.prefix = prefix;
	}

	final String getPrefix() {
		return prefix;
	}
}

