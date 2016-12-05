package com.neaterbits.query.sql.dsl.api;


final class ConditionValueGetterImpl extends ConditionValueImpl {

	private final Getter getter;

	ConditionValueGetterImpl(Getter getter) {
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
}
