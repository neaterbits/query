package com.neaterbits.query.sql.dsl.api;

final class ConditionStringBuilder_JPA extends ConditionStringBuilder {

	private final QueryParametersDistinct distinctParams;
	
	
	ConditionStringBuilder_JPA(QueryParametersDistinct distinctParams) {
		
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

}
