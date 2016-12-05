package com.neaterbits.query.sql.dsl.api;

final class ConditionValueParamImpl extends ConditionValueImpl {

	private final Param<?> param;

	ConditionValueParamImpl(Param<?> param) {
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
