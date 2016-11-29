package com.neaterbits.query.sql.dsl.api;

final class ConditionStringEndsWith extends ConditionStringImpl {

	ConditionStringEndsWith(Getter getter, ConditionValueImpl value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onEndsWith(this, param);
	}
}
