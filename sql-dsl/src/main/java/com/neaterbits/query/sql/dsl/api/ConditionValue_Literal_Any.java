package com.neaterbits.query.sql.dsl.api;

final class ConditionValue_Literal_Any<V> extends ConditionValue_Literal_Base<V> {

	ConditionValue_Literal_Any(V literal) {
		super(literal);
	}

	
	@Override
	EConditionValue getType() {
		return EConditionValue.ANY;
	}



	@Override
	<T, R> R visit(ConditionValueVisitor<T, R> visitor, T param) {
		return visitor.onLiteralAny(this, param);
	}
}
