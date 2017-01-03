package com.neaterbits.query.sql.dsl.api;

abstract class ConditionValue_Literal_Base<T> extends ConditionValue {

	private final T literal;
	
	ConditionValue_Literal_Base(T literal) {

		if (literal == null) {
			throw new IllegalArgumentException("literal == null");
		}

		this.literal = literal;
	}

	final T getLiteral() {
		return literal;
	}
}
