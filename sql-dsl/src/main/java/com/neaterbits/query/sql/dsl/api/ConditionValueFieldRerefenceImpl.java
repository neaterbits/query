package com.neaterbits.query.sql.dsl.api;

final class ConditionValueFieldRerefenceImpl extends ConditionValueImpl {

	private final CompiledFieldReference fieldReference;

	ConditionValueFieldRerefenceImpl(CompiledFieldReference fieldReference) {
		
		if (fieldReference == null) {
			throw new IllegalArgumentException("fieldReference == null"); 
		}

		this.fieldReference = fieldReference;
	}

	CompiledFieldReference getFieldReference() {
		return fieldReference;
	}

	@Override
	<T, R> R visit(ConditionValueVisitor<T, R> visitor, T param) {
		return visitor.onFieldReference(this, param);
	}
}
