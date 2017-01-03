package com.neaterbits.query.sql.dsl.api;

final class ConditionValue_Param extends ConditionValue {

	private final Param<?> param;

	ConditionValue_Param(Param<?> param) {
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}

		this.param = param;
	}
	
	Param<?> getParam() {
		return param;
	}

	@Override
	<T, R> R visit(ConditionValueVisitor<T, R> visitor, T param) {
		return visitor.onParam(this, param);
	}
}
