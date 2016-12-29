package com.neaterbits.query.sql.dsl.api;

final class ConditionStringMatches extends ConditionStringImpl {

	ConditionStringMatches(Getter getter, ConditionValueImpl value) {
		super(getter, value);
	}

	@Override
	<T, R> R visit(ConditionVisitor<T, R> visitor, T param) {
		return visitor.onMatches(this, param);
	}

	@Override
	EClauseOperator getOperator() {
		return EClauseOperator.MATCHES;
	}
}
