package com.neaterbits.query.sql.dsl.api;

final class ConditionValue_Array extends ConditionValue_Collection {

	private final Object [] values;
	
	ConditionValue_Array(Object[] values) {
		this.values = values;
	}

	Object[] getValues() {
		return values;
	}

	@Override
	<T, R> R visit(ConditionValueVisitor<T, R> visitor, T param) {
		return visitor.onArray(this, param);
	}

	@Override
	EConditionValue getType() {
		return EConditionValue.ARRAY_OF_LITERAL;
	}
}
