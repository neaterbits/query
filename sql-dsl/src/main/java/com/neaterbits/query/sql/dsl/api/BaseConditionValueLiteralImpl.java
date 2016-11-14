package com.neaterbits.query.sql.dsl.api;

abstract class BaseConditionValueLiteralImpl<T> extends ConditionValueImpl {

	private final T literal;
	
	BaseConditionValueLiteralImpl(T literal) {

		if (literal == null) {
			throw new IllegalArgumentException("literal == null");
		}

		this.literal = literal;
	}

	final T getLiteral() {
		return literal;
	}
}
