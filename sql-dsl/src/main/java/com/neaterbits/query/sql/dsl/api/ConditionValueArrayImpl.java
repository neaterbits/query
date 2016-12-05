package com.neaterbits.query.sql.dsl.api;

final class ConditionValueArrayImpl extends ConditionValueImpl {

	private final Object [] values;
	
	ConditionValueArrayImpl(Object[] values) {
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
