package com.neaterbits.query.sql.dsl.api;

final class CompiledFieldExpression extends CompiledExpression {

	private final CompiledFieldReference fieldReference;

	CompiledFieldExpression(CompiledFieldReference fieldReference) {

		if (fieldReference == null) {
			throw new IllegalArgumentException("fieldReference == null");
		}

		this.fieldReference = fieldReference;
	}

	CompiledFieldReference getFieldReference() {
		return fieldReference;
	}

	@Override
	<T, R> R visit(CompiledExpressionVisitor<T, R> visitor, T param) {
		return visitor.onField(this, param);
	}
}

