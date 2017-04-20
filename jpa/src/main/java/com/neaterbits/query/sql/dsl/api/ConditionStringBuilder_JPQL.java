package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.IEntityModelUtil;

final class ConditionStringBuilder_JPQL extends ConditionStringBuilder {

	private final QueryParametersDistinct distinctParams;
	
	
	ConditionStringBuilder_JPQL(QueryDialect_SQL dialect, IEntityModelUtil entityModelUtil, QueryParametersDistinct distinctParams) {
		super(dialect, entityModelUtil);
		
		if (distinctParams == null) {
			throw new IllegalArgumentException("distinctParams == null");
		}
		
		this.distinctParams = distinctParams;
	}


	static String makeParamName(int idx) {
		return "param" + idx;
	}
	

	@Override
	void appendParam(Param<?> param) {
		
		final int idx = distinctParams.getIndexOf(param);
		
		if (idx < 0) {
			throw new IllegalArgumentException("Cannot find param " + param + " out of " + distinctParams);
		}
		
		
		append(':').append(makeParamName(idx));
	}


	@Override
	PreparedQueryComparisonRHS convertInParam(ConditionValue_Param value) {
		
		final Param<?> param = value.getParam();
		
		// just add as regular param
		appendParam(param);
		
		return new SQLConditionResolved(getBuiltString(), param);
	}
}
