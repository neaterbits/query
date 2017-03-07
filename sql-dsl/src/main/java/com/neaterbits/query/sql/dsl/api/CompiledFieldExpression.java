package com.neaterbits.query.sql.dsl.api;

final class CompiledFieldExpression extends Expression {

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
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onCompiledField(this, param);
	}
}
