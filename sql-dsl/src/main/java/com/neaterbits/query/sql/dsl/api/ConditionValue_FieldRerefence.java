package com.neaterbits.query.sql.dsl.api;

final class ConditionValue_FieldRerefence extends ConditionValue {

	private final CompiledFieldReference fieldReference;

	ConditionValue_FieldRerefence(CompiledFieldReference fieldReference) {
		
		if (fieldReference == null) {
			throw new IllegalArgumentException("fieldReference == null"); 
		}

		this.fieldReference = fieldReference;
	}

	CompiledFieldReference getFieldReference() {
		return fieldReference;
	}

	@Override
	EConditionValue getType() {
		return EConditionValue.FIELD_REFERENCE;
	}

	@Override
	<T, R> R visit(ConditionValueVisitor<T, R> visitor, T param) {
		return visitor.onFieldReference(this, param);
	}
}
