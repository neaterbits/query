package com.neaterbits.query.sql.dsl.api;

final class ConditionValue_Array extends ConditionValue {

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
}
