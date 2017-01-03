package com.neaterbits.query.sql.dsl.api;

final class ConditionValue_Literal_Any<V> extends BaseConditionValueLiteralImpl<V> {

	ConditionValue_Literal_Any(V literal) {
		super(literal);
	}

	@Override
	<T, R> R visit(ConditionValueVisitor<T, R> visitor, T param) {
		return visitor.onLiteralAny(this, param);
	}
}
