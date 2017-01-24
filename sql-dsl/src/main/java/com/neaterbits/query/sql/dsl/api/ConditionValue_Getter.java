package com.neaterbits.query.sql.dsl.api;


final class ConditionValue_Getter extends ConditionValue {

	private final Getter getter;

	ConditionValue_Getter(Getter getter) {
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
	}

	Getter getGetter() {
		return getter;
	}

	@Override
	<T, R> R visit(ConditionValueVisitor<T, R> visitor, T param) {
		return visitor.onGetter(this, param);
	}

	@Override
	EConditionValue getType() {
		throw new UnsupportedOperationException("Should have been replaced bu field reference");
	}
}
