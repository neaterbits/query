package com.neaterbits.query.sql.dsl.api;

final class ConditionStringBuilder_Native extends ConditionStringBuilder {

	private final QueryParametersDistinct distinctParams;
	
	
	ConditionStringBuilder_Native(QueryParametersDistinct distinctParams) {
		
		if (distinctParams == null) {
			throw new IllegalArgumentException("distinctParams == null");
		}
		
		this.distinctParams = distinctParams;
	}
	
	private int getParamIdx(Param<?> param) {
		final int idx = distinctParams.getIndexOf(param);

		if (idx < 0) {
			throw new IllegalArgumentException("Cannot find param " + param + " out of " + distinctParams);
		}

		return getParamIdx(idx);
	}
	
	static int getParamIdx(int idx) {
		if (idx < 0) {
			throw new IllegalArgumentException("idx < 0 : " + idx);
		}
		
		return idx + 1;
	}
	
	
	@Override
	void appendParam(Param<?> param) {
		append('?').append(getParamIdx(param));
	}

	@Override
	PreparedQueryComparisonRHS convertInParam(ConditionValue_Param value) {
		// Cannot convert value now, must do that later
		// as has no idea how many '?'s to add
		return new JPAConditionNativeInUnresolved(getBuiltString(), (BaseParamImpl<?>)value.getParam());
	}
}
