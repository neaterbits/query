package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

final class QueryRunner_JPA_Native extends QueryRunner_JPA {

	QueryRunner_JPA_Native(Query jpaQuery) {
		super(jpaQuery);
	}
	
	private static void setParamUtil(Query jpaQuery, BaseParamImpl<?> param, int idx, Object value) {

		final int queryParamIdx = ConditionStringBuilder_Native.getParamIdx(idx);

		jpaQuery.setParameter(queryParamIdx, value);
	}
	
	@Override
	void setParam(BaseParamImpl<?> param, int idx, Object value) {
		
		setParamUtil(jpaQuery, param, idx, value);
		
	}
}
