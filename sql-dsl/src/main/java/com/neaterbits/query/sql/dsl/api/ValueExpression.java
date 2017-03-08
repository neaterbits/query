package com.neaterbits.query.sql.dsl.api;

class ValueExpression extends Expression {

	private final Comparable<?> value;

	ValueExpression(Comparable<?> value) {
		
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		this.value = value;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onValue(this, param);
	}
}
