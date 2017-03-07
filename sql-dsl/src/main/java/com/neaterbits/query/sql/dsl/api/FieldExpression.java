package com.neaterbits.query.sql.dsl.api;

final class FieldExpression extends Expression {

	private final Getter getter;

	FieldExpression(Getter getter) {
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}

	Getter getGetter() {
		return getter;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onField(this, param);
	}
}
