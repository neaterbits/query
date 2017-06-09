package com.neaterbits.query.sql.dsl.api;

final class ValueExpression extends Expression {

	private final Comparable<?> value;

	ValueExpression(Comparable<?> value) {
		
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		this.value = value;
	}
	
	Comparable<?> getValue() {
		return value;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onValue(this, param);
	}

	@Override
	public String toString() {
		return "ValueExpression [value=" + value + "]";
	}
}
