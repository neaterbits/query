package com.neaterbits.query.sql.dsl.api;

final class CompiledValueExpression extends CompiledExpression {

	private final Comparable<?> value;

	CompiledValueExpression(Comparable<?> value) {
		
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		this.value = value;
	}
	
	public Comparable<?> getValue() {
		return value;
	}

	@Override
	<T, R> R visit(CompiledExpressionVisitor<T, R> visitor, T param) {
		return visitor.onValue(this, param);
	}
}
