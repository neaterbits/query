package com.neaterbits.query.sql.dsl.api;

final class ConditionValueLiteralAnyImpl<V> extends BaseConditionValueLiteralImpl<V> {

	ConditionValueLiteralAnyImpl(V literal) {
		super(literal);
	}

	@Override
	<T, R> R visit(ConditionValueVisitor<T, R> visitor, T param) {
		return visitor.onLiteralAny(this, param);
	}
}
